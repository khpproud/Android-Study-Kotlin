package com.patrick.slidegallery

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class SlidePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    // ViewPager가 표시할 Fragment 목록
    private val items = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    override fun getCount(): Int {
        return items.size
    }

    // 아이템 갱신
    fun updateFragments(items: List<Fragment>) {
        this.items.addAll(items)
    }
}