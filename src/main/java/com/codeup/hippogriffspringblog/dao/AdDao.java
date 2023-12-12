package com.codeup.hippogriffspringblog.dao;

import com.codeup.hippogriffspringblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdDao extends JpaRepository<Ad, Long> {

}
