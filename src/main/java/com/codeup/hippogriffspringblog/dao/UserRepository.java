package com.codeup.hippogriffspringblog.dao;

import com.codeup.hippogriffspringblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {


    User findUserById(long id);

    @Query("from User u where u.id = :userId")
    User findUserByHisHerNumber(@Param("userId") long userId);

    // By default this would return a Optional<User> object
    // By writing it myself, it returns a User instead
}
