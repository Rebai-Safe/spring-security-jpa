package com.springsecurity.jpa.Repositories;

import com.springsecurity.jpa.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

Optional<Users> findByUserName(String userName);

}
