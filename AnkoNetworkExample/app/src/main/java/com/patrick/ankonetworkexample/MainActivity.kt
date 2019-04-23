package com.patrick.ankonetworkexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.patrick.ankonetworkexample.JSON.Response
import kotlinx.android.synthetic.main.activity_download_file.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), AnkoLogger {
    companion object {
        val API_ZEN_URL = "https://api.github.com/zen"
        val JSON_URL = "https://www.worldtradingdata.com/api/v1/stock?symbol=AAPL,MSFT,HSBA.L&api_token=demo"
        val MOCK_DOWNLOAD_URL = "https://httpbin.org"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        //doPureNetwork()
        //doKotlinNetwork()
        //doJSONParsing()
        //downloadFile()
        //recyclerList()
        coroutines()
    }

    // 서드파티 라이브러리 도움 없이 네트워크 통신 수행
    private fun doPureNetwork() {
        thread {
            lateinit var urlConnection: HttpURLConnection
            lateinit var reader: BufferedReader
            try {
                val url = URL(API_ZEN_URL)
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.connect()
                val inputStream: InputStream = urlConnection.inputStream
                val buffer: StringBuffer = StringBuffer()

                reader = BufferedReader(InputStreamReader(inputStream))
                var line: String?
                while (true) {
                    line = reader.readLine() ?: break
                    buffer.append(line + "\n")
                }
                val result = buffer.toString()
                info(message = result)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                urlConnection?.disconnect()
                reader?.close()
            }
        }
    }

    // 코틀린의 네트워크 작업 수행
    private fun doKotlinNetwork() {
        // 비동기 백그라운드 작업
        doAsync {
            val result = URL(API_ZEN_URL).readText()
            // Ui 스레드에서 토스트 표시
            uiThread {
                toast(result)
            }
        }
    }

    // JSON Data parsing
    private fun doJSONParsing() {
        doAsync {
            var response = URL(JSON_URL).readText()
            var gson = Gson()
            val data = gson.fromJson(response, Response::class.java)
            info(message = data.data?.get(0))
        }
    }

    // Fuel을 사용한 파일 다운로드 예
    private fun downloadFile() {
        setContentView(R.layout.activity_download_file)

        info("${filesDir.absolutePath} / ${filesDir.canonicalPath}")

        btn_download_file.onClick {
            progressBar.progress = 0
            Fuel.download("$MOCK_DOWNLOAD_URL/bytes/32768")
                .fileDestination { _, _ ->
                    File.createTempFile("abcd", ".tmp")
                }.progress { readBytes, totalBytes ->
                    val progress = readBytes.toFloat() / totalBytes.toFloat()
                    info("progress : $progress")
                    progressBar.progress = progress.toInt() * 100
                }.response { request, response, result ->
                    info("Status result : ${result.component1().toString()}")
                    info("Status response : ${response.responseMessage}")
                    info("Status request : ${request.url}")
                }
        }
    }

    // RecyclerView 를 이용해 무한스크롤 되는 리스트 만들기
    private fun recyclerList() {
        setContentView(R.layout.activity_main)

        val dataList = mutableListOf<Int>()

        val layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerAdapter(recyclerList = updateDataList(dataList))

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // 아래쪽으로 스크롤이 더 이상 가능하지 않다면
                if (!recyclerView.canScrollVertically(1)) {
                    // 데이터를 추가해 줌
                    onScrolledBottom()
                }
            }

            fun onScrolledBottom() {
                val initialSize = dataList.size
                updateDataList(dataList)
                val updatedSize = dataList.size
                adapter.notifyItemRangeChanged(initialSize, updatedSize)
            }
        })

    }

    // 데이터를 임의로 30개씩 추가
    private fun updateDataList(dataList: MutableList<Int>): List<Int> {
        repeat(30) {
            dataList.add(dataList.size + 1)
        }
        return dataList
    }

    // 코틀린 코루틴 테스트
    private fun coroutines() {
//        GlobalScope.launch {
//            info("Hello")
//            delay(1000)
//            info("World!")
//        }
        suspend fun longOperationOne(): Int {
            delay(1000)
            return 10
        }

        suspend fun longOperationTwo(): Int {
            delay(2000)
            return 20
        }

//        val one = GlobalScope.async { longOperationOne() }
//        val two = GlobalScope.async { longOperationTwo() }
//        GlobalScope.async { info("Result is $${one.await() + two.await()}") }
        val one = GlobalScope.async(Dispatchers.Default) { longOperationOne() }
        val two = GlobalScope.async(Dispatchers.Default) { longOperationTwo() }

        runBlocking {
            info("Result is $${one.await() + two.await()}")
        }
    }
}
