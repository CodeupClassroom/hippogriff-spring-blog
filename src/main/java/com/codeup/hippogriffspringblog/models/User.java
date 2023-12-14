package com.codeup.hippogriffspringblog.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false, length = 70)
    private String username;


    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Ad> ads;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Post> posts;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
