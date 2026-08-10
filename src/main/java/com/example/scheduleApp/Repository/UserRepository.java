package com.example.scheduleApp.Repository;

import com.example.scheduleApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserAccount(String UserAccount);

	boolean existsByUserAccount(String UserAccount);
}