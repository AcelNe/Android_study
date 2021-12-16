package com.study.teststudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.study.teststudy.databinding.ActivityListBinding
import com.study.teststudy.databinding.ActivityMainBinding

class ListActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityListBinding.inflate(layoutInflater)
    }
    val helper=SqliteHelper(this,"diary",1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter=RecyclerAdapter()

        adapter.listData.addAll(helper.selectDairy())
        binding.recyclerList.adapter=adapter
        binding.recyclerList.layoutManager=GridLayoutManager(this,2)

        binding.btnRemember.setOnClickListener {
            CameraActivity()
        }
    }
}