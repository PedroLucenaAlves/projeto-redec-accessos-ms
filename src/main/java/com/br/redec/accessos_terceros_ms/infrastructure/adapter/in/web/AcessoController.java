package com.br.redec.accessos_terceros_ms.infrastructure.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/acessos")
public class AcessoController {

    // TODO: Aqui chamaremos a interface (Porta de Entrada) do nosso Domínio

    @PostMapping
    public ResponseEntity<AcessoRegistradoResponseDTO> solicitarAcesso(SolicitacaoAcessoRequest request) {
        AcessoRegistradoResponseDTO response = AcessoRegistradoResponseDTO.builder()
                .idSolicitacao(java.util.UUID.randomUUID())
                .status("PROCESSANDO")
                .dataRegistro(java.time.LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
