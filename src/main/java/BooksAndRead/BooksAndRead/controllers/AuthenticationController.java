package BooksAndRead.BooksAndRead.controllers;


import BooksAndRead.BooksAndRead.Constants.JWTConstants;
import BooksAndRead.BooksAndRead.entities.LoginRequestDTO;
import BooksAndRead.BooksAndRead.entities.Publisher;
import BooksAndRead.BooksAndRead.entities.RegisterDTO;
import BooksAndRead.BooksAndRead.entities.User;
import BooksAndRead.BooksAndRead.infra.TokenGenerator;
import BooksAndRead.BooksAndRead.repositories.PublisherRepository;
import BooksAndRead.BooksAndRead.repositories.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO request, HttpServletResponse response){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.username(), request.password());

        var authentication = authenticationManager.authenticate(usernamePassword);
        var token = tokenGenerator.generateToken((UserDetails) authentication.getPrincipal());

        var cookie = this.setAuthenticationCookie(token);
        response.addCookie(cookie);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody RegisterDTO request){
        if(request.authority() == null
                || (userRepository.findByUsername(request.username()) != null
                        && publisherRepository.findByPublisherName(request.username()) != null)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());

        if(request.authority().equals("PUBLISHER")){
            Publisher newPublisher = new Publisher(request.username(), encryptedPassword, request.resume());
            publisherRepository.save(newPublisher);

        }
        else if (request.authority().equals("USER")) {
            User newUser = new User(request.username(),request.email(),encryptedPassword);
            userRepository.save(newUser);

        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    private Cookie setAuthenticationCookie(String token){
        Cookie cookie = new Cookie("JWT", token);

        cookie.setHttpOnly(true);
        cookie.setMaxAge(JWTConstants.JWT_EXPIRATION / 1000);
        cookie.setPath("/jwt");

        return cookie;
    }
}
