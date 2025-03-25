package iesra.dawb.seguros.model

enum class Perfil(val valor: String) {
    ADMIN("admin"),
    GESTION("gestion"),
    CONSULTA("consulta");

    companion object {
        fun getPerfil(valor: String): Perfil {
            return values().firstOrNull { it.valor.equals(valor, ignoreCase = true) } ?: CONSULTA
        }
    }
}