package com.sha.spring_boot_book_seller.security.jwt;


import com.sha.spring_boot_book_seller.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

/**
 * @author sa
 * @date 3.07.2021
 * @time 19:15
 */
public interface IJwtProvider
{
    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean validateToken(HttpServletRequest request);
}
