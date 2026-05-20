package com.br.redec.accessos_terceros_ms.infrastructure.adapter.in.web;

import lombok.Data;

@Data
public class SolicitacaoAcessoRequest {

    private String nome;
    private String documento;
    private String empresaOrigem;
    private String areaDestino;

}
