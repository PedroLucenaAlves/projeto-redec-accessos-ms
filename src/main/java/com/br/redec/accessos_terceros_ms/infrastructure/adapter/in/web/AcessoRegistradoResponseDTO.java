package com.br.redec.accessos_terceros_ms.infrastructure.adapter.in.web;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class AcessoRegistradoResponseDTO {

    private UUID idSolicitacao;
    private String status;
    private LocalDateTime dataRegistro;

}
