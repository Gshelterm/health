package com.health.dao;

import com.health.entity.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test, junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// spring 配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class PatientDaoTest {
    @Autowired
    private PatientDao patientDao;

    @Test
    public void queryById() throws Exception {
        long id = 100000001;
        Patient patient = patientDao.queryById(id);
        System.out.println(patient);
    }

    @Test
    public void queryAll() throws Exception {
        List<Patient> patientList =  patientDao.queryAll();
        assertEquals(3, patientList.size());
    }

    @Test
    public void insertPatient() throws Exception {
//        Patient patient = new Patient(100000005, 105,100,
////                19,17, 38, 35, 1000);
//        int res = patientDao.insertPatient(patient);
//        System.out.println(res);
    }

    @Test
    public void setFrequency() throws Exception {
        int res = patientDao.setFrequency(100000001, 100);
        assertEquals(1, res);
    }

    @Test
    public void updateHealth() throws Exception {
        int row = patientDao.updateHealth(100000001,103.381, 17.405, 36.620);
        assertEquals(row, 1);
    }

    @Test
    public void updateBound() throws Exception {
//        int row = patientDao.updateBound(100000001, 110, 100, 18, 17, 38, 35);
//        assertEquals(row, 1);
    }

}