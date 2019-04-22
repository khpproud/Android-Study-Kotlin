package com.patrick.ankosqliteexample.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "SupportDatabase", null, 1) {
    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable("Requests", true,
            "id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            "name" to TEXT,
            "message" to TEXT)

        // customer table 생성
        db.createTable(Customer.TABLE_NAME, true,
            Customer.COLUMN_ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Customer.COLUMN_NAME to TEXT,
            Customer.COLUMN_PHONE_NUM to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable("Requests", true)
        db.dropTable(Customer.TABLE_NAME, true)
    }

}