package com.healthplan.properties.repository;

import com.healthplan.properties.model.Properties;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository extends BaseRepository<Properties, Long> {
    
    List<Properties> findByOdontologyContainingOrOrthodonticsContaining(String odontology, String Orthodontics);
}
