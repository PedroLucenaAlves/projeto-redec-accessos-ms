package com.br.redec.accessos_terceros_ms.domain.model;

public class Acesso {

    private String id;

    private String nome;

    private String documento;

    private String status;

    private String empresaOrigem;

    private String areaDestino;

    public Acesso() {
    }

    public Acesso(String id, String nome, String documento, String status, String empresaOrigem, String areaDestino) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.status = status;
        this.empresaOrigem = empresaOrigem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmpresaOrigem() {
        return empresaOrigem;
    }

    public void setEmpresaOrigem(String empresaOrigem) {
        this.empresaOrigem = empresaOrigem;
    }

    public String getAreaDestino() {
        return areaDestino;
    }

    public void setAreaDestino(String areaDestino) {
        this.areaDestino = areaDestino;
    }
}
