package com.bilgeadam.controlller;

import com.bilgeadam.dto.request.ActivateRequestDto;
import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.LoginResponseDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.service.AuthService;
import com.bilgeadam.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtTokenManager jwtTokenManager;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto dto) {

        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {

        return ResponseEntity.ok(authService.login(dto).get());
    }


    @PostMapping("/activate")
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto) {
        return ResponseEntity.ok(authService.activateStatus(dto));
    }

    @GetMapping("/token")
    public String getToken(Long id) {

        return jwtTokenManager.createToken(id);

    }

    @GetMapping("/getId")
    public Long getId(String token) {

        return jwtTokenManager.getUserId(token).get();

    }

}
