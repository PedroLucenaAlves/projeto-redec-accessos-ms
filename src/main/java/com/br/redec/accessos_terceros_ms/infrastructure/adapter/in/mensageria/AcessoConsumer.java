package com.br.redec.accessos_terceros_ms.infrastructure.adapter.in.mensageria;

import com.br.redec.accessos_terceros_ms.infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AcessoConsumer {

    /**
     * O Spring fica "escutando" essa fila.
     * Quando chegar uma mensagem, ele chama esse método (executado automaticamente)
     * e passa a mensagem como parâmetro.
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void processarMensagem(String mensagem) {
        System.out.println("[CONSUMER] Nova mensagem recebida da fila: " + mensagem);
        System.out.println("[CONSUMER] Iniciando processamento do acesso no banco de dados...");

        //simluando tempo de processamento de 2 segundos
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[CONSUMER] Processamento concluído com sucesso.");
        dispararWebhookParceiro();
    }

    private void dispararWebhookParceiro() {
        // Simulação de um Webhook: a nossa API avisando o mundo externo que terminou
        System.out.println("[WEBHOOK] Disparando requisição HTTP POST para o sistema parceiro: 'Status atualizado para CONCLUÍDO!'");
    }

}
