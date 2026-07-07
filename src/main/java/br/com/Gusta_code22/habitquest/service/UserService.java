package br.com.Gusta_code22.habitquest.service;

import br.com.Gusta_code22.habitquest.domain.User;
import br.com.Gusta_code22.habitquest.dto.UserRequestDTO;
import br.com.Gusta_code22.habitquest.dto.UserResponseDTO;
import br.com.Gusta_code22.habitquest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO save(UserRequestDTO userRequestDTO) throws Exception {
        if (userRepository.findByEmail(userRequestDTO.email()).isPresent()){
            throw new Exception("A user with this email already exists");
        }
        User user = new User();
        user.setXp(0);
        user.setName(userRequestDTO.name());
        user.setLevel(1);
        user.setEmail(userRequestDTO.email());
        userRepository.save(user);
        return new UserResponseDTO(
                user.getId(), user.getName(), user.getEmail(), user.getXp(), user.getLevel()
        );
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public UserResponseDTO findById(Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User Not Found"));


        return new UserResponseDTO(
                user.getId(), user.getName(), user.getEmail(), user.getXp(), user.getLevel()
        );
    }
}
