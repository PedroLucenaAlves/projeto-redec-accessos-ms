package com.br.redec.accessos_terceros_ms.infrastructure.adapter.in.web;

import com.br.redec.accessos_terceros_ms.infrastructure.adapter.out.mensageria.AcessoProducer;
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

    // TODO: Aqui chamaremos a interface (Porta de Entrada) do nosso Domínio

    private final AcessoProducer acessoProducer;

    public AcessoController(AcessoProducer acessoProducer) {
        this.acessoProducer = acessoProducer;
    }

    @PostMapping
    public ResponseEntity<AcessoRegistradoResponseDTO> solicitarAcesso(@RequestBody SolicitacaoAcessoRequest request) {
        UUID idSolicitacao = UUID.randomUUID();

        //UUID cria um identificador único para rastrear essa solicitação de acesso
        AcessoRegistradoResponseDTO response = AcessoRegistradoResponseDTO.builder()
                .idSolicitacao(idSolicitacao)
                .status("PROCESSANDO")
                .dataRegistro(LocalDateTime.now())
                .build();

        //monta a resposta e envia para a fila de mensagens
        acessoProducer.enviarParaFila("Nova solicitação de acesso recebida. ID: " + idSolicitacao
                + ", nome: " + request.getNome()
                + ", documento: " + request.getDocumento()
                + ", empresa origem: " + request.getEmpresaOrigem()
                + ", área destino: " + request.getAreaDestino());

        //devolve o status http
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
