package com.starters.dodu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "mentors")
public class Mentor {

  @Id
  @GeneratedValue
  @Column(name = "mentor_id")
  private UUID id; // 식별자

  @Column(nullable = true)
  private String password; // 비밀번호


  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String nickname;

  private String phone;

  private int age;

  private String gender;

  private LocalDateTime indate; // 생성일
}