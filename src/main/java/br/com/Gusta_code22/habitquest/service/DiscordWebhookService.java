package br.com.Gusta_code22.habitquest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DiscordWebhookService {

    // Substitua pela sua URL real do webhook (coloque no application.properties depois!)
    private final String WEBHOOK_URL = "https://discord.com/api/webhooks/1525206515781468162/EQR9znkJAgfEZOBSHDaW5xX4KKT8u-neekrDI2WyGZi8cbWZBF9eYkYQMFATmScMVZYA";
    private final RestTemplate restTemplate = new RestTemplate();

    public void enviarMensagem(String texto) {
        DiscordMessageDTO payload = new DiscordMessageDTO(texto);
        try {
            // O RestTemplate já converte o objeto para JSON e configura os Headers automaticamente!
            restTemplate.postForEntity(WEBHOOK_URL, payload, String.class);
            System.out.println("⚔️ Mensagem enviada para a guilda com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar com o Discord: " + e.getMessage());
        }
    }
}