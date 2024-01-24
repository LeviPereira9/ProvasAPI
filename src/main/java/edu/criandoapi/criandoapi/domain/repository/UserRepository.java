package edu.criandoapi.criandoapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import edu.criandoapi.criandoapi.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);

}
