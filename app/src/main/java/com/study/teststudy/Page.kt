package com.study.teststudy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URI
import java.util.*

@Entity(tableName = "diary_page")
class Page {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var no:Long?=null
    @ColumnInfo
    var img: URI?=null
    @ColumnInfo
    var date:Long=0
    @ColumnInfo
    var time:Long=0
    @ColumnInfo
    var place:String=""
    @ColumnInfo
    var feeling:String=""
    @ColumnInfo
    var comment:String=""

    constructor(img:URI, date: Long, feeling:String){
        this.img=img
        this.date=date
        this.feeling=feeling
    }
}