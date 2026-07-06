package br.com.Gusta_code22.habitquest.controller;

import br.com.Gusta_code22.habitquest.dto.UserRequestDTO;
import br.com.Gusta_code22.habitquest.dto.UserResponseDTO;
import br.com.Gusta_code22.habitquest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO dto) throws Exception {
        UserResponseDTO responseDTO = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
