package com.patrick.todolist

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    val realm = Realm.getDefaultInstance()
    private var isAscending: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // 전체 할 일 정보를 가져와서 날짜순으로 내림차순 정렬
        val realmResult = realm.where<Todo>().findAll().sort("date", loadOrder())
        val adapter = TodoListAdapter(realmResult)
        listView.adapter = adapter

        // 데이터가 변경되면 어댑터에 적용
        realmResult.addChangeListener { _ -> adapter.notifyDataSetChanged() }

        listView.setOnItemClickListener { _, _, _, id ->
            // 할 일 수정
            startActivity<EditActivty>("id" to id)
        }

        addFab.setOnClickListener {
            startActivity<EditActivty>()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.menu_order -> {
                saveOrder(!isAscending)
                // 전체 할 일 정보를 가져와서 날짜순으로 내림차순 정렬
                val realmResult = realm.where<Todo>().findAll().sort("date", loadOrder())
                val adapter = TodoListAdapter(realmResult)
                listView.adapter = adapter
                true
            }
            R.id.menu_delete_all -> {
                realm.beginTransaction()
                realm.deleteAll()
                realm.commitTransaction()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveOrder(isAscending: Boolean) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putBoolean("KEY_ORDER", isAscending).apply()
    }

    private fun loadOrder(): Sort {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        return when (pref.getBoolean("KEY_ORDER", false)) {
            true -> {
                isAscending = true
                Sort.ASCENDING
            }
            false -> {
                isAscending = false
                Sort.DESCENDING
            }
        }
    }
}


