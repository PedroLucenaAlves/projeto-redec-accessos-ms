package com.br.redec.accessos_terceros_ms.infrastructure.adapter.out.mensageria.event;

/**
 * Representa o evento que será enviado para a fila (RabbitMQ) notificando que um acesso foi solicitado.
 * 
 * Por que esta classe é separada da resposta da API (ResponseDTO)?
 * - O ResponseDTO é o "recibo" do cliente (Postman): Tem apenas o ID e o status da solicitação.
 * - Este Evento é a "ordem de serviço" (cozinha): Contém os dados de negócio reais (nome, documento) 
 *   que os consumidores da fila precisam para efetivamente processar o pedido.
 * 
 * Separar os dois evita acoplamento: podemos mudar a resposta visual da API para o usuário
 * sem quebrar o formato de dados esperado pelo resto do sistema (consumidores da fila).
 */

public record AcessoSolicitadoEvent(
        String idSolicitacao,
        String nomeUsuario,
        String documento,
        String empresaOrigem
) {}
