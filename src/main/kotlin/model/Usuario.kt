package model

class Usuario(val nombre: String, val clave: Int, val perfil: String) {

    fun crearUsuario(datos: List<String>): Usuario {

    }

    fun cambiarClave(nuevaClaveEncriptada: String){

    }

    override serializar(separador: String): String{

    }
}