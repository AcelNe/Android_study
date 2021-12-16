package com.study.teststudy

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.graphics.createBitmap
import java.util.logging.MemoryHandler

class SqliteHelper(context: Context, name:String, version: Int):
    SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val create="create table diary(" +
                "no integer primary key," +
                "img text," +
                "date integer," +
                "time integer," +
                "place text," +
                "feeling text," +
                "comment text)"
        //no, image, date, time, place, feeling, comment

        db?.execSQL(create)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    //insert함수
    fun insertDairy(diary: Diary){
        val values=ContentValues()
        values.put("img",diary.img)
        values.put("date",diary.date)
        values.put("time",diary.time)
        values.put("place",diary.place)
        values.put("feeling",diary.feeling)
        values.put("content",diary.comment)

        val wd=writableDatabase
        wd.insert("diary",null,values)
        wd.close()
    }

    //select함수
    fun selectDairy():MutableList<Diary>{
        val list=mutableListOf<Diary>()

        val select="select * from diary"
        val rd=readableDatabase
        val cursor=rd.rawQuery(select,null)

        while(cursor.moveToNext()){
            var index=cursor.getColumnIndex("no")
            val no = cursor.getLong(index)
            index=cursor.getColumnIndex("img")
            var img=cursor.getString(index)
            index=cursor.getColumnIndex("date")
            val date = cursor.getLong(index)
            index=cursor.getColumnIndex("time")
            val time = cursor.getLong(index)
            index=cursor.getColumnIndex("place")
            val place = cursor.getString(index)
            index=cursor.getColumnIndex("feeling")
            val feeling = cursor.getString(index)
            index=cursor.getColumnIndex("comment")
            val comment = cursor.getString(index)
//            val no=cursor.getLong(cursor.getColumnIndex("no"))//cursor-값이 -1이 될 수 있다:error
//            val content=cursor.getString(cursor.getColumnIndex("content"))
//            val datetime=cursor.getLong(cursor.getColumnIndex("datetime"))

            list.add(Diary(no, img, date, time, place, feeling, comment))
        }
        cursor.close()
        rd.close()

        return list
    }
    
    //update 함수
    fun updateDiary(diary: Diary){
        val values=ContentValues()
        values.put("img",diary.img)
        values.put("date",diary.date)
        values.put("time",diary.time)
        values.put("place",diary.place)
        values.put("feeling",diary.feeling)
        values.put("content",diary.comment)

        val wd=writableDatabase
        wd.update("diary",values, "no=${diary.no}",null)
        wd.close()
    }

    //delete함수
    fun deleteDiary(diary: Diary){
        val delete="delete from diary where no=${diary.no}"

        val wr=writableDatabase
        wr.execSQL(delete)
        wr.close()
    }
}

data class Diary(
    var no:Long?,
    var img:String,
    var date:Long,
    var time:Long,
    var place:String,
    var feeling:String,
    var comment: String)