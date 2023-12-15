package com.codeup.hippogriffspringblog.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name="adlister_categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 75)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Ad> ads;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}
