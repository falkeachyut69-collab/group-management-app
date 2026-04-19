package com.example.groupmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_table")
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(unique = true, nullable = false)
    private String groupName;

    private Boolean isActive = true;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void updateTime() {
        this.updatedAt = LocalDateTime.now();
    }
}