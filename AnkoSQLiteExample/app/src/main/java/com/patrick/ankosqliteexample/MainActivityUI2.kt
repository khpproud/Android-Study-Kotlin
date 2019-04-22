package com.patrick.ankosqliteexample

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import org.jetbrains.anko.*

class MainActivityUI2: AnkoComponent<MainActivity> {
    lateinit var name: EditText
    lateinit var phone: EditText
    lateinit var btnSend: Button
    lateinit var btnRead: Button
    lateinit var btnDelete: Button

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        verticalLayout {
            padding = dip(20)
            textView {
                gravity = Gravity.CENTER
                text = "Input your customer information."
                textColor = Color.BLACK
                textSize = 24f
            }.lparams(width = matchParent) {
                margin = dip(20)
            }
            name = editText {
                hint = "Name"
            }
            phone = editText {
                hint = "Phone Number"
            }
            btnSend = button("Send")
            btnRead = button("Read")
            btnDelete = button("Delete")
        }
    }

}