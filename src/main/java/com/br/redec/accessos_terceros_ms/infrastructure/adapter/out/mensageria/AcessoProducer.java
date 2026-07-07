package com.br.redec.accessos_terceros_ms.infrastructure.adapter.out.mensageria;

import com.br.redec.accessos_terceros_ms.infrastructure.adapter.out.mensageria.event.AcessoSolicitadoEvent;
import com.br.redec.accessos_terceros_ms.infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * O pacote 'out' representa a saída de dados do nosso microsserviço para o mundo externo.
 * 
 * Responsabilidade do Producer:
 * É o "mensageiro" (ou despachante). Ele não processa a regra de negócio pesada. Sua única função 
 * é pegar o Evento (AcessoSolicitadoEvent, gerado pelo Controller) e enviar para o RabbitMQ.
 * 
 * Analogia dos Correios:
 * O Producer é a ação de postar a carta. Ele entrega o envelope (Evento) na 
 * central de triagem (Exchange) apontando a etiqueta de destino (Routing Key).
 * 
 * Por que usar o Producer?
 * Para que a API seja rápida. Ele "despacha e esquece" (Fire-and-Forget), permitindo que o 
 * Controller responda ao Postman imediatamente enquanto o processamento real fica pra depois.
 */

@Component //component sao classes gerenciadas no contexto da aplicacao do Spring
public class AcessoProducer {

    private final RabbitTemplate rabbitTemplate;

    public AcessoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //Método responsável por publicar a mensagem na fila (Exchange) do RabbitMQ
    public void enviarParaFila(AcessoSolicitadoEvent evento) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME, //envia uma mensangem para a exchange usando uma routing key
                RabbitMQConfig.ROUTING_KEY,
                evento
        );

        System.out.println("[PRODUCER] Enviando para fila o evento da solicitação: " + evento.idSolicitacao());

    }
}
