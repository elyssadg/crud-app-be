package com.project.spring.boot.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Persistable<String>, Serializable {
    @Id
    private String id;

    private String username;

    private String password;

    private String name;

    private String token;

    @Column("token_expired_at")
    private Long tokenExpiredAt;

    @Transient
    private boolean newUser;

    @Override
    @Transient
    public boolean isNew() {
        return this.newUser || id == null;
    }

    public User setAsNew() {
        this.newUser = true;
        return this;
    }
}
