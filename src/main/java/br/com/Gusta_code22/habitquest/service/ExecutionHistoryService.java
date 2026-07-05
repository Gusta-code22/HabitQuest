package br.com.Gusta_code22.habitquest.service;

import br.com.Gusta_code22.habitquest.repository.ExecutionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExecutionHistoryService {

    private final ExecutionHistoryRepository historyRepository;
}
