package com.jakubowskiartur.resourceserver.service;

import com.jakubowskiartur.resourceserver.model.Resource;
import com.jakubowskiartur.resourceserver.repository.ResourceRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("resourceService")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResourceServiceImpl implements ResourceService {

    ResourceRepository repository;

//    static List<Resource> resources;
//
//    static {
//        resources = Arrays.asList(
//                new Resource(1, "First resource", 13.45, 10L),
//                new Resource(2, "Second resource", -89.43265, 10L),
//                new Resource(3, "Third resource", 1.01, 11L),
//                new Resource(4, "Fourth resource", 0.1, 12L),
//                new Resource(5, "Fifth resource", 69.09, 12L));
//    }

    @Override
    public List<Resource> receiveResources() {

//        if (repository.findAll().isEmpty()) {
//            resources.forEach(this::saveResource);
//        }
        return repository.findAll();
    }

    @Override
    public Resource receiveResourceById(Integer id) {

//        if (repository.findAll().isEmpty()) {
//            resources.forEach(this::saveResource);
//        }
        return repository.getOne(id);
    }

    @Override
    public Resource saveResource(Resource resource) {
        return repository.save(resource);
    }
}
