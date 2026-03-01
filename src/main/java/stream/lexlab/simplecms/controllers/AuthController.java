package stream.lexlab.simplecms.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stream.lexlab.simplecms.models.LoginRequest;
import stream.lexlab.simplecms.models.TokenDto;
import stream.lexlab.simplecms.security.TokenProcessor;

@RestController
@RequiredArgsConstructor
class AuthController {
    private final TokenProcessor tokenProcessor;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth/login")
    public TokenDto login(@RequestBody LoginRequest loginRequest) {
        var authToken = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        Authentication auth  = authenticationManager.authenticate(authToken);
        return new TokenDto(tokenProcessor.generateToken(auth));
    }

}
