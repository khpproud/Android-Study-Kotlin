package com.patrick.todolist

import android.os.Bundle
import android.support.constraint.solver.widgets.ConstraintWidget
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.ViewStubCompat
import android.view.View
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import java.util.*

class EditActivty : AppCompatActivity() {
    var realm = Realm.getDefaultInstance()

    val calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        // 업데이트 조건
        val id = intent.getLongExtra("id", -1L)
        if(id == -1L)
            insertMode()
        else
            updateMode(id)

        // 캘린더 뷰의 날짜를 선택했을 때 Calendar 객체에 설정
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    // 추가 모드 초기화
    private fun insertMode() {
        // 삭제 버튼을 감추기
        deleteFab.hide()

        // 완료 버튼을 클릭하면 추가
        doneFab.setOnClickListener {
            insertTodo()
        }
    }

    // 수정 모드 초기화
    private fun updateMode(id: Long) {
        // id에 해당하는 객체를 화면에 표시
        val todo =realm.where<Todo>().equalTo("id", id).findFirst()!!
        todoEditText.setText(todo.title)
        calendarView.date = todo.date

        // 완료 버튼을 클릭하면 수정
        doneFab.setOnClickListener {
            updateTodo(id)
        }

        // 삭제 버튼을 클릭하면 삭제
        deleteFab.setOnClickListener {
            deleteTodo(id)
        }
    }


    // 할 일 추가
    private fun insertTodo() {
        if(todoEditText.text.toString().equals("")) {
            toast( R.string.message_not_inputed )
            return
        }

        realm.beginTransaction()

        val newItem = realm.createObject<Todo>(nextId())
        newItem.title = todoEditText.text.toString()
        newItem.date = calendar.timeInMillis

        realm.commitTransaction()

        alert(R.string.add_alert) {
            yesButton { finish() }
        }.show()
    }

    // 다음 id를 반환
    private fun nextId(): Int {
        val maxId = realm.where<Todo>().max("id")
        if (maxId != null)
            return maxId.toInt() + 1
        return 0
    }

    // 할 일 수정
    private fun updateTodo(id: Long) {
        if(todoEditText.text.toString().equals("")) {
            toast( R.string.message_not_inputed )
            return
        }
        realm.beginTransaction()

        val updateItem = realm.where<Todo>().equalTo("id", id).findFirst()!!
        // 값 수정
        updateItem.title = todoEditText.text.toString()
        updateItem.date = calendar.timeInMillis

        realm.commitTransaction()

        alert(R.string.update_alert) {
            yesButton { finish() }
        }.show()
    }

    // 할 일 삭제
    private fun deleteTodo(id: Long) {
        realm.beginTransaction()
        val deleteItem = realm.where<Todo>().equalTo("id", id).findFirst()!!

        // 삭제할 객체
        deleteItem.deleteFromRealm()
        realm.commitTransaction()

        alert(R.string.delete_alert) {
            yesButton { finish() }
        }.show()
    }
}
