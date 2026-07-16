package br.com.Gusta_code22.habitquest.service;

import br.com.Gusta_code22.habitquest.domain.Habit;
import br.com.Gusta_code22.habitquest.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationScheduledService {

    private final HabitRepository habitRepository;
    private final DiscordWebhookService discordWebhookService;

    // Cron para rodar todo dia às 22h: "segundo minuto hora dia mes dia-da-semana"
//    @Scheduled(cron = "0 0 22 * * *")
    // Para testar a cada 1 minuto, use: @Scheduled(fixedDelay = 60000)
    @Scheduled(fixedDelay = 60000)
    public void verificarHabitosEsquecidos() {
        LocalDate hoje = LocalDate.now();
        LocalDateTime inicioDoDia = hoje.atStartOfDay(); // 00:00:00
        LocalDateTime fimDoDia = hoje.atTime(LocalTime.MAX); // 23:59:59.999999

        List<Habit> habitosEsquecidos = habitRepository.findHabitsWithoutExecutionToday(inicioDoDia, fimDoDia);

        for (Habit habit : habitosEsquecidos) {
            String nomeDoMestre = habit.getUser().getName();
            String nomeDoHabito = habit.getName();

            String mensagem = String.format("""
    >>> 🚨 **AVISO DA TAVERNA: QUEST EM PERIGO!** 🚨
    
    O herói **%s** está prestes a deixar os monstros invadirem o reino!
    A quest **[%s]** ainda não foi realizada hoje.
    
    ⚔️ *Status: Pendente*
    ⏳ *Tempo restante: Poucas horas até o reset do dia!*
    
    👉 Vá até o painel, complete sua tarefa e garanta seus pontos de XP antes que seja tarde demais!
    """,
                    nomeDoMestre, nomeDoHabito);

            discordWebhookService.enviarMensagem(mensagem);

            // Pausa de 1 segundo entre as mensagens para evitar o Rate Limit do Discord
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}