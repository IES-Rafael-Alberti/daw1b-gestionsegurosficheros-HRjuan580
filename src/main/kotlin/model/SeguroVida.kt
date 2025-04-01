import iesra.dawb.seguros.model.IExportable
import iesra.dawb.seguros.model.Riesgo

class SeguroVida private constructor(
    numPoliza: String,
    dniTitular: String,
    importe: Double,
    private val fechaNac: LocalDate,
    private val nivelRiesgo: Riesgo,
    private val indemnizacion: Double
) : Seguro(numPoliza, dniTitular, importe) {
    companion object {
        private var numPolizasVida: Int = 800000

        fun crearSeguro(datos: List<String>): SeguroVida? {
            return try {
                SeguroVida(
                    numPoliza = datos[0],
                    dniTitular = datos[1],
                    importe = datos[2].toDouble(),
                    fechaNac = LocalDate.parse(datos[3]),
                    nivelRiesgo = Riesgo.getRiesgo(datos[4]),
                    indemnizacion = datos[5].toDouble()
                )
            } catch (e: Exception) {
                null
            }
        }

        fun nuevoSeguro(
            dniTitular: String,
            importe: Double,
            fechaNac: LocalDate,
            nivelRiesgo: Riesgo,
            indemnizacion: Double
        ): SeguroVida {
            return SeguroVida(
                numPoliza = (++numPolizasVida).toString(),
                dniTitular = dniTitular,
                importe = importe,
                fechaNac = fechaNac,
                nivelRiesgo = nivelRiesgo,
                indemnizacion = indemnizacion
            )
        }
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val edad = Period.between(fechaNac, LocalDate.now()).years
        val incrementoEdad = edad * 0.0005
        val incrementoRiesgo = nivelRiesgo.interesAplicado / 100
        return importe * (1 + interes + incrementoEdad + incrementoRiesgo)
    }

    override fun toString(): String {
        return "${super.toString().dropLast(1)}, fechaNac=$fechaNac, nivelRiesgo=$nivelRiesgo, indemnizacion=$indemnizacion)"
    }
}