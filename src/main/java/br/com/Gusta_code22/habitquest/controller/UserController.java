package br.com.Gusta_code22.habitquest.controller;

import br.com.Gusta_code22.habitquest.dto.ApiError;
import br.com.Gusta_code22.habitquest.dto.UserRequestDTO;
import br.com.Gusta_code22.habitquest.dto.UserResponseDTO;
import br.com.Gusta_code22.habitquest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Endpoints for managing players (users) in the HabitQuest RPG ecosystem")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Register a new player", description = "Creates a new user profile with a unique email address to start the RPG journey.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Player successfully registered",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data or Email already exists",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody @Valid UserRequestDTO dto){
        UserResponseDTO responseDTO = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a player profile", description = "Permanently deletes a user account and all associated data by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Player not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a player by ID", description = "Retrieves the profile details of a specific player using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player profile found",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Player not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id){
        UserResponseDTO userDto = userService.findById(id);
        // Quick note: Changed HttpStatus from CREATED to OK here since it's a GET request
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
}