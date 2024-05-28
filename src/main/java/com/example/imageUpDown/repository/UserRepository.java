package com.example.imageUpDown.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.imageUpDown.entity.UserEntity;




 
@Repository
public interface UserRepository
        extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUserName(String userName);
 
}
