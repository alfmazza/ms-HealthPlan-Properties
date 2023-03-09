package com.healthplan.properties.controller;

import com.healthplan.properties.model.Properties;
import com.healthplan.properties.service.PropertiesServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/properties")

public class PropertiesController extends BaseControllerImpl<Properties, PropertiesServiceImpl> {

    @Autowired
    protected PropertiesServiceImpl propertiesService;
    
    @GetMapping("/search")
    @Operation(
            description = "Obtener registros por filtro",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "okAPI"),
                    @ApiResponse(responseCode = "404", ref = "notFoundAPI"),              
            }
    )
    public ResponseEntity<?> search(@RequestParam String filter) {
        
        try {
            return ResponseEntity.status(HttpStatus.OK).body(propertiesService.search(filter));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"notice\":\"Notice. No se encontraron registros.(CODE 404)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }
}
