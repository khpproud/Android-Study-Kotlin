package com.patrick.ankosqliteexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.patrick.ankosqliteexample.database.Customer
import com.patrick.ankosqliteexample.database.Request
import com.patrick.ankosqliteexample.database.database
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        //saveDatabase()
        //readDatabase()
        transformData()
    }

    // Anko SQLite 사용해 간단한 데이터 저장하기
    private fun saveDatabase() {
        // Main layout 정의
        val mainUI = MainActivityUI()
        mainUI.setContentView(this)

        mainUI.btnSend.onClick {
            database.use {
                insert("Requests",
                    "name" to mainUI.name.text.toString(),
                    "message" to mainUI.message.text.toString())
            }
        }
    }

    // Anko SQLite 사용해 간단한 데이터 읽고 쓰기
    private fun readDatabase() {
        val mainUI = MainActivityUI()
        mainUI.setContentView(this)

        mainUI.btnSend.onClick {
            database.use {
                insert(Request.TABLE_NAME,
                    Request.COLUMN_NAME to mainUI.name.text.toString(),
                    Request.COLUMN_MESSAGE to mainUI.message.text.toString())
            }
        }

        mainUI.btnRead.onClick {
            var requests = database.use {
                // DB에서 select 쿼리 함 - 데이터가 하나 이상인 경우
                select(Request.TABLE_NAME).parseList(classParser<Request>())
            }

            for (x in requests) {
                info("${x.name}: ${x.message}")
            }
        }
    }

    // 데이터베이스 커서를 객체리스트로 변환
    private fun transformData() {
        val mainUI = MainActivityUI2()
        mainUI.setContentView(this)

        mainUI.btnSend.onClick {
            database.use {
                insert(Customer.TABLE_NAME,
                    Customer.COLUMN_NAME to mainUI.name.text.toString(),
                    Customer.COLUMN_PHONE_NUM to mainUI.phone.text.toString())
            }

            toast("Success!")
            mainUI.name.text.clear()
            mainUI.phone.text.clear()
        }

        mainUI.btnRead.onClick {
            var customers = database.use {
                // 데이터가 하나 이상인 경우 - parseList
                //select(Customer.TABLE_NAME).parseList(classParser<Customer>())

                // 데이터가 정확히 한 개인 경우 - parseSingle
//                select(Customer.TABLE_NAME).parseSingle(classParser<Customer>())

                // 데이터가 0개 혹은 1개인 경우 - parseOpt
                select(Customer.TABLE_NAME).parseOpt(classParser<Customer>())
            }

//            for (c in customers) {
//                info("${c.name} : ${c.phoneNumber}")
//            }

//            info("${customers.name} : ${customers.phoneNumber}")
            info("${customers?.name} : ${customers?.phoneNumber}")
        }

        mainUI.btnDelete.onClick {
            database.use {
                delete(Customer.TABLE_NAME, "", null)
            }
        }
    }
}
