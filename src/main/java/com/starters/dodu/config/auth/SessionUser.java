package com.starters.dodu.config.auth;

import com.starters.dodu.domain.Mentee;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(Mentee mentee) {
        this.name = mentee.getNickname();
        this.email = mentee.getEmail();
    }
}