package com.marshmellow.localcbt.repository;

import com.marshmellow.localcbt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

}
