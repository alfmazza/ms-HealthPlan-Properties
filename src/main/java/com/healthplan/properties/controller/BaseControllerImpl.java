package com.healthplan.properties.controller;

import com.healthplan.properties.interfaces.BaseController;
import com.healthplan.properties.model.Base;
import com.healthplan.properties.service.BaseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


public abstract class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long> {
    
    @Autowired
    public S service;
   
    
    @Override
    @GetMapping("")
    @Operation(
            description = "Obtener todos los registros",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "okAPI"),
                    @ApiResponse(responseCode = "404", ref = "notFoundAPI"),              
            }
    )
    public ResponseEntity<?> getAllRecord() {
        try { 
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"notice\":\"Notice. No se encontraron registros.(CODE 404)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }
    
    
    @Override
    @GetMapping("/{id}")
    @Operation(
            description = "Obtener un registro por id",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "okAPI"),
                    @ApiResponse(responseCode = "404", ref = "notFoundAPI")
            }
    )
    public ResponseEntity<?> getRecordById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"notice\":\"Notice. No se encontro el registro.(CODE 404)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }
    
    
    @Override
    @PostMapping("")
    @Operation(
            description = "Guardar registro/s",
            responses = {
                    @ApiResponse(responseCode = "201", ref = "createdAPI"),
                    @ApiResponse(responseCode = "400", ref = "badRequestAPI")
            }
    )
    public ResponseEntity<?> save(@RequestBody E entity) {
        try {
             return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor, intente nuevamente mas tarde.(CODE 400)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }
    
    
    @Override
    @PutMapping("/{id}")
    @Operation(
            description = "Editar registro/s",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "okAPI"),
                    @ApiResponse(responseCode = "400", ref = "badRequestAPI")
            }
    )
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody E entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor, intente nuevamente mas tarde.(CODE 400)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }

    
    @Override
    @DeleteMapping("/{id}")
    @Operation(
            description = "Eliminar registro/s",
            responses = {
                    @ApiResponse(responseCode = "204", ref = "noContentAPI"),
                    @ApiResponse(responseCode = "404", ref = "notFoundAPI")
            }
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"notice\":\"Notice. No se encontro el registro. (CODE 404)\",\"timestamp\":\"" + LocalDateTime.now() + "\"}");
        }
    }
}
