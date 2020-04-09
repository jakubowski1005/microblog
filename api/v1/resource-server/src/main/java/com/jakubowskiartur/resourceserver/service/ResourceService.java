package com.jakubowskiartur.resourceserver.service;

import com.jakubowskiartur.resourceserver.model.Resource;

import java.util.List;

public interface ResourceService {

    List<Resource> receiveResources();
    Resource receiveResourceById(Integer id);
    Resource saveResource(Resource resource);
}
