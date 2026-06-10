package com.br.redec.accessos_terceros_ms.infrastructure.adapter.out.mensageria;

import com.br.redec.accessos_terceros_ms.infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * O Producer é quem envia uma mensagem para o RabbitMQ.
 * Ele é semelhante a uma carta colocada no correio, onde posteriormente
 * o responsavel que pegar a carta pode fazer o que precisa com ela
*/

@Component
public class AcessoProducer {

    private final RabbitTemplate rabbitTemplate;

    public AcessoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //Método responsável por publicar a mensagem na fila (Exchange) do RabbitMQ
    public void enviarParaFila(String mensagem) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                mensagem
        );

        System.out.println("Enviando uma fila para " + mensagem);

    }
}
