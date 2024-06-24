package BooksAndRead.BooksAndRead.services;

import BooksAndRead.BooksAndRead.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import BooksAndRead.BooksAndRead.repositories.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    PublisherRepository publisherRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, PublisherRepository publisherRepository) {
        this.userRepository = userRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(username);

        if(user != null){
            return user;
        }

        UserDetails publisher = publisherRepository.findByPublisherName(username);

        if(publisher != null){
            return publisher;
        }

        throw new UsernameNotFoundException("User not found");
    }
}
