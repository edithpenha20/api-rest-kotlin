package com.forum.service

import com.forum.dto.AtualizacaoTopicoForm
import com.forum.dto.NovoTopicoForm
import com.forum.dto.TopicoView
import com.forum.exception.NotFoundException
import com.forum.mapper.TopicoFormMapper
import com.forum.mapper.TopicoViewMapper
import com.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado."
) {

    fun listar(): List<TopicoView> {
        return topicos.stream().map { t -> topicoViewMapper.map(t) }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow {NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topicoMapper = topicoFormMapper.map(form)
        topicoMapper.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topicoMapper)
        return topicoViewMapper.map(topicoMapper)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = topicos.stream().filter { t ->
            t.id == form.id
        }.findFirst().orElseThrow {NotFoundException(notFoundMessage)}
        val topicoAtualizado = Topico(
            id = form.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )
        topicos = topicos.minus(topico).plus(topicoAtualizado)
        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow {NotFoundException(notFoundMessage)}
        topicos = topicos.minus(topico)
    }

}