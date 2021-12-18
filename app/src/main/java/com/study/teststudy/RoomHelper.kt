package com.study.teststudy

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Page::class),version=1, exportSchema = false)
abstract class RoomHelper:RoomDatabase() {
    abstract fun pageDAO():PageDAO
}