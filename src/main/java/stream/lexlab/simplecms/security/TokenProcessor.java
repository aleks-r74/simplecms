package stream.lexlab.simplecms.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.*;


@Component
public class TokenProcessor {

    private static final long TOKEN_LIFE_SEC = 86_400L * 10; // 10 days

    private final String secretBase64;
    private SecretKey privateKey;

    public TokenProcessor(@Value("${app.jwt.secret}") String secretBase64) {
        this.secretBase64 = secretBase64;
    }

    @PostConstruct
    public void init() {
        if (secretBase64 != null && !secretBase64.isBlank()) {
            byte[] decoded = Base64.getDecoder().decode(secretBase64);
            this.privateKey = Keys.hmacShaKeyFor(decoded);
        } else {
            byte[] keyBytes = new byte[32]; // 256-bit key
            new SecureRandom().nextBytes(keyBytes);
            this.privateKey = Keys.hmacShaKeyFor(keyBytes);
        }
    }

    public String generateToken(Authentication auth) {
        Date now = new Date();
        Date expiresAt = Date.from(
                Instant.now().plusSeconds(TOKEN_LIFE_SEC)
        );

        List<String> roles = auth.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .toList();

        return Jwts.builder()
                .subject(auth.getName())
                .claim("roles", roles)
                .signWith(privateKey)
                .issuedAt(now)
                .expiration(expiresAt)
                .compact();
    }

    public Authentication getAuthObjFromToken(String token) {

        if (token == null || token.isBlank()) {
            return null;
        }

        try {

            var payload = Jwts.parser()
                    .verifyWith(privateKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String username = payload.getSubject();
            if (username == null) {
                return null;
            }

            Object rawRoles = payload.get("roles");
            List<String> rolesList = new ArrayList<>();

            if (rawRoles instanceof Collection<?> collection) {
                for (Object r : collection) {
                    if (r != null) {
                        rolesList.add(r.toString());
                    }
                }
            } else if (rawRoles instanceof String str) {
                for (String part : str.split(",")) {
                    String trimmed = part.trim();
                    if (!trimmed.isEmpty()) {
                        rolesList.add(trimmed);
                    }
                }
            }

            var authorities = AuthorityUtils.createAuthorityList(
                    rolesList.toArray(new String[0])
            );

            return new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities
            );

        } catch (JwtException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}