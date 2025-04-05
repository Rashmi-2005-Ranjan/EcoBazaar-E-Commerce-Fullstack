package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.UserException;
import com.ecobazar.main.ecommerce.model.User;

public interface UserService {
    public User findUserById(Long id) throws UserException;
    public User findUserProfileByJwt(String jwt) throws UserException;
}
