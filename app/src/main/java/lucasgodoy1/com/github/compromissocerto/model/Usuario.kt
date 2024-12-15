package lucasgodoy1.com.github.compromissocerto.model

class Usuario(var compromisso: String, var data: String, var hora: String) {
    var id: Int? = null
    var alarmeId: String? = null
    var dataTimeStamp: Long? = null



    override fun toString(): String {
        return "Usuario(compromisso='$compromisso', data='$data', hora='$hora', id=$id, alarmeId=$alarmeId, dataTimeStamp=$dataTimeStamp)"
    }


}