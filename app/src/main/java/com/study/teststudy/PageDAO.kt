package com.study.teststudy

import androidx.room.*

@Dao
interface PageDAO {
    @Query("select * from diary_page")
    fun getAll():List<Page>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetr(page:Page)

    @Delete
    fun delete(page:Page)
}