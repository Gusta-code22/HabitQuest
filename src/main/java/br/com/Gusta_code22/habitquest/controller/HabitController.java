package br.com.Gusta_code22.habitquest.controller;

import br.com.Gusta_code22.habitquest.dto.ApiError;
import br.com.Gusta_code22.habitquest.dto.HabitRequestDTO;
import br.com.Gusta_code22.habitquest.dto.HabitResponseDTO;
import br.com.Gusta_code22.habitquest.service.HabitService;
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
@RequestMapping("/habits")
@RequiredArgsConstructor
@Tag(name = "Habit Management", description = "Endpoints for creating, managing, and tracking RPG-style daily habits and quests")
public class HabitController {

    private final HabitService service;

    @PostMapping
    @Operation(summary = "Create a new habit", description = "Registers a new habit/quest for a specific player profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Habit successfully created",
                    content = @Content(schema = @Schema(implementation = HabitResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data or business rule violation",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<HabitResponseDTO> createHabit(@RequestBody @Valid HabitRequestDTO requestDTO){
        HabitResponseDTO responseDTO = service.createHabit(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a habit", description = "Permanently removes a habit from the player's quest log by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habit successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Habit not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a habit by ID", description = "Retrieves the full specifications and metadata of a specific habit.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habit details found",
                    content = @Content(schema = @Schema(implementation = HabitResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Habit not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<HabitResponseDTO> findUserById(@PathVariable Long id){
        HabitResponseDTO responseDTO = service.findById(id);
        // Quick note: Changed from HttpStatus.CREATED to OK here for accurate GET behavior
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}