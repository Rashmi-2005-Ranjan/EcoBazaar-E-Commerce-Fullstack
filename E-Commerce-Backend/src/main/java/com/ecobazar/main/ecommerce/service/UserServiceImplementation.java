package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.config.JwtProvider;
import com.ecobazar.main.ecommerce.exception.UserException;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long id) throws UserException {
        Optional<User> user = userRepository.findById ( id );
        if (user.isPresent ( )) {
            return user.get ( );
        } else {
            throw new UserException ( "User Not Found With Id" + id );
        }
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken ( jwt );
        User user = userRepository.findByEmail ( email );
        if (user == null) {
            throw new UserException ( "User Not Found With Email" + email );
        }
        return user;
    }
}
