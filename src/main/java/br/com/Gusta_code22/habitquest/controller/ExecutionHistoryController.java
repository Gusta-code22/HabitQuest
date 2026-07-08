package br.com.Gusta_code22.habitquest.controller;

import br.com.Gusta_code22.habitquest.dto.ApiError;
import br.com.Gusta_code22.habitquest.service.ExecutionHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/executions")
@RequiredArgsConstructor
@Tag(name = "Execution History", description = "Endpoints for checking in, completing daily habits, and tracking quest logs")
public class ExecutionHistoryController {

    private final ExecutionHistoryService service;

    @PostMapping("/habit/{habitId}")
    @Operation(summary = "Complete a habit check-in", description = "Registers a new execution for a specific habit. This action acts as completing a quest log.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Habit check-in successfully registered"),
            @ApiResponse(responseCode = "400", description = "Habit already executed today",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Habit not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public ResponseEntity<?> registerExecution(@PathVariable Long habitId){
        service.registrarExecucao(habitId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}