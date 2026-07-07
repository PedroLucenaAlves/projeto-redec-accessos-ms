package com.br.redec.accessos_terceros_ms.infrastructure.adapter.in.web;

import com.br.redec.accessos_terceros_ms.infrastructure.adapter.out.mensageria.AcessoProducer;
import com.br.redec.accessos_terceros_ms.infrastructure.adapter.out.mensageria.event.AcessoSolicitadoEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/v1/acessos")
public class AcessoController {

    private final AcessoProducer acessoProducer;

    public AcessoController(AcessoProducer acessoProducer) {
        this.acessoProducer = acessoProducer;
    }

    @PostMapping
    public ResponseEntity<AcessoRegistradoResponseDTO> solicitarAcesso(@RequestBody SolicitacaoAcessoRequest request) {
        UUID idSolicitacao = UUID.randomUUID();

        //monta a resposta para o postman
        AcessoRegistradoResponseDTO response = AcessoRegistradoResponseDTO.builder()
                .idSolicitacao(idSolicitacao)
                .status("PROCESSANDO")
                .dataRegistro(LocalDateTime.now())
                .build();

         //criando evento para o rabbitmq (necessário para processar o pedido)
        AcessoSolicitadoEvent evento = new AcessoSolicitadoEvent(
                idSolicitacao.toString(),
                request.getNome(),
                request.getDocumento(),
                request.getEmpresaOrigem()
        );

        //envia o objeto para a fila
        acessoProducer.enviarParaFila(evento);

        //devolve o status http
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
