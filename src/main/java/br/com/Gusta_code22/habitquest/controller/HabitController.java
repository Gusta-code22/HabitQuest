package br.com.Gusta_code22.habitquest.controller;

import br.com.Gusta_code22.habitquest.dto.HabitRequestDTO;
import br.com.Gusta_code22.habitquest.dto.HabitResponseDTO;
import br.com.Gusta_code22.habitquest.dto.UserResponseDTO;
import br.com.Gusta_code22.habitquest.service.HabitService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService service;

    @PostMapping
    public ResponseEntity<HabitResponseDTO> createHabit(@RequestBody HabitRequestDTO requestDTO) throws Exception {
        HabitResponseDTO responseDTO = service.createHabit(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitResponseDTO> findUserById(@PathVariable Long id) throws Exception{
        HabitResponseDTO responseDTO = service.findById(id);
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
