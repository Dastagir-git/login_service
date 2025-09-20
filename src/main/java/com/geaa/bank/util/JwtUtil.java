package com.geaa.bank.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final static String SECRET = "ashbhbuyaguyyuwbjkcjwasbhcwegyuhwe51521as3cx5a12SC152WA1XC5SC250WABHWQBDYHUWD544556561Qscasdc454145145";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 360000))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

//    private static Key getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

    private static Key getSignKey1() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }
    public static String extractUsername(String token) {
        return getClaims(token).getSubject();// 'sub' claim holds the username by convention
    }

    public static Claims getClaims(String token) {
        Claims claims =  Jwts.parserBuilder()
                .setSigningKey(getSignKey1())
                .build()
                .parseClaimsJws(token)
                .getBody();
       return claims;
    }

    public static boolean validate( String token){
        Claims claims = getClaims(token);
        Date currentTime = new Date();
        if(claims!=null && !claims.isEmpty() && currentTime.before(claims.getExpiration())){
            return true;
        }
        return false;
    }
}

