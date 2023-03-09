package com.healthplan.properties;

import com.healthplan.properties.model.Properties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PropertiesTest {

    private Properties properties;

    @BeforeEach
    public void setUp() {

        properties = new Properties();
        properties.setCopay(300.5);
        properties.setInternation(1);
        properties.setDoctorToHome(0);
        properties.setOdontology("NO INCLUYE");
        properties.setOrthodontics("NO INCLUYE");
        properties.setMedicalGuide(1240);
        properties.setRefund(150.3);
    }

    @Test
    void testPropertiesCreation() {

        assertNotNull(properties);
        assertEquals(300.5, properties.getCopay());
        assertEquals(1, properties.getInternation());
        assertEquals(0, properties.getDoctorToHome());
        assertEquals("NO INCLUYE", properties.getOdontology());
        assertEquals("NO INCLUYE", properties.getOrthodontics());
        assertEquals(1240, properties.getMedicalGuide());
        assertEquals(150.3, properties.getRefund());
    }

    @Test
    void testPropertiesId() {

        assertNotNull(properties.getProperId());
    }
}
