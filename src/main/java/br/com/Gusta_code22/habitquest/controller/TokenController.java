package br.com.Gusta_code22.habitquest.controller;

import br.com.Gusta_code22.habitquest.dto.LoginRequest;
import br.com.Gusta_code22.habitquest.dto.LoginResponse;
import br.com.Gusta_code22.habitquest.exception.InvalidHeroCredentialsException;
import br.com.Gusta_code22.habitquest.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){
        var user = userRepository.findByEmail(request.email());

        if (user.isEmpty() || !user.get().isLoginCorrect(request, passwordEncoder)){
            throw new InvalidHeroCredentialsException("email or password is invalid!");

        }

        var now = Instant.now();
        var expiresIn = 86400L;

        var scopes = "Player";

        var claims = JwtClaimsSet.builder()
                .issuer("habitquest-backend")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }


}
