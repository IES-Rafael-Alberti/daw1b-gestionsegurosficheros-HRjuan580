package iesra.dawb.seguros.model

interface IExportable {
    fun serializar(separador: String = ";"): String
}