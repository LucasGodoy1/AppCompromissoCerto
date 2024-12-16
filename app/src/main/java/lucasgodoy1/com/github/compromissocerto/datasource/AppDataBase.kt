package lucasgodoy1.com.github.compromissocerto.datasource

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import lucasgodoy1.com.github.compromissocerto.datamodel.UsuarioModelDB
import lucasgodoy1.com.github.compromissocerto.model.Usuario

class AppDataBase(context: Context) : SQLiteOpenHelper(context, DB_NOME,null, VERSAO){

    private val TAG = "LOG_AppDB"
    var db : SQLiteDatabase? = null
    var cursor: Cursor? = null
    var compromissosList = ArrayList<Usuario>()



    companion object {
        val DB_NOME = "DB_APP_COMPROMISSO"
        val VERSAO = 1
    }

    init {
        if (context.getDatabasePath("CompromissoDB").exists()) {
            Log.w(TAG, "Excluindo DB desatualizado")
            context.deleteDatabase("CompromissoDB")
            Log.w(TAG, "Banco de dados excluído com sucesso.")
        }


        db = writableDatabase
        Log.i(TAG, "DB inicializado")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(UsuarioModelDB.criarTabela())
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertDados(contentValues: ContentValues) {
        db?.insert(UsuarioModelDB.TABELA, null, contentValues)
    }

    fun listaDados(): List<Usuario> {
        cursor = db?.rawQuery(UsuarioModelDB.selecionarTodos(), null)
        cursor?.use { cursor ->
            while (cursor.moveToNext()) {
                val compromisso = cursor.getString(cursor.getColumnIndexOrThrow("COMPROMISSO"))
                val data = cursor.getString(cursor.getColumnIndexOrThrow("DATA"))
                val hora = cursor.getString(cursor.getColumnIndexOrThrow("HORA"))
                val usuario = Usuario(compromisso, data, hora)
                usuario.id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"))
                usuario.alarmeId = cursor.getString(cursor.getColumnIndexOrThrow("ALARME_ID"))
                usuario.dataTimeStamp = cursor.getString(cursor.getColumnIndexOrThrow("DATA_TIME_STAMP")).toLong()
                compromissosList.add(usuario)
            }
        }
        return compromissosList
    }

    fun atualizarDados(id: Int, contentValues: ContentValues): Int {
        val whereClause = "ID = ?"
        val whereArgs = arrayOf(id.toString())
        return db?.update(UsuarioModelDB.TABELA, contentValues, whereClause, whereArgs) ?: -1
    }

    fun deletarUsuario(id: Int?): Int {
        val whereClause = "ID = ?"
        val whereArgs = arrayOf(id.toString())
        return db?.delete(UsuarioModelDB.TABELA, whereClause, whereArgs) ?: -1
    }

    fun buscarDados(alarmeID: String): Usuario? {
        val cursor = db?.rawQuery(UsuarioModelDB.selecionarUm(), arrayOf(alarmeID))

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