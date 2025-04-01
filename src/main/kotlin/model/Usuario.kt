package model

import iesra.dawb.seguros.model.IExportable
import iesra.dawb.seguros.model.Perfil

data class Usuario(
    val id: String,
    val nombre: String,
    val clave: String,
    val perfil: Perfil
): IExportable {
    companion object {
        fun crearUsuario(datos: List<String>): Usuario? {
            return try {
                Usuario(
                    nombre = datos[0],
                    clave = datos[1],
                    perfil = Perfil.getPerfil(datos[2])
            } catch (e: Exception) {
                null
            }
        }
    }
    fun cambiarClave(nuevaClaveEncriptada: String){
        clave = nuevaClaveEncriptada
    }
    override fun serializar(separodor: String): String{
        return listOf(id, nombre, clave, perfil.name()).joinToString(separodor)
    }
}