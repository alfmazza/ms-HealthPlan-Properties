package com.healthplan.properties.service;

import com.healthplan.properties.interfaces.BaseService;
import com.healthplan.properties.model.Base;
import com.healthplan.properties.repository.BaseRepository;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;



public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {
    
    @Autowired
    protected BaseRepository<E, ID> baseRepository;
    
    
    @Override
    public List<E> findAll() throws Exception {
        
        try {
            List <E> entities = baseRepository.findAll();
            if(entities.isEmpty()) {
                throw new NotFoundException();
            }

            return entities;
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    public E findById(ID id) throws Exception{
    
       try {
           Optional<E> entityOptional = baseRepository.findById(id);
           
            return entityOptional.get();
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    
    @Override
    @Transactional
    public E save(E entity) throws Exception {
    
        try {
            entity = baseRepository.save(entity);
            
            return entity;
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
    
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if(entityOptional.isEmpty()) {
                throw new NotFoundException();
            }
            
            E existingEntity = entityOptional.get();
            BeanUtils.copyProperties(entity, existingEntity, "properId");
            return baseRepository.save(existingEntity);
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        
        try {
            
            if(baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
