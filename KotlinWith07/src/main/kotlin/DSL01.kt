package seven.dsl

// DSL (domain specific language) 예

// 기사 항목을 나타내는 Post 클래스
data class Post(var name: String = "",
                var url: String = "",
                var tags: List<String> = listOf())

// 기사의 범주를 포함하는 클래스
class PostCategory(val name: String) {
    // 기사의 리스트를 저장
    var posts: List<Post> = listOf()

    // 간단한 기사 추가를 위한 함수 정의 - Post가 리시버 형식인 함수 매개변수 포함
    fun post(init: Post.() -> Unit) {
        val post = Post()
        post.init()
        posts += post
    }
}

// 범주의 리스트를 저장하며 간단하게 범주를 정의할 수 있게 함
class PostList {
    var categories: List<PostCategory> = listOf()

    fun category(name: String, init: PostCategory.() -> Unit) {
        val category = PostCategory(name)
        category.init()
        categories += category
    }
}

fun definesPosts(init: PostList.() -> Unit): PostList {
    val postList = PostList()
    postList.init()
    return postList
}

fun main() {
    // 다음과 같이 안전형 빌더로 객체 생성
    val postList = definesPosts {
        category("Kotlin") {
            post {
                name = "Awesome delegates"
                url = "SomeUrl.com"
            }
            post {
                name = "Awesome extensions"
                url = "SomeUrl.com"
            }
        }
        category("Android") {
            post {
                name = "Awesome app"
                url = "AnyUrl.com"
                tags = listOf("Kotlin", "Google Wow")
            }
        }
    }

    println(postList?.categories[1].posts[0])
}