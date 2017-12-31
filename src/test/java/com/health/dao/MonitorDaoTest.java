package com.health.dao;

import com.health.entity.Monitor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
// spring 配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class MonitorDaoTest {


    @Autowired
    private MonitorDao monitorDao;


    @Test
    public void queryById() throws Exception {
        Monitor monitor = monitorDao.queryById(100000000L);
        System.out.println(monitor);
    }

    @Test
    public void updatePatientId() throws Exception {
        int rows = monitorDao.updatePatientId(1L,100000003L);
        System.out.println(rows);
    }

    @Test
    public void queryFreeMonitor() throws Exception {
        Monitor monitor = monitorDao.queryFreeMonitor();
        System.out.println(monitor);
    }

}