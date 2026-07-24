package br.com.Gusta_code22.habitquest.service;

import br.com.Gusta_code22.habitquest.domain.User;
import br.com.Gusta_code22.habitquest.dto.DiscordLinkDTO;
import br.com.Gusta_code22.habitquest.dto.MessageResponseDTO;
import br.com.Gusta_code22.habitquest.exception.DiscordAlreadyLinkedException;
import br.com.Gusta_code22.habitquest.exception.DiscordIdAlreadyInUseException;
import br.com.Gusta_code22.habitquest.exception.UserNotFoundException;
import br.com.Gusta_code22.habitquest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscordService {

    private final UserRepository userRepository;

    public MessageResponseDTO link(DiscordLinkDTO dto){


        User user = getUser(dto);


        validateUserDiscord(dto, user);

        validateDiscordId(dto);

        saveDiscord(dto, user);
        return new MessageResponseDTO("✅ Conta vinculada com sucesso!");

    }

    private void saveDiscord(DiscordLinkDTO dto, User user) {
        user.setDiscordId(dto.discordId());
        userRepository.save(user);
    }

    private void validateDiscordId(DiscordLinkDTO dto) {
        Optional<User> byDiscordId = userRepository.findByDiscordId(dto.discordId());
        if (byDiscordId.isPresent() && !Objects.equals(byDiscordId.get().getId(), dto.userId())) {
            throw new DiscordIdAlreadyInUseException("Esse DiscordID ja pertence a um usuario");
        }
    }

    private static void validateUserDiscord(DiscordLinkDTO dto, User user) {
        if (user.getDiscordId() != null && !Objects.equals(user.getDiscordId(), dto.discordId())){
            throw new DiscordAlreadyLinkedException("O usuário já possui um Discord vinculado");
        }
    }

    private User getUser(DiscordLinkDTO dto) {
        return userRepository.findById(dto.userId())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found")
                );
    }
}
