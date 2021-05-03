package ru.evilnoob.project.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.evilnoob.project.auth.config.jwt.JwtTokenHelper;
import ru.evilnoob.project.auth.model.dto.AuthRequestDto;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final JwtTokenHelper jwtTokenHelper;
    private final AuthenticationManager authenticationManager;

    @GetMapping("hello")
    public Mono<String> hello() {
        return Mono.just("hello");
    }

    @PostMapping("login")
    public Mono<ResponseEntity> hello(@RequestBody AuthRequestDto authRequest) {
        return Mono.defer(() -> {
            try {
                Authentication authenticate = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getLogin(),
                                authRequest.getPassword())
                );
                UserDetails user = (UserDetails) authenticate.getPrincipal();
                log.info("Succesfully authentication.");

                return Mono.just(ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtTokenHelper.generateToken(user)).build());
            } catch (BadCredentialsException e) {
                log.info("Unsuccesfull authentication.");
                return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
            }
        });

    }
}
