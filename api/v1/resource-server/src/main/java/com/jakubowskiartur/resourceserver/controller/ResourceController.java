package com.jakubowskiartur.resourceserver.controller;

import com.jakubowskiartur.resourceserver.model.Resource;
import com.jakubowskiartur.resourceserver.service.ResourceService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResourceController {

    ResourceService service;

    @GetMapping("/resources")
    public List<Resource> receiveResource() {
        return service.receiveResources();
    }

    @GetMapping("/resources/{id}")
    public Resource receiveResource(@PathVariable Integer id) {
        return service.receiveResourceById(id);
    }

    @PostMapping("/resources")
    public Resource saveResource(@RequestBody Resource resource) {
        return service.saveResource(resource);
    }
}
