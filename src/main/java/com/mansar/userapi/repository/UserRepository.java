package com.mansar.userapi.repository;


import com.mansar.userapi.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<user,Long> {
}
