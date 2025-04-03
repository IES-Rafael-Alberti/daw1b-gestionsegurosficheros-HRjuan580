package iesra.dawb.seguros.data

import model.Usuario

class RepoUsuariosFich(
    private val rutaArchivo: String,
    private val fich: IUtilFicheros
) : RepoUsuariosMem(), ICargarUsuariosIniciales, IRepoUsuarios {

    override fun agregar(usuario: Usuario): Boolean {
        if (buscar(usuario.nombre) != null) return false
        return if (fich.agregarLinea(rutaArchivo, usuario.serializar())) {
            super.agregar(usuario)
        } else false
    }

    override fun eliminar(usuario: Usuario): Boolean {
        val usuariosActualizados = usuarios.filter { it != usuario }
        return if (fich.escribirArchivo(rutaArchivo, usuariosActualizados)) {
            super.eliminar(usuario)
        } else false
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        usuario.cambiarClave(nuevaClave)
        val usuariosActualizados = usuarios.map {
            if (it.nombre == usuario.nombre) usuario else it
        }
        return fich.escribirArchivo(rutaArchivo, usuariosActualizados) && super.cambiarClave(usuario, nuevaClave)
    }

    override fun cargarUsuarios(): Boolean {
        val lineas = fich.leerArchivo(rutaArchivo)
        lineas.forEach { linea ->
            val datos = linea.split(";")
            Usuario.crearUsuario(datos)?.let { super.agregar(it) }
        }
        return true
    }
}