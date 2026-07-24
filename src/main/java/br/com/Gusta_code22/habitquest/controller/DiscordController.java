package br.com.Gusta_code22.habitquest.controller;

import br.com.Gusta_code22.habitquest.dto.DiscordLinkDTO;
import br.com.Gusta_code22.habitquest.service.DiscordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discord")
@RequiredArgsConstructor
public class DiscordController {

    private final DiscordService service;

    @PostMapping("/link")
    public ResponseEntity<?> link(@RequestBody DiscordLinkDTO dto){
        return ResponseEntity.ok(service.link(dto));
    }
}
