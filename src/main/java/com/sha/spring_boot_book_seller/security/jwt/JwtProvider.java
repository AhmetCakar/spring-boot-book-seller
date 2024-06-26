package com.sha.spring_boot_book_seller.security.jwt;

import com.sha.spring_boot_book_seller.security.UserPrincipal;
import com.sha.spring_boot_book_seller.util.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtProvider implements IJwtProvider {

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;

//    private static final String JWT_SECRET_test = "your-32-character-or-more-secret-key";
//    // HMACSHA key oluşturmak
//    private Key key;
//
//    @PostConstruct
//    public void init() {
//        this.key = Keys.hmacShaKeyFor(JWT_SECRET_test.getBytes());
//    }

    private static SecretKey Key;


    public JwtProvider(){
        String secretString= "84356789445641JHDKGJDNVDK46464BLABLA1234567";
        byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
        this.Key = new SecretKeySpec(keyBytes,"HmacSHA256");

    }

    @Override
    public String generateToken(UserPrincipal auth) {
        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(auth.getUsername())
                .claim("roles", authorities)
                .claim("userId", auth.getId())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(Key) // Anahtarla imzala
                .compact();
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        Claims claims = extractClaims(request);

        if (claims == null) {
            return null;
        }

        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);

        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtils::convertToAuthority)
                .collect(Collectors.toSet());

        UserDetails userDetails = UserPrincipal.builder()
                .userName(username)
                .authorities(authorities)
                .id(userId)
                .build();

        if (username == null) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }

    @Override
    public boolean validateToken(HttpServletRequest request) {
        Claims claims = extractClaims(request);

        if (claims == null) {
            return false;
        }

        if (claims.getExpiration().before(new Date())) {
            return false;
        }
        return true;
    }

    private Claims extractClaims(HttpServletRequest request) {
        String token = SecurityUtils.extractAuthTokenFromRequest(request);

        if (token == null) {
            return null;
        }

        return Jwts.parserBuilder()
                .setSigningKey(Key) // Anahtarı burada da kullan
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
