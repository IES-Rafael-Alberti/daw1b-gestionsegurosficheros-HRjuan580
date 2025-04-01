class SeguroAuto private constructor(
    numPoliza: String,
    dniTitular: String,
    importe: Double,
    private val descripcion: String,
    private val combustible: String,
    private val tipoAuto: Auto,
    private val cobertura: Cobertura,
    private val asistenciaCarretera: Boolean,
    private val numPartes: Int
) : Seguro(numPoliza, dniTitular, importe) {
    companion object {
        private var numPolizasAuto: Int = 400000
        private const val PORCENTAJE_INCREMENTO_PARTES = 2.0

        fun crearSeguro(datos: List<String>): SeguroAuto? {
            return try {
                SeguroAuto(
                    numPoliza = datos[0],
                    dniTitular = datos[1],
                    importe = datos[2].toDouble(),
                    descripcion = datos[3],
                    combustible = datos[4],
                    tipoAuto = Auto.getAuto(datos[5]),
                    cobertura = Cobertura.getCobertura(datos[6]),
                    asistenciaCarretera = datos[7].toBoolean(),
                    numPartes = datos[8].toInt()
                )
            } catch (e: Exception) {
                null
            }
        }

        fun nuevoSeguro(
            dniTitular: String,
            importe: Double,
            descripcion: String,
            combustible: String,
            tipoAuto: Auto,
            cobertura: Cobertura,
            asistenciaCarretera: Boolean,
            numPartes: Int
        ): SeguroAuto {
            return SeguroAuto(
                numPoliza = (++numPolizasAuto).toString(),
                dniTitular = dniTitular,
                importe = importe,
                descripcion = descripcion,
                combustible = combustible,
                tipoAuto = tipoAuto,
                cobertura = cobertura,
                asistenciaCarretera = asistenciaCarretera,
                numPartes = numPartes
            )
        }
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val incrementoPartes = numPartes * PORCENTAJE_INCREMENTO_PARTES / 100
        return importe * (1 + interes + incrementoPartes)
    }

    override fun toString(): String {
        return "${super.toString().dropLast(1)}, descripcion='$descripcion', combustible='$combustible', tipoAuto=$tipoAuto, cobertura=$cobertura, asistenciaCarretera=$asistenciaCarretera, numPartes=$numPartes)"
    }
}