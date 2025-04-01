package iesra.dawb.seguros.model

enum class Auto(val: String) {
    COCHE("coche"),
    MOTO("moto"),
    CAMION("camion");

    companion object {
        fun getAuto(valor: String): Auto {
            return values().firstOrNull() { it.valor.equals(valor, ignoreCase = true)} ?: COCHE
        }
    }
}