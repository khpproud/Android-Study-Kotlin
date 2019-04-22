package com.patrick.ankosqliteexample

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import org.jetbrains.anko.*

class MainActivityUI: AnkoComponent<MainActivity> {
    lateinit var name: EditText
    lateinit var message: EditText
    lateinit var btnSend: Button
    lateinit var btnRead: Button

    override fun createView(ui: AnkoContext<MainActivity>): View  = with(ui) {
        verticalLayout {
            gravity = Gravity.CENTER
            padding = dip(20)
            textView {
                gravity = Gravity.CENTER
                text = "Input your requests."
                textColor = Color.BLACK
                textSize = 24f
            }.lparams(width = matchParent) {
                margin = dip(20)
            }
            name = editText {
                hint = "Input your name"
                singleLine = true
            }
            message = editText {
                hint = " Input your message"
                lines = 3
            }
            btnSend = button("Send")
            btnRead = button("Read")
        }
    }
}