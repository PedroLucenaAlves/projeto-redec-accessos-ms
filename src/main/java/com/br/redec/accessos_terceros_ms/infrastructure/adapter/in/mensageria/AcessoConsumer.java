package com.br.redec.accessos_terceros_ms.infrastructure.adapter.in.mensageria;

import com.br.redec.accessos_terceros_ms.infrastructure.adapter.out.mensageria.event.AcessoSolicitadoEvent;
import com.br.redec.accessos_terceros_ms.infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * As classes do pacote in recebem dados de fora para dentro
 */

@Component
public class AcessoConsumer {

    /**
     * O Consumer é o "trabalhador/cozinheiro" nos bastidores.
     * 
     * Fluxo (Analogia dos Correios):
     * 1. Producer: Posta a carta no centro de distribuição (Exchange) com um CEP (Routing Key).
     * 2. Mensageria (Binding/Config): Lê o CEP e joga na caixa de correio certa (Fila/Queue).
     * 3. Consumer (ESTE MÉTODO): É quem abre a caixa de correio (@RabbitListener), 
     *    pega a carta (AcessoSolicitadoEvent) e efetivamente executa o trabalho.
     * 
     * Responsabilidade: Realizar o processamento pesado/assíncrono da solicitação 
     * de forma invisível para o usuário final, sem travar a API (Controller).
     */

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void processarMensagem(AcessoSolicitadoEvent evento) {
        System.out.println("[CONSUMER] Nova mensagem recebida da fila: " + evento.nomeUsuario());
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
