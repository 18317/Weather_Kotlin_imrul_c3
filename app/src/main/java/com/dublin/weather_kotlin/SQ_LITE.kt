package com.dublin.weather_kotlin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//(MD. IMRUL MAHAMUD
//STUDENT ID:18317)
class SQ_LITE(context: Context?) :
    SQLiteOpenHelper(context, Db_name, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        // Create table query
        db.execSQL(
            "Create table " + Table_name + "("
                    + Id + " integer primary key autoincrement ,"
                    + Area + " text ,"
                    + Level + " text ,"
                    + Type + " text )"
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase, oldVersion: Int, newVersion: Int
    )
    {

    }
    // Insert funtion
    fun INSERT_NEW_USER_DATA(area: String?, level: String?, type: String?): Boolean
    {
        val sqLiteDatabase = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Area, area)
        contentValues.put(Level, level)
        contentValues.put(Type, type)
        val res = sqLiteDatabase.insert(Table_name, null, contentValues)
        //Toast.makeText(C, res +"   signup", Toast.LENGTH_SHORT).show();
        return if (res == -1L) { false } else { true }
    }

    // Get query to get my adding warning from the sqlite database
    fun GET_WARNING(): Cursor {
        val sqLiteDatabase = writableDatabase
        return sqLiteDatabase.rawQuery("select * from "+Table_name, null)
    }
    // to delete the add warning by me
    fun delete_ALL(id: String): Int {
        val db = writableDatabase
        return db.delete(Table_name, Id + "=" + id, null)
    }

    fun update_all(
        id: String,
        area: String?,
        level: String?,
        type: String?
    ): Int {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(area, area)
        cv.put(type, type)
        cv.put(level, level)
        return db.update(Table_name, cv, id + "=" + id, null)
    }
    // object which are comes up with part of database
    companion object {
        protected const val Db_name = "warning.db"
        const val Table_name = "warn_table"
        const val Id = "id"
        const val Area = "areea"
        const val Level = "levele"
        const val Type = "typee"
    }
}