package com.health.service.impl;

import com.health.entity.Patient;
import com.health.service.HealthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
// spring 配置文件
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"})
public class HealthServiceImplTest {
    @Autowired
    HealthService healthService;

    @Test
    public void getPatientAll() throws Exception {
        List<Patient> list = healthService.getPatientAll();
        assertEquals(list.size(), 6);
    }

    @Test
    public void getPatient() throws Exception {
        Patient patient = healthService.getPatient(100000006);
        System.out.println(patient);
    }

    /**
     * org.springframework.dao.CannotAcquireLockException:
     ### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLTransactionRollbackException: Lock wait timeout exceeded; try restarting transaction
     ### The error may involve com.health.dao.PatientDao.insertPatient-Inline
     ### The error occurred while setting parameters
     ### SQL: INSERT INTO patient (patient_id, pressure, breath, temperature, pressure_high,pressure_low,         breath_high, breath_low, temperature_high, temperature_low, frequency)         VALUES (?, 0.00, 0.00, 0.00, ?, ?, ?, ?,         ?, ?, ?)
     ### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLTransactionRollbackException: Lock wait timeout exceeded; try restarting transaction
     ; SQL []; Lock wait timeout exceeded; try restarting transaction; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLTransactionRollbackException: Lock wait timeout exceeded; try restarting transaction
     */
    @Test
    public void insertPatient() throws Exception {
//        Patient patient = new Patient(100000006, 105.123,100,
//                19,17.534, 38.98754, 35, 1000);
//        boolean success = healthService.insertPatient(patient);
//        assertEquals(success, true);
    }

    @Test
    public void setFrequency() throws Exception {
//        boolean result = healthService.setFrequency(100000001, 8000);
//        assertEquals(result, true);
    }

    @Test
    public void alarm() throws Exception {
        boolean result = healthService.alarm(100000001);
        assertEquals(result, true);
    }

    @Test
    public void recordAndUpdateHealth() throws Exception {
    }

    @Test
    public void updateBound() throws Exception {
    }

}