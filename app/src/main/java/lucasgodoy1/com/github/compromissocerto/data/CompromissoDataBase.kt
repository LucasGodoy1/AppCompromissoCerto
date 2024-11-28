package lucasgodoy1.com.github.compromissocerto.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import lucasgodoy1.com.github.compromissocerto.model.Usuario

class CompromissoDataBase(contexto : Context) : SQLiteOpenHelper(contexto, DB_NOME, null, VERSAO){

    var cursor : Cursor? = null
    var db : SQLiteDatabase? = writableDatabase

    companion object{
        val DB_NOME = "CompromissoDB"
        val VERSAO = 1
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        db = sqLiteDatabase
        val query = "Create table tb_usuario (ID INTEGER PRIMARY KEY AUTOINCREMENT, COMPROMISSO TEXT, DATA TEXT, HORA TEXT)"
        sqLiteDatabase?.execSQL(query)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    fun salvarDados(nomeTabela : String, contentValues: ContentValues){
        db?.insert(nomeTabela, null,  contentValues)
    }

    fun listaDados() : List<Usuario>{
        var lista = ArrayList<Usuario>()
        var querySQL = "SELECT * FROM tb_usuario"
        cursor = db?.rawQuery(querySQL, null)

        cursor?.use { cursor ->
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"))
                val compromisso = cursor.getString(cursor.getColumnIndexOrThrow("COMPROMISSO"))
                val data = cursor.getString(cursor.getColumnIndexOrThrow("DATA"))
                val hora = cursor.getString(cursor.getColumnIndexOrThrow("HORA"))
                val usuario = Usuario(compromisso, data, hora)
                usuario.id = id

                lista.add(usuario)
            }
        }
        return lista
    }

    fun atualizarDados(id: Int, contentValues: ContentValues): Int {
        val whereClause = "ID = ?"
        val whereArgs = arrayOf(id.toString())
        return db?.update("tb_usuario", contentValues, whereClause, whereArgs) ?: -1
    }

    fun deletarUsuario(id: Int?): Int {
        val whereClause = "ID = ?"
        val whereArgs = arrayOf(id.toString())
        return db?.delete("tb_usuario", whereClause, whereArgs) ?: -1
    }






}