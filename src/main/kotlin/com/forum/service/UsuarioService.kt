package com.forum.service

import com.forum.model.Usuario
import com.forum.repository.UsuarioRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(private val repository: UsuarioRepository) {

    fun buscarPorId(id: Long): Usuario {
        return repository.getById(id)
    }
}