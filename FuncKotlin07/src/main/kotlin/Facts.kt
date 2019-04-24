package chap07

import com.github.salomonbrys.kotson.*
import com.github.salomonbrys.kotson.registerTypeAdapter
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import nl.komponents.kovenant.async
import org.h2.jdbcx.JdbcDataSource
import org.http4k.client.ApacheClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.concurrent.thread

// 실제 코루틴 사용 예

enum class Gender {
    MALE, FEMALE;

    companion object {
        fun valueOfIgnoreCase(name: String): Gender = valueOf(name.toUpperCase())
    }
}

typealias UserId = Int

data class User(val id: UserId, val firstName: String, val lastName: String, val gender: Gender)

data class Fact(val id: Int, val value: String, val user: User? = null)

// UserService 인터페이스는 유저 ID로 식별되느 유저에 대한 척 노리스의 진실을 반환하는 getFact 하나의 메소드만 가짐
interface UserService {
    fun getFact(id: UserId): Fact
}

interface UserClient {
    fun getUser(id: UserId): User
}

interface FactClient {
    fun getFact(user: User): Fact
}

// User와 Fact를 가져오기 위한 Http 통신용 추상 클래스
abstract class WebClient {
    protected val apacheClient = ApacheClient()
    protected val gson = GsonBuilder()
        .registerTypeAdapter<User> {
            deserialize { deserializerArg ->
                val json = deserializerArg.json
                User(json["info"]["seed"].int,
                    json["results"][0]["name"]["first"].string.capitalize(),
                    json["results"][0]["name"]["last"].string.capitalize(),
                    Gender.valueOfIgnoreCase(json["results"][0]["gender"].string))
            }
        }
        .registerTypeAdapter<Fact> {
            deserialize {
                deserializerArg -> val json = deserializerArg.json
                Fact(json["value"]["id"].int,
                    json["value"]["joke"].string)
            }
        }.create()!!
}

// UserClient
class Http4KUserClient: WebClient(), UserClient {
    override fun getUser(id: UserId): User {
        return gson.fromJson(apacheClient(Request(Method.GET,
            "https://randomuser.me/api")
            .query("seed", id.toString()))
            .bodyString())
    }
    }

// FactClient : copy()를 사용해 Fact 인스턴스 내로 유저 값을 설정
class Http4KFactClient: WebClient(), FactClient {
    override fun getFact(user: User): Fact {
        return gson.fromJson<Fact>(apacheClient(Request(Method.GET,
            "http://api.icndb.com/jokes/random")
            .query("firstName", user.firstName)
            .query("lastName", user.lastName))
            .bodyString())
            .copy(user = user)
    }
}

// Mock Client
class MockUserClient: UserClient {
    override fun getUser(id: UserId): User {
        println("MockUserClient.getUser")
        Thread.sleep(500)
        return User(id, "Foo", "Bar", Gender.MALE)
    }
}

class MockFactClient: FactClient {
    override fun getFact(user: User): Fact {
        println("MockFactClient.getFact")
        Thread.sleep(500)
        return Fact(Random().nextInt(), "Fact ${user.firstName}, ${user.lastName}", user)
    }
}

// 데이터베이스 저장소
interface UserRepository {
    fun getUserById(id: UserId): User?
    fun insertUser(user: User)
}

interface FactRepository {
    fun getFactByUserId(id: UserId): Fact?
    fun insertFact(fact: Fact)
}

// 저장소의 경우 Spring 5의 JdbcTemplate 사용
abstract class JdbcRepository(protected val template: JdbcTemplate) {
    protected fun <T> toNullable(block: () -> T): T? {
        return try {
            block()
        } catch (_: EmptyResultDataAccessException) {
            null
        }
    }
}

// UserRepository
class JdbcUserREpository(template: JdbcTemplate): JdbcRepository(template), UserRepository {
    override fun getUserById(id: UserId): User? {
        return toNullable {
            template.queryForObject("select * from USERS where id = ?", id) {
                resultSet, _ -> with(resultSet) {
                User(getInt("ID"),
                    getString("FIRST_NAME"),
                    getString("LAST_NAME"),
                    Gender.valueOfIgnoreCase(getString("GENDER")))
                }
            }
        }
    }

    override fun insertUser(user: User) {
        template.update("Insert into USERS values (?, ?, ?, ?)",
            user.id, user.firstName, user.lastName, user.gender.name)
    }
}

// FactRepository
class JdbcFactRepository(template: JdbcTemplate): JdbcRepository(template), FactRepository {
    override fun getFactByUserId(id: UserId): Fact? {
        return toNullable {
            template.queryForObject("select * from USERS as U inner join FACTS as F on U.ID " +
                    "= F.USER where U.ID = ?", id) {
                resultSet, _ ->
                with(resultSet) {
                    Fact(getInt(5),
                        getString(6),
                        User(getInt(1),
                            getString(2),
                            getString(3),
                            Gender.valueOfIgnoreCase(getString(4))))
                }
            }
        }
    }

    override fun insertFact(fact: Fact) {
        template.update("insert into FACTS values (?, ?, ?)", fact.id, fact.value, fact.user?.id)
    }
}

// 데이터베이스는 H2 인메모리 데이터베이스 사용 : H2 데이터 소스로 JdbcTemplate을 생성하고 준비가되면 apply 확장함수 안에 테이블 생성
fun initJdbcTemplate(): JdbcTemplate {
    return JdbcTemplate(JdbcDataSource()
        .apply {
            setUrl("jdbc:h2:mem:facts_app;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false")
        })
        .apply {
            execute("CREATE TABLE USERS (ID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "FIST_NAME VARCHAR(64) NOT NULL, LAST_NAME VARCHAR(64) NOT NULL, GENDER VARCHAR(8) NOT NULL);")
            execute("CREATE TABLE FACTS (ID INT AUTO_INCREMENT PRIMARY KEY, VALUE_ TEXT NOT NULL, USER INT NOT NULL, " +
                    "FOREIGN KEY (USER) REFERENCES USERS(ID) ON DELETE RESTRICT)")
        }
}

// 테스트를 위한 Mock 클래스
class MockUserRepository: UserRepository {
    private val users = hashMapOf<UserId, User>()
    override fun getUserById(id: UserId): User? {
        println("MockUserRepository,getUserById")
        Thread.sleep(200)
        return users[id]
    }

    override fun insertUser(user: User) {
        println("MockUserRepository.insertUser")
        Thread.sleep(200)
        users[user.id] = user
    }
}

class MockFactRepository: FactRepository {
    private val facts = hashMapOf<UserId, Fact>()
    override fun getFactByUserId(id: UserId): Fact? {
        println("MockFactRepository.getFactByUserId")
        Thread.sleep(200)
        return facts[id]
    }

    override fun insertFact(fact: Fact) {
        println("MockFactRepository.insertFact")
        Thread.sleep(200)
        facts[fact.user?.id?: 0] = fact
    }
}

// 동기 구현 : 작성하기 쉽고 예측하기 쉬우며 테스트 하기 쉽지만 최적의 방식이 아님
class SynchronousUserService(private val userClient: UserClient, private val factClient: FactClient,
                             private val userRepository: UserRepository, private val factRepository: FactRepository)
    : UserService {
    override fun getFact(id: UserId): Fact {
        val user = userRepository.getUserById(id)
        return if (user == null) {
            val userFromService = userClient.getUser(id)
            userRepository.insertUser(userFromService)
            getFact(userFromService)
        } else {
            factRepository.getFactByUserId(id) ?: getFact(user)
        }
    }

    private fun getFact(user: User): Fact {
        val fact = factClient.getFact(user)
        factRepository.insertFact(fact)
        return fact
    }
}

// 콜백 : 코드를 여러 스레드에서 실행하고 완료할 때 콜백 함수를 호출
class CallbackUserClient(private val client: UserClient) {
    fun getUser(id: Int, callback: (User) -> Unit) {
        thread {
            callback(client.getUser(id))
        }
    }
}

class CallbackFactClient(private val client: FactClient) {
    fun get(user: User, callback: (Fact) -> Unit) {
        thread {
            callback(client.getFact(user))
        }
    }
}

class CallbackUserRepository(private val userRepository: UserRepository) {
    fun getUserById(id: UserId, callback: (User?) -> Unit) {
        thread {
            callback(userRepository.getUserById(id))
        }
    }

    fun insertUser(user: User, callback: () -> Unit) {
        thread {
            userRepository.insertUser(user)
            callback()
        }
    }
}

class CallbackFactRepository(private val factRepository: FactRepository) {
    fun getFactByUserId(id: Int, callback: (Fact?) -> Unit) {
        thread {
            callback(factRepository.getFactByUserId(id))
        }
    }

    fun insertFact(fact: Fact, callback: () -> Unit) {
        thread {
            factRepository.insertFact(fact)
            callback()
        }
    }
}

// 이 어댑터는 별도의 스레드에서 코드를 실행하고 완료되면 콜백 람다를 호출
class CallbackUserService(private val userClient: CallbackUserClient,
                          private val factClient: CallbackFactClient,
                          private val userRepository: CallbackUserRepository,
                          private val factRepository: CallbackFactRepository): UserService {
    override fun getFact(id: UserId): Fact {
        var aux: Fact? = null
        userRepository.getUserById(id) {
            user ->
            if (user == null) {
                userClient.getUser(id) {
                    userFromClient ->
                    userRepository.insertUser(userFromClient) { }
                    factClient.get(userFromClient) {
                        fact ->
                        factRepository.insertFact(fact) { }
                        aux = fact
                    }
                }
            } else {
                factRepository.getFactByUserId(id) {
                    fact ->
                    if (fact == null) {
                        factClient.get(user) {
                            factFromClient ->
                            factRepository.insertFact(factFromClient) { }
                            aux = factFromClient
                        }
                    } else {
                        aux = fact
                    }
                }
            }
        }
        while (aux == null) {
            Thread.sleep(2)
        }
        return aux!!
    }
}

// Future : 미래에 완료될 수 있는 계산, Future.get() 실행할 때 결과를 얻게 되지만 스레드도 차단
class FutureUserService(private val userClient: UserClient, private val factClient: FactClient,
                        private val userRepository: UserRepository, private val factRepository: FactRepository)
    : UserService {
    override fun getFact(id: UserId): Fact {
        val executor = Executors.newFixedThreadPool(2)
        val user = executor.submit<User?> {
            userRepository.getUserById(id)
        }.get()
        return if (user == null) {
            val userFromService = executor.submit<User> {
                userClient.getUser(id)
            }.get()
            executor.submit { userRepository.insertUser(userFromService) }
            getFact(userFromService, executor)
        } else {
            executor.submit<Fact> {
                factRepository.getFactByUserId(id) ?: getFact(user, executor)
            }.get()
        }.also {
            executor.shutdown()
        }
    }

    private fun getFact(user: User, executor: ExecutorService): Fact {
        val fact = executor.submit<Fact> { factClient.getFact(user) }.get()
        executor.submit { factRepository.insertFact(fact) }
        return fact
    }
}

// 코루틴 사용
class CoroutineUserService(private val userClient: UserClient,
                           private val factClient: FactClient,
                           private val userRepository: UserRepository,
                           private val factRepository: FactRepository): UserService {
    override fun getFact(id: UserId): Fact = runBlocking {
        val user = async { userRepository.getUserById(id) }.await()
        if (user == null) {
            val userFromService = async { userClient.getUser(id) }.await()
            launch { userRepository.insertUser(userFromService) }
            getFact(userFromService)
        } else {
            async { factRepository.getFactByUserId(id) ?: getFact(user) }.await()
        }
    }
    private suspend fun getFact(user: User): Fact {
        val fact: Deferred<Fact> = GlobalScope.async { factClient.getFact(user) }
        GlobalScope.launch { factRepository.insertFact(fact.await()) }
        return fact.await()
    }
}

fun main() {
    awaitEnter {
        pauseUntilEnter()
        fun execute(userService: UserService, id: Int) {
            val (fact, time) = inTime {
                userService.getFact(id)
            }

            println("진실 = $fact")
            println("시간 = $time ms.")
        }

        val userClient = MockUserClient()
        val factClient = MockFactClient()
        val userRepository = MockUserRepository()
        val factRepository = MockFactRepository()

        //val userService = SynchronousUserService(userClient, factClient, userRepository, factRepository)
        //val userService = CallbackUserService(CallbackUserClient(userClient), CallbackFactClient(factClient),
        //    CallbackUserRepository(userRepository), CallbackFactRepository(factRepository))

//        val userService = FutureUserService(userClient, factClient, userRepository, factRepository)
        val userService = CoroutineUserService(userClient, factClient, userRepository, factRepository)

        execute(userService, 1)
        execute(userService, 2)
        execute(userService, 1)
        execute(userService, 2)
        execute(userService, 3)
        execute(userService, 4)
        execute(userService, 5)
        execute(userService, 10)
        execute(userService, 100)
    }
}

inline fun <T> inTime(body: () -> T): Pair<T, Long> {
    val startTime = System.currentTimeMillis()
    val v = body()
    val endTime = System.currentTimeMillis()
    return v to endTime - startTime
}

typealias Hook = () -> Unit

class ShutdownBuilder {
    private val scanner = Scanner(System.`in`)

    private val hooks = arrayListOf<Hook>()

    fun hook(hook: Hook) {
        hooks.add(hook)
    }

    fun pauseUntilEnter() {
        println("진행하려면 엔터를 누르세요")
        scanner.nextLine()
    }

    fun executeShutdown() {
        println("셧다운 실행")
        hooks.forEach(Hook::invoke)
    }
}

//유틸
fun awaitEnter(body: ShutdownBuilder.() -> Unit) {
    val sb = ShutdownBuilder()
    sb.body()
    sb.pauseUntilEnter()
    sb.executeShutdown()
    println("바이!")
}
