package com.codeup.hippogriffspringblog.dao;

import com.codeup.hippogriffspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
