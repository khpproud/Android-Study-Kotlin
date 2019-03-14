package com.patrick

import org.apache.commons.lang3.StringUtils

fun main() {
    val data = "  Hello World !! Park"
    println(StringUtils.deleteWhitespace(data))
}

