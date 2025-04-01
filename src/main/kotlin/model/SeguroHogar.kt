import iesra.dawb.seguros.model.Auto
import iesra.dawb.seguros.model.Cobertura


class SeguroHogar private constructor(
    numPoliza: String,
    dniTitular: String,
    importe: Double,
    private val metrosCuadrados: Int,
    private val valorContenido: Double,
    private val direccion: String,
    private val anioConstruccion: Int

) : Seguro(numPoliza, dniTitular, importe) {
    companion object {
        private var numPolizasHogar: Int = 100000
        private const val PORCENTAJE_INCREMENTO_ANIOS = 0.02
        private const val CICLO_ANIOS_INCREMENTO = 5

        fun crearSeguro(datos: List<String>): SeguroHogar? {
            return try {
                SeguroHogar(
                    numPoliza = datos[0],
                    dniTitular = datos[1],
                    importe = datos[2].toDouble(),
                    metrosCuadrados = datos[3].toInt(),
                    valorContenido = datos[4].toDouble(),
                    direccion = datos[5],
                    anioConstruccion = datos[6].toInt()
                )
            } catch (e: Exception) {
                null
            }
        }

        fun nuevoSeguro(
            dniTitular: String,
            importe: Double,
            metrosCuadrados: Int,
            valorContenido: Double,
            direccion: String,
            anioConstruccion: Int
        ): SeguroHogar {
            return SeguroHogar(
                numPoliza = (++numPolizasHogar).toString(),
                dniTitular = dniTitular,
                importe = importe,
                metrosCuadrados = metrosCuadrados,
                valorContenido = valorContenido,
                direccion = direccion,
                anioConstruccion = anioConstruccion
            )
        }
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val aniosAntiguedad = java.time.LocalDate.now().year - anioConstruccion
        val incrementoAdicional = (aniosAntiguedad / CICLO_ANIOS_INCREMENTO) * PORCENTAJE_INCREMENTO_ANIOS
        return importe * (1 + interes + incrementoAdicional)
    }

    override fun toString(): String {
        return "${super.toString().dropLast(1)}, metrosCuadrados=$metrosCuadrados, valorContenido=$valorContenido, direccion='$direccion', anioConstruccion=$anioConstruccion)"
    }
}