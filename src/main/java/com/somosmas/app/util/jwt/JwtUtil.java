package com.somosmas.app.util.jwt;

import com.somosmas.app.model.response.UserDetailsResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class JwtUtil {

    private static final String SECRET_KEY = "secret";
    private static final String BEARER_TOKEN = "Bearer %s";
    private static final String AUTHORITIES = "authorities";

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetailsResponse login) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, login.getEmail(), login.getRole());
    }

    private String createToken(Map<String, Object> claims, String subject, String role) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(role);

        String token = Jwts
                .builder()
                .setSubject(subject)
                .claim(AUTHORITIES,
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))//1 hour
                .signWith(SignatureAlgorithm.HS512,
                        SECRET_KEY.getBytes()).compact();
        return String.format(BEARER_TOKEN, token);
    }

    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

}