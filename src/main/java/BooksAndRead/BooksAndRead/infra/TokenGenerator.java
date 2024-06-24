package BooksAndRead.BooksAndRead.infra;

import BooksAndRead.BooksAndRead.Constants.JWTConstants;
import BooksAndRead.BooksAndRead.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenGenerator {

    public String generateToken (UserDetails user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWTConstants.JWT_SECRET);
            String token = JWT.create()
                    .withIssuer("AuthenticationAPI")
                    .withSubject(user.getUsername())
                    .withExpiresAt(Instant.ofEpochSecond(JWTConstants.JWT_EXPIRATION))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error Token generator:", exception);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(JWTConstants.JWT_SECRET);

            return JWT.require(algorithm)
                    .withIssuer("auth-apí")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "Não foi possível logar: " + exception.getMessage();
        }
    }
}
