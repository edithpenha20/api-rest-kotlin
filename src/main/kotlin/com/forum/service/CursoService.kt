package com.forum.service

import com.forum.model.Curso
import com.forum.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(private val repository: CursoRepository) {

    fun buscarPorId(id: Long): Curso{
        return repository.getById(id)
    }
}
