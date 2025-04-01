import iesra.dawb.seguros.model.IExportable
import javax.swing.JPopupMenu.Separator

abstract class Seguro(
    val numPoliza: String,
    private val dniTitular: String,
    protected  val importe: Double
): IExportable{
    abstract fun calcularImporteAnioSiguiente(interes: Double): Double

    override fun serializar(separator : Separator): String{
        return listOf(numPoliza, dniTitular, "%.2f".format(importe)).joinToString(separator)
    }

    override fun toString() : String {
        return "${this::class.simpleName ?: "Seguro"}(numPoliza=$numPoliza, dniTitular=$dniTitular, importe=${"%.2f".format(importe)})"
    }

    override fun hasdCode(): Int = numPoliza.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Seguro) return false
        return numPoliza == other.numPoliza
    }
    fun tipoSeguro(): String = this:: class.simpleName ?: "Desconocido"
}