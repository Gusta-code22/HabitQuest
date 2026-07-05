package br.com.Gusta_code22.habitquest.service;

import br.com.Gusta_code22.habitquest.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
}
