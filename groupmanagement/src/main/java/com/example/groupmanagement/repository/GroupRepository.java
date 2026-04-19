package com.example.groupmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.groupmanagement.entity.Group;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByGroupName(String groupName);
}