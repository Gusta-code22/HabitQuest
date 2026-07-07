package br.com.Gusta_code22.habitquest.controller;

import br.com.Gusta_code22.habitquest.service.ExecutionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/executions")
@RequiredArgsConstructor
public class ExecutionHistoryController {

    private final ExecutionHistoryService service;

    @PostMapping("/habit/{habitId}")
    public ResponseEntity<?> registerExecution(@PathVariable Long habitId) throws Exception {
        service.registrarExecucao(habitId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
