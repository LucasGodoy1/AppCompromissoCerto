package lucasgodoy1.com.github.compromissocerto.data

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CompromissoDataBase(contexto : Context) : SQLiteOpenHelper(contexto, DB_NOME, null, VERSAO){

    lateinit var cursor : Cursor
    var db : SQLiteDatabase = writableDatabase

    companion object{
        val DB_NOME = "tb_compromisso"
        val VERSAO = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}