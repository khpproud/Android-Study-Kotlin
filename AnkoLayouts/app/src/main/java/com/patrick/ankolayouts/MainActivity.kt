package com.patrick.ankolayouts

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.test.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onLongClick
import org.jetbrains.anko.sdk25.coroutines.onRatingBarChange

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        //basicLayout()
        //coordiLayout()
        //xmlBasedLayout()
//        val mainUI = MainUI()
//        mainUI.setContentView(this)
//
//        // MainUI 의 뷰에 접근
//        mainUI.btnSend.onClick {
//            toast("Hello ${mainUI.name.text}!")
//        }
//        relative()

//        eventListener()
//        xmlToDSL()
        callSnackbar()
    }

    // 간단한 DSL 레이아웃 코드 추가
    private fun basicLayout() {
        verticalLayout {
            button("Hello Anko!!!") {
                onClick {
                    toast("Clicked!!!")
                }
            }
        }
    }

    // 앱바와 함꼐 coordinatorLayout에 추가
    private fun coordiLayout() {
        coordinatorLayout {
            fitsSystemWindows = true

            // 레이아웃에 매개변수 설정
            lparams {
                width = matchParent
                height = matchParent
            }

            // 앱바 추가
            appBarLayout {
                toolbar {
                    setTitleTextColor(Color.WHITE)
                    title = "Contact Form"
                }.lparams(width = matchParent, height = wrapContent)
            }.lparams(width = matchParent, height = wrapContent)

            verticalLayout {
                verticalLayout {
                    backgroundColor = Color.LTGRAY
                    gravity = Gravity.CENTER

                    textView("Logo") {
                        textColor = R.color.colorAccent
                        textSize = 24f
                    }.lparams(width = wrapContent, height = wrapContent) {
                        horizontalMargin = dip(5)
                        topMargin = dip(10)
                    }
                }.lparams(width = matchParent, height = dip(200)) {
                    horizontalMargin = dip(5)
                    topMargin = dip(10)
                }
                padding = dip(20)

                val name = editText {
                    hint = "Input your name"
                }

                val message = editText {
                    hint = "Input messages"
                }
                button("Send") {
                    onClick {
                        toast("Hello ${name.text}!")
                    }
                }
            }.lparams {
                width = matchParent
                height = matchParent
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }

    // 이미 만들어진 XML 추가
    private fun xmlBasedLayout() {
        setContentView(R.layout.activity_main)
        // find() 메소드 없이 뷰의 id만으로도 접근 가능
        var name = name.text
        var msg = message.text
        btn_send.onClick {
            toast("Hello! $name")
        }
    }

    // AnkoComponent 인터페이스 이용 레이아웃 추가
    class MainUI: AnkoComponent<MainActivity> {
        lateinit var name: EditText
        lateinit var message: EditText
        lateinit var btnSend: Button

        override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
            verticalLayout {
                padding = dip(20)
                name = editText {
                    hint = "Input your name!"
                }
                message = editText {
                    hint = "Input messages"
                }
//                btnSend = button("Send") {
//                    onClick {
//                        toast("Clicked!!!")
//                    }
//                }
                btnSend = themedButton("Send", theme = R.style.newButton) {

                }
            }
        }
    }

    private fun relative() {
        relativeLayout {
            val ID_BTN_OK = 1

            val okButton = button("Ok") {
                id = ID_BTN_OK
            }
            okButton.lparams {
                alignParentEnd()
            }

            button("Cancel") {

            }.lparams {
                leftOf(okButton)
            }
            lparams(matchParent, matchParent)
        }
    }

    // 뷰에 이벤트 리스너 추가
    private fun eventListener() {
        verticalLayout {
            padding = dip(20)
            val name = editText {
                hint = "Input your name"
            }
            val message = editText {
                hint = "Input messages"
            }
            button("Send") {
                onLongClick {
                    toast("Score Inputted")
                }
            }
            var rating = ratingBar {
                onRatingBarChange { _, rating, _ -> toast(rating.toString()) }
            }.lparams(wrapContent, wrapContent)

            // DSL 에서 xml 레이아웃 추가
            include<View>(R.layout.test) {
                backgroundColor = Color.CYAN
            }.lparams(width = matchParent)

            btn_test.onClick {
                toast("Test Clicked!!!")
            }
        }
    }

    // XMl Layout을 DSL로 변환
    private fun xmlToDSL() {
        coordinatorLayout {
            appBarLayout {
                toolbar {
                    backgroundResource = R.color.colorPrimary
                }
            }.lparams(width = matchParent, height = wrapContent)

            linearLayout {
                gravity = Gravity.CENTER
                orientation = LinearLayout.VERTICAL

                textView("Text1") {
                    id = Ids.text1
                }.lparams(width = matchParent, height = matchParent) {
                    margin = dip(10)
                }
                calendarView {
                    id = Ids.calendarView
                }.lparams(width = matchParent, height = dip(180)) {
                    margin = dip(10)
                }
                button("Done") {
                    backgroundResource = R.color.colorAccent
                    id = Ids.btn_done
                }.lparams(width = wrapContent, height = wrapContent) {
                    margin = dip(10)
                }
            }.lparams(width = matchParent, height = wrapContent)

            floatingActionButton {
                id = Ids.fab
                imageResource = android.R.drawable.ic_dialog_email
            }.lparams(width = wrapContent, height = wrapContent) {
                gravity = Gravity.BOTTOM or Gravity.END
                margin = dip(10)
            }
        }
    }

    private object Ids {
        val btn_done = 1
        val calendarView = 2
        val fab = 3
        val text1 = 4
        val toolbar = 5
    }

    // 스낵바 띄우기
    private fun callSnackbar() {
        verticalLayout {
            padding = dip(20)
            button("Snackbar1") {
                onClick {
                    snackbar("I am Snackbar1")
                }
            }
            button("LongSnackbar2") {
                onClick {
                    longSnackbar("I am longSanckbar2")
                }
            }
            button("IndefiniteSnackbar3") {
                onClick {
                    indefiniteSnackbar("I am indefiniteSnackbar3", "Ok") {

                    }
                }
            }
        }
    }
}
