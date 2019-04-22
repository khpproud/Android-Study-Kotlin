package com.patrick.ankoexamples

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.design.indefiniteSnackbar
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.design.snackbar

class MainActivity : AppCompatActivity(), AnkoLogger {
    // 기본적으로 태그는 클래스 명이지만 변경하고 싶다면 Override
    override val loggerTag = "CustomTag"

    // Anko DSL layout
    private val rootView: LinearLayout by lazy {
          verticalLayout {
              padding = dip(20)
              val editText1 = editText {
                  hint = "Input your name..."
              }
              val editText2 = editText {
                  hint = "Input messages..."
              }

              button("Send") {
                  setOnClickListener {
                      toast("clicked...")
                  }
              }
          }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setContentView(rootView)
        //showDialog()
        //rootView.snackbar("Cool!!!")
        //rootView.longSnackbar("Ha ha!!!")
//        rootView.indefiniteSnackbar("Ok", "Retry") {
//            toast("Loading...")
//        }
//        MainActivityUI().setContentView(this)

        // XML로 작성된 레이아웃의 뷰 조작하기
        var name = find<EditText>(R.id.name)
        var btnSend = find<Button>(R.id.btn_send)
        btnSend.setOnClickListener {
            toast("Hello, ${name.text}!")
        }

        // Anko를 이용해 로그 남기기
        info("This is a Anko log...")

        // 다음과 같이 객체로 선언하여 사용도 가능
        val log = AnkoLogger<MainActivity>()
        val logWithASpeceficTag = AnkoLogger("SpecificTag")

        // 즉각 로그
        log.info("Immediately")
        // 지연 로그 (Log.isLoggable(tag, Log.{LEVEL}) 이 true 일 때만
        log.info { "by late" }

        logWithASpeceficTag.info("Specific....")

        // 버전 체크하기
        doIfSdk(Build.VERSION_CODES.O) {
            // 버전 코드가 인자와 동일할 때 실행
            toast("Version : 26(Oreo)")
        }

        doFromSdk(Build.VERSION_CODES.LOLLIPOP) {
            // 버전 코드가 인자 이상일 때 실행
            longToast("Version 21(Lollipop) Over...")
        }
    }

    // AnkoComponent를 상속받은 별도의 클래스에 작성하는것도 가능
    class MainActivityUI: AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
            verticalLayout {
                padding = dip(20)
                editText {
                    hint = "Input your name..."
                }
                editText {
                    hint = "Input messages..."
                }
                button("Send") {
                    setOnClickListener {
                        toast("complete...")
                    }
                }
            }
        }

    }

    private fun showSnackBar() {
        rootView
    }

    private fun showDialog() {
        // 타이틀과 본문만 있는 다이얼로그
        //alert("A simple alert", "Alert") { }.show()

        // yes, no, neutral 버튼 추가
//        alert("Which one?", "Popup") {
//            yesButton {
//                toast("OK")
//            }
//            noButton {
//                toast("No")
//            }
//            neutralPressed("Meh") {
//                toast("Neutral")
//            }
//        }.show()

        // yse, no 버튼의 문구를 변경
//        alert("Select any", "Popup") {
//            positiveButton("Good") {
//                toast("Good~~~")
//            }
//            negativeButton("Bad") {
//                toast("Bad~~~~")
//            }
//        }.show()

        // progressDialog - Deprecated
//        val dialog = progressDialog(message = "loading...", title = "data")
//        dialog.show()

        // 무한 로딩
//        indeterminateProgressDialog("loading...").show()

        // 텍스트 목록이 있는 다이얼로그
        val animals = listOf("Cat", "Dog", "Rabbit", "Pig", "Monkey")
        selector("Which one do you like?", animals) {
            _, i -> toast("${animals[i]} selected...")
        }
    }

}
