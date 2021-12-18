package com.study.teststudy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.Hold
import com.study.teststudy.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter(val pageList:List<Page>): RecyclerView.Adapter<Holder>() {
    var listData= mutableListOf<Page>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding=ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        val diary=listData.get(position)
        holder.setDiary(pageList.get(position))
    }

    override fun getItemCount(): Int {
        return listData.size
    }

}
class Holder(val binding: ItemRecyclerBinding):RecyclerView.ViewHolder(binding.root){
    fun setDiary(page:Page){
        with(binding){
//            ivImg.setImageBitmap() //이미지 가져오기
            tvDate.text=SimpleDateFormat("yyyy-MM-dd").format(page.date)
        }
    }
}