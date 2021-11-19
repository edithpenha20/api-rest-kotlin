package com.forum.service

import com.forum.dto.AtualizacaoTopicoForm
import com.forum.dto.NovoTopicoForm
import com.forum.dto.TopicoPorCategoriaDto
import com.forum.dto.TopicoView
import com.forum.exception.NotFoundException
import com.forum.mapper.TopicoFormMapper
import com.forum.mapper.TopicoViewMapper
import com.forum.model.Topico
import com.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado."
) {

    fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoView> {
        val topicos = if(nomeCurso == null){
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map { t ->
            topicoViewMapper.map(t)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id)
            .orElseThrow {NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topicoMapper = topicoFormMapper.map(form)
        repository.save(topicoMapper)
        return topicoViewMapper.map(topicoMapper)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id)
            .orElseThrow {NotFoundException(notFoundMessage)}
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun buscarPorRelatorio(): List<TopicoPorCategoriaDto> {
        return repository.buscarPorRelatorio()
    }

}