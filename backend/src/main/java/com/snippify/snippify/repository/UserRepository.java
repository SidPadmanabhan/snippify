package com.snippify.snippify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.snippify.snippify.model.User;
import java.util.Optional; 

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); 
}
