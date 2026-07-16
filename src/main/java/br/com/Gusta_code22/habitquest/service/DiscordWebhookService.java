package br.com.Gusta_code22.habitquest.service;

import br.com.Gusta_code22.habitquest.dto.DiscordMessageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DiscordWebhookService {

    @Value("${discord.webhook-url}")
    private String WEBHOOK_URL;
    private final RestTemplate restTemplate = new RestTemplate();

    public void enviarMensagem(String texto) {
        DiscordMessageDTO payload = new DiscordMessageDTO(texto);
        try {
            restTemplate.postForEntity(WEBHOOK_URL, payload, String.class);
            System.out.println("⚔️ Message sent to guild successfully!");
        } catch (Exception e) {
            System.err.println("Error connecting to Discord: " + e.getMessage());
        }
    }
}