package com.carlos.curso.springboot.jpa.springbootjpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@Embeddable
public class Audit {

  @Column(name = "create_at")
  private LocalDateTime createAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  public void prePersist() {
    System.out.println("evento del ciclo de vida del entity pre persist");
    this.createAt = LocalDateTime.now();
  }

  @PreUpdate
  public void preUpdate() {
    System.out.println("evento del ciclo de vida del entity pre update");
    this.updatedAt = LocalDateTime.now();
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

}
