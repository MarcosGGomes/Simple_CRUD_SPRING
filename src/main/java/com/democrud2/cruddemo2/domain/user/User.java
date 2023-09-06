package com.democrud2.cruddemo2.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public User(RequestUser requestUser) {
        this.name = requestUser.name();
        this.email = requestUser.email();
    }
}
