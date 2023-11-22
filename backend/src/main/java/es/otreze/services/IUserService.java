package es.otreze.services;

import es.otreze.dtos.SaveUserDTO;
import es.otreze.persistence.security.User;

import java.util.Optional;


public interface IUserService {

    User registerOneCustomer (SaveUserDTO newUser);

    Optional<User> findOneByUsername(String username);
}
