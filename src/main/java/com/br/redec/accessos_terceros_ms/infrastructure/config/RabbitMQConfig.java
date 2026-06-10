package com.br.redec.accessos_terceros_ms.infrastructure.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Classe criada para ensinar ao SpringBoot o fluxo que deve ser seguido do envio de mensagens
 */

@Configuration //le as configs da classe antes do spring inicializar
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "fila-acessos";
    public static final String EXCHANGE_NAME = "acessos-exchange";
    public static final String ROUTING_KEY = "acessos-routing-key";

  @Bean
  public Queue queue() {
      // O parâmetro 'true' significa que a fila é durável (não some se o RabbitMQ reiniciar)
      return new Queue(QUEUE_NAME, true);
  }

    @Bean
    public DirectExchange exchange() {
        //envia a mensagem para a fila que tiver a Routing Key
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        //faz a ligação entre a fila e o exchange, indicando a routing key
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

}
