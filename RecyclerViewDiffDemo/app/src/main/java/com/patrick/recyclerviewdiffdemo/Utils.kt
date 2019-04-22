package com.patrick.recyclerviewdiffdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.*

// ImageView 의 load 확장 함수
fun ImageView.loadImage(image: Int) {
    Glide.with(context).load(image).into(this)
}

// viewGroup 의 inflate 확장 함수
fun ViewGroup.inflate(resource: Int): View {
    return LayoutInflater.from(context).inflate(resource, null, false)
}

// 셔플 기능
fun <E> List<E>.shuffle(): MutableList<E> {
    val list = this.toMutableList()
    Collections.shuffle(list)
    return list
}