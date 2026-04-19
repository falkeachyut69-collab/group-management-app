package com.example.groupmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.groupmanagement.entity.Group;
import com.example.groupmanagement.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "*")

public class GroupController {

    @Autowired
    private GroupService service;

    @PostMapping
    public Group add(@RequestBody Group g) {
        return service.addGroup(g.getGroupName());
    }

    @GetMapping
    public List<Group> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Group update(@PathVariable Long id, @RequestBody Group g) {
        return service.update(id, g.getGroupName());
    }

    @PatchMapping("/{id}")
    public Group toggle(@PathVariable Long id) {
        return service.toggle(id);
    }

@DeleteMapping("/{id}")
public void deleteGroup(@PathVariable Long id) {
    service.delete(id);
}

}