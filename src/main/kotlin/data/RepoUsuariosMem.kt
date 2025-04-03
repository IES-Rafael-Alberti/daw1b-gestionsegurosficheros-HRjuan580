package iesra.dawb.seguros.data

import model.Usuario

class RepoUsuariosMem: IRepoUsuarios {
    private val usuarios = mutableListOf<Usuario>()

    override fun agregar(usuario : Usuario) : Boolean {
        if (buscar(usuario.nombre) != null) return false
        return usuarios.add(usuario)
    }

    override fun buscar(nombreUsuario : String) : Usuario? {
        return usuarios.find {it.nombre == nombreUsuario}
    }

    override fun eliminar(usuario : Usuario : String) : Boolean {
        return usuarios.remove(usuario)
    }

    override fun eliminar(nombreUsuario : String): Boolean {
        val usuario = buscar(nombreUsuario)
        return usuario?.let { eliminar(it) }?: false
    }

    override fun obtenerTodos() : List<Usuario> {
        return usuarios.toList()
    }

    override fun obtener(perfil : Perfil) : List<Usuario> {
        return usuarios.find { it.perfil == perfil }
    }

    override fun cambiarClave(usuario : Usuario , nuevaClave : String) : Boolean {
        usuario.cambiarClave(nuevaClave)
        return false
    }
}