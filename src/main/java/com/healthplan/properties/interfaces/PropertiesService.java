package com.healthplan.properties.interfaces;

import com.healthplan.properties.model.Properties;
import java.util.List;


public interface PropertiesService extends BaseService<Properties, Long> {
   
    List<Properties> search(String filter) throws Exception;
}
