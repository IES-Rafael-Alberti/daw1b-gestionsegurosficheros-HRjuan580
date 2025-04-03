package iesra.dawb.seguros.data

interface ICargarSegurosIniciales {
    fun cargarSeguros(mapa: Map<String, (List<String>) -> Seguro>): Boolean
}