package com.study.teststudy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.study.teststudy.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityListBinding.inflate(layoutInflater)
    }
    lateinit var helper:RoomHelper
    lateinit var recyclerAdapter:RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        helper= Room.databaseBuilder(this,RoomHelper::class.java,"diary").allowMainThreadQueries().build()

        val list=helper.pageDAO().getAll()
        recyclerAdapter= RecyclerAdapter(list)

        with(binding){
            recyclerList.adapter=recyclerAdapter
            recyclerList.layoutManager= GridLayoutManager(this@ListActivity,2)
        }
    }
}