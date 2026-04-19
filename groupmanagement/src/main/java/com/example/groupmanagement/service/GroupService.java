package com.example.groupmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.groupmanagement.entity.Group;
import com.example.groupmanagement.repository.GroupRepository;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository repository;

    public Group addGroup(String name) {
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Group name required");
        }

        repository.findByGroupName(name).ifPresent(g -> {
            throw new RuntimeException("Group already exists");
        });

        Group g = new Group();
        g.setGroupName(name);
        return repository.save(g);
    }

    public List<Group> getAll() {
        return repository.findAll();
    }

    public Group update(Long id, String name) {
        Group g = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        g.setGroupName(name);
        return repository.save(g);
    }

    public Group toggle(Long id) {
        Group g = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        g.setIsActive(!g.getIsActive());
        return repository.save(g);
    }

    public void delete(Long id) {
    repository.deleteById(id);
}
}