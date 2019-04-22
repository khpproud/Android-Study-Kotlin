package com.patrick.recyclerviewdiffdemo

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.util.DiffUtil
import android.widget.ImageView
import android.widget.TextView
import kotlin.properties.Delegates

/**
 * RecyclerView를 관리할 AndroidFlavour Adapter 클래스
 */
class AndroidFlavourAdapter: RecyclerView.Adapter<AndroidFlavourAdapter.FlavourViewHolder>() {
    // 디저트 리스트 자료구조
    var flavourItems: List<AndroidFlavours> by Delegates.observable(emptyList()) {
        _, oldValue, newValue ->
        // 리스트의 목록이 변경되면 호출
        notifyChanges(oldValue, newValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FlavourViewHolder {
        //val layout: View = LayoutInflater.from(parent.context).inflate(R.layout.flavour_item, parent,false)
        // Util.kt에 확장함수로 정의
        return FlavourViewHolder(parent.inflate(R.layout.flavour_item))
    }

    override fun getItemCount(): Int = flavourItems.size

    override fun onBindViewHolder(holder: FlavourViewHolder, position: Int) {
        holder.name.text = flavourItems.get(holder.adapterPosition).name
        holder.image.loadImage(flavourItems.get(holder.adapterPosition).image)
    }

    // 뷰 홀더 클래스
    inner class FlavourViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.textView)
        var image: ImageView = view.findViewById(R.id.imageView)
    }

    // 어댑터의 리스트 변경시 호출됨
    // 만약 리스트의 사이즈가 너무 큰 경우 두 리스트간의 diff를 처리하는데 비용이 커지므로 백그라운드 스레드에서 계산해야됨
    private fun notifyChanges(oldValue: List<AndroidFlavours>, newValue: List<AndroidFlavours>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
                val oldFlavour = oldValue.get(oldItemPosition)
                val newFlavour = newValue.get(newItemPosition)
                val bundle = Bundle()
                if (!oldFlavour.name.equals(newFlavour.name)) {
                    bundle.putString("name", newFlavour.name)
                }
                if (!oldFlavour.image.equals(newFlavour.image)) {
                    bundle.putInt("image", newFlavour.image)
                }
                if (bundle.size() == 0)
                    return null
                return bundle
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldValue.get(oldItemPosition) == newValue.get(newItemPosition)
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldValue.get(oldItemPosition).name.equals(newValue.get(newItemPosition).name) &&
                        oldValue.get(oldItemPosition).image.equals(newValue.get(newItemPosition).image)
            }

            override fun getOldListSize(): Int = oldValue.size

            override fun getNewListSize(): Int = newValue.size
        })
        // diff 계산후에 DiffUtils 객체가 변경 내용을 Adapter에 전달하기 위해 호출
        // payload의 변경사항은 Adapter의 notifyItemRangeChanged 로 호출됨
        diff.dispatchUpdatesTo(this)
    }

    // payload의 데이터 변경 사항 업데이트를 위해 오버라이드
    override fun onBindViewHolder(holder: FlavourViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            return onBindViewHolder(holder, position)
        else {
            val o = payloads[0] as Bundle
            for (key in o.keySet()) {
                if (key == "name")
                    holder.name.text = o.getString("name")
                else if (key == "image")
                    holder.image.loadImage(o.getInt("image"))
            }
        }
    }
}