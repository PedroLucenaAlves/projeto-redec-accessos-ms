package com.br.redec.accessos_terceros_ms.infrastructure.adapter.out.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A Entidade (Entity) representa o estado oficial da aplicação no Banco de Dados (O "Prontuário Médico").
 * 
 * Diferença Prática (Arquitetura Hexagonal / Clean Architecture):
 * - DTO (Recepção): O que entra/sai da API. Focado estritamente na tela/usuário externo.
 * - Event (Rádio/Pager): O aviso para a fila. Focado nos dados mínimos para o worker trabalhar.
 * - Entity (Prontuário): TUDO que o negócio precisa lembrar a longo prazo. Mistura dados do usuário 
 *   com dados de controle interno do sistema (ID, status, e futuramente datas de criação/atualização).
 * 
 * Regra de Ouro: O banco de dados (Entity) não deve ditar o formato da API (DTO), 
 * e a API não deve ditar como o banco salva. Eles evoluem de forma independente.
 * 
 * OBS Técnica: Evitamos usar @Data do Lombok em Entidades porque os métodos equals()/hashCode() 
 * gerados por ele podem causar problemas de performance e bugs (recursividade) com o Hibernate.
 */

@Entity
@Table(name = "acessos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcessoEntity {

    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "documento", nullable = false, length = 20)
    private String documento;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "empresa_origem", nullable = false, length = 50)
    private String empresaOrigem;

    @Column(name = "area_destino", nullable = false, length = 50)
    private String areaDestino;


}
