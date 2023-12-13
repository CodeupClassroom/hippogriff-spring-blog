package com.codeup.hippogriffspringblog.dao;

import com.codeup.hippogriffspringblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(long id);
}
