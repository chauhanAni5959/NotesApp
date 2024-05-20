package com.production.notessqllite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDatabaseHelper(context:Context):SQLiteOpenHelper(context, DATABASE_NAME, null,
    DATABASE_VERSION) {


    companion object{
        private const val DATABASE_NAME = "notesapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allnotes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"


    }

    override fun onCreate(db: SQLiteDatabase?) {
//        THIS IS FOR CREATING THE DATABSE
        val createTableQuery = "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_CONTENT TEXT)"
//        THIS IS FOR EXECUTING THE QUERY
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        THIS QUESRY IS FOR DROP THE TABLE
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
//        THIS IS FOR DROP THE TABLE EXECUTION
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }
//        THIS FUNCTION IS FOR INSERTING THE NOTE
    fun insertNote(note: Note){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, note.title)
            put(COLUMN_CONTENT, note.content)

        }
        db.insert(TABLE_NAME, null , values)
        db.close()

    }

//      This function is for View the All notes

    fun getAllNodes():List<Note>{
        val notesList = mutableListOf<Note>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while(cursor.moveToNext()){
//            Creating the variable of the id , title , content
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            val note = Note(id, title, content)

            notesList.add(note)

        }
        cursor.close()
        db.close()
        return notesList

    }
}