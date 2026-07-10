package br.com.Gusta_code22.habitquest.service;

import br.com.Gusta_code22.habitquest.domain.User;
import br.com.Gusta_code22.habitquest.dto.UserRequestDTO;
import br.com.Gusta_code22.habitquest.dto.UserResponseDTO;
import br.com.Gusta_code22.habitquest.exception.EmailAlreadyExistsException;
import br.com.Gusta_code22.habitquest.exception.ForbiddenException;
import br.com.Gusta_code22.habitquest.exception.UserNotFoundException;
import br.com.Gusta_code22.habitquest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        if (userRepository.findByEmail(userRequestDTO.email()).isPresent()){
            throw new EmailAlreadyExistsException("A user with this email already exists");
        }
        User user = new User();
        user.setXp(0);
        user.setName(userRequestDTO.name());
        user.setPassword(encoder.encode(userRequestDTO.password()));
        user.setLevel(1);
        user.setEmail(userRequestDTO.email());
        userRepository.save(user);
        return new UserResponseDTO(
                user.getId(), user.getName(), user.getEmail(), user.getXp(), user.getLevel()
        );
    }

    public void deleteUser(Long id, JwtAuthenticationToken token){
        Long currentUserId = Long.valueOf(token.getName());
        var user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        if (!Objects.equals(user.get().getId(), currentUserId)){
            throw new ForbiddenException("You are not allowed to delete this user");
        }
        userRepository.deleteById(id);
    }

    public UserResponseDTO findById(Long id, JwtAuthenticationToken token) {
        Long currentUserId = Long.valueOf(token.getName());
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        if (!Objects.equals(user.getId(), currentUserId)){
            throw new ForbiddenException("You cannot search for another user");
        }


        return new UserResponseDTO(
                user.getId(), user.getName(), user.getEmail(), user.getXp(), user.getLevel()
        );
    }
}
