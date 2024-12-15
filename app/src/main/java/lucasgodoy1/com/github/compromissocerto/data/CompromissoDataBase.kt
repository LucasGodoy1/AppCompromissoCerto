package lucasgodoy1.com.github.compromissocerto.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import lucasgodoy1.com.github.compromissocerto.model.Usuario
import lucasgodoy1.com.github.compromissocerto.util.TAG

class CompromissoDataBase(contexto: Context) :
    SQLiteOpenHelper(contexto, DB_NOME, null, VERSAO_ATUAL) {

    var cursor: Cursor? = null
    var db: SQLiteDatabase? = writableDatabase

    companion object {
        val DB_NOME = "CompromissoDB"
        val VERSAO = 1
        val VERSAO_ATUAL = 6

    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        db = sqLiteDatabase
        val query =
            "Create table tb_usuario (ID INTEGER PRIMARY KEY AUTOINCREMENT, COMPROMISSO TEXT, DATA TEXT, HORA TEXT, ALARME_ID TEXT)"
        sqLiteDatabase?.execSQL(query)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (VERSAO < VERSAO_ATUAL) {
            val alterTableQuery = "ALTER TABLE tb_usuario ADD COLUMN DATA_TIME_STAMP LONG"
            db?.execSQL(alterTableQuery)
        }
    }


    fun salvarDados(nomeTabela: String, contentValues: ContentValues) {
        db?.insert(nomeTabela, null, contentValues)
    }

    fun listaDados(): List<Usuario> {
        var lista = ArrayList<Usuario>()
        var querySQL = "SELECT * FROM tb_usuario"
        cursor = db?.rawQuery(querySQL, null)

        cursor?.use { cursor ->
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"))
                val compromisso = cursor.getString(cursor.getColumnIndexOrThrow("COMPROMISSO"))
                val data = cursor.getString(cursor.getColumnIndexOrThrow("DATA"))
                val hora = cursor.getString(cursor.getColumnIndexOrThrow("HORA"))
                val alarmeID = cursor.getString(cursor.getColumnIndexOrThrow("ALARME_ID")) ?: ""
                val dataTimeStamp = cursor.getString(cursor.getColumnIndexOrThrow("DATA_TIME_STAMP")) ?: "1111111111111"
                val usuario = Usuario(compromisso, data, hora)
                usuario.id = id
                usuario.alarmeId = alarmeID
                usuario.dataTimeStamp = dataTimeStamp.toLong()
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

    fun buscarDados(alarmeID: String): Usuario? {
        val querySQL = "SELECT * FROM tb_usuario WHERE ALARME_ID = ?"
        val cursor = db?.rawQuery(querySQL, arrayOf(alarmeID))

        cursor?.use { c ->
            if (c.moveToFirst()) {
                val id = c.getInt(c.getColumnIndexOrThrow("ID"))
                val compromisso = c.getString(c.getColumnIndexOrThrow("COMPROMISSO"))
                val data = c.getString(c.getColumnIndexOrThrow("DATA"))
                val hora = c.getString(c.getColumnIndexOrThrow("HORA"))
                val alarmeId = c.getString(c.getColumnIndexOrThrow("ALARME_ID"))

                return Usuario(compromisso, data, hora).apply {
                    this.id = id
                    this.alarmeId = alarmeId
                }
            }
        }
        Log.e(TAG, "ERRO! Select retornou VAZIO")
        return null
    }


}