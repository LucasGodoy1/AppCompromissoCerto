package lucasgodoy1.com.github.compromissocerto.datamodel

class UsuarioModelDB {

    companion object{
        val TABELA = "TB_COMPROMISSO"
        val ID = "ID"
        val COMPROMISSO = "COMPROMISSO"
        val DATA = "DATA"
        val HORA = "HORA"
        val ALARME_ID = "ALARME_ID"
        val DATA_TIME_STAMP = "DATA_TIME_STAMP"

        fun criarTabela(): String{
            var queryCriarTabela = "CREATE TABLE $TABELA "
            queryCriarTabela+= "($ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            queryCriarTabela+= " $COMPROMISSO TEXT,"
            queryCriarTabela+= " $DATA TEXT,"
            queryCriarTabela+= " $HORA TEXT,"
            queryCriarTabela+= " $ALARME_ID TEXT,"
            queryCriarTabela+= " $DATA_TIME_STAMP LONG)"

            return queryCriarTabela
        }

        fun selecionarUm() : String{
            return "SELECT * FROM $TABELA WHERE $ALARME_ID = ?"
        }

        fun selecionarTodos() : String{
            return "SELECT * FROM $TABELA"
        }




    }

}