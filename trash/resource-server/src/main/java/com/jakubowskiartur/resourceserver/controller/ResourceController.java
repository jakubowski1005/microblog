package com.jakubowskiartur.resourceserver.controller;

import com.jakubowskiartur.resourceserver.model.Resource;
import com.jakubowskiartur.resourceserver.service.ResourceService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResourceController {

    ResourceService service;
    RestTemplate restTemplate;

    @GetMapping("/resources")
    public List<Resource> receiveResource() {
        return service.receiveResources();
    }

    @GetMapping("/resources/{id}")
    public Resource receiveResource(@PathVariable Integer id) {
        return service.receiveResourceById(id);
    }

    @SneakyThrows
    @PostMapping("/resources")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Resource saveResource(@RequestBody Resource resource) {

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        Class<?> cls = principal.getClass();
//        Method method = cls.getMethod("getId");
//        Long id = (Long) method.invoke(cls);

        resource.setOwner(getPrincipal().toString());
        return service.saveResource(resource);
    }

    @GetMapping("/me")
    public Object getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
