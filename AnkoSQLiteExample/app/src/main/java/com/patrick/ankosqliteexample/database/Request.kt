package com.patrick.ankosqliteexample.database

data class Request(val id: Int, val name: String, val message: String) {
    companion object {
        val TABLE_NAME = "Requests"
        val COLUMN_NAME = "name"
        val COLUMN_MESSAGE = "message"
    }
}