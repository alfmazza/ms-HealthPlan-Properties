package com.healthplan.properties.service;

import com.healthplan.properties.interfaces.PropertiesService;
import com.healthplan.properties.model.Properties;
import com.healthplan.properties.repository.PropertiesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertiesServiceImpl extends BaseServiceImpl<Properties, Long> implements PropertiesService {

    @Autowired
    private PropertiesRepository propertiesRepository;

    @Override
    public List<Properties> search(String filter) throws Exception {
        
        try{
            List<Properties> entities = propertiesRepository.findByOdontologyContainingOrOrthodonticsContaining(filter, filter);
            return entities;
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    
    
}

