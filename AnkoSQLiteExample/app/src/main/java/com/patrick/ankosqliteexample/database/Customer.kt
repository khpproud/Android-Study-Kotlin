package com.patrick.ankosqliteexample.database

// Customer class
data class Customer(val id: Int, val name: String, val phoneNumber: String) {
    companion object {
        val TABLE_NAME = "customers"
        val COLUMN_ID = "id"
        val COLUMN_NAME = "name"
        val COLUMN_PHONE_NUM = "phone_num"
    }
}