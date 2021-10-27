package com.example.notesappfull

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper ( context: Context) : SQLiteOpenHelper(context, "details.db", null, 1) {

    var sqLiteDatabase : SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("create table Notes( Masseg text ) ")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun save (  s1:String ): Long {

        val cv = ContentValues()
        cv.put("Masseg" , s1)

        var status = sqLiteDatabase.insert("Notes",null,cv)

        return status

    }


    @SuppressLint("Range")
    fun Retrieve(s1:String):String {

        var c : Cursor = sqLiteDatabase.query("Notes", null ,"Masseg=?", arrayOf(s1),
            null,null,null)

        c.moveToFirst()
        var note = c.getString(c.getColumnIndex("Masseg"))
        return note
    }

    @SuppressLint("Range")
    fun retriveData():ArrayList<String>{
        var al = arrayListOf<String>()
        var c : Cursor = sqLiteDatabase.query("Notes",null,null, null,null,null,null)
        if (c.moveToFirst()) {
            do {
                al.add(c.getString(c.getColumnIndex("Masseg")));
            } while (c.moveToNext());
        }
        return al
    }

    fun update(s1: String, S2: String): Int {
        sqLiteDatabase=writableDatabase
        val cv = ContentValues()
        cv.put("Masseg" , s1)

        var j = sqLiteDatabase.update("Notes", cv, "Masseg=?", arrayOf(S2))
        return j
    }

    fun delete(s1: String) {
        sqLiteDatabase=writableDatabase
        sqLiteDatabase.delete("Notes", "Masseg=?",  arrayOf(s1))
    }


}