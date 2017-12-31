package com.health.service.impl;

import com.health.dao.MonitorDao;
import com.health.dao.PatientDao;
import com.health.dto.InsertExecution;
import com.health.dto.UpdateExecution;
import com.health.entity.Monitor;
import com.health.entity.Patient;
import com.health.service.HealthService;
import com.health.util.SocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HealthServiceImpl implements HealthService{

    @Autowired
    private MonitorDao monitorDao;

    @Autowired
    private PatientDao patientDao;

    public List<Patient> getPatientAll() {
        return patientDao.queryAll();
    }

    public Patient getPatient(long patientId) {
        return patientDao.queryById(patientId);
    }

    @Transactional
    public InsertExecution insertPatient(Patient patient) {
        // TODO 封装返回数据
        if(null != patientDao.queryById(patient.getPatientId())) {
            System.out.println("病例号已存在");
            return new InsertExecution(false, "病例号已存在");
        }
        Monitor monitor = getFreeMonitor();
        if (null == monitor) return new InsertExecution(false, "监护器用完了");;
        patient.setMonitor(monitor);
        monitor.setPatientId(patient.getPatientId());
        monitor.setStatus(1);
        monitorDao.updateMonitor(monitor);

        int effectedRow = patientDao.insertPatient(patient);
        if (effectedRow != 1) return new InsertExecution(false, "增加病员出错");
        // 启动一个Socket连接到监护器，使其开始工作
        boolean result = SocketUtil.sendMsgTo(monitor, "start="+patient.getPatientId());
        if (result) {
            SocketUtil.sendMsgTo(monitor, "setFrequency="+patient.getFrequency()*1000);
            return new InsertExecution(true, patient);
        }
        else {
            return new InsertExecution(false, "与监护器通信出错");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Monitor getFreeMonitor() {
        Monitor monitor = monitorDao.queryFreeMonitor();
        if (monitor != null)   return monitor;
        else {
            System.out.println("监护器用完了!!");
            return null;
        }
    }

    @Transactional
    public UpdateExecution setFrequency(long patientId, int frequency) {
        // 启动一个Socket, 给监护器发送设置频率指令
        Monitor monitor = monitorDao.queryById(patientId);
        boolean result = SocketUtil.sendMsgTo(monitor, "setFrequency="+frequency*1000);
        if (result) {
            int effectedRow = patientDao.setFrequency(patientId, frequency);
            if (1 != effectedRow) {
                return new UpdateExecution(false, "更新数据库失败");
            }
            return new UpdateExecution(true, patientDao.queryById(patientId));
        }
        return new UpdateExecution(false, "监护器用完了");
    }

    @Transactional
    public boolean alarm(long patientId) {
        Monitor monitor = monitorDao.queryById(patientId);
        // 启动一个Socket, 给监护器发送报警指令
        boolean result = SocketUtil.sendMsgTo(monitor, "Alarm");
        return result;
    }

    @Transactional
    public void recordAndUpdateHealth(long patientId, double pressure, double breath, double temperature) {
        // TODO 通过日志记录健康信息
        patientDao.updateHealth(patientId, pressure, breath, temperature);
    }

//    @Transactional
//    public UpdateExecution updateBound(long patientId, double pressureHigh, double pressureLow, double breathHigh, double breathLow, double temperatureHigh, double temperatureLow) {
//        // TODO
//        // 记录不记录？
//        int effectedRow = patientDao.updateBound(patientId, pressureHigh, pressureLow, breathHigh,
//                breathLow, temperatureHigh, temperatureLow);
//        if (1 != effectedRow) {
//            return new UpdateExecution(false, "更新数据库失败");
//        }
//        else {
//            return new UpdateExecution(true, patientDao.queryById(patientId));
//        }
//    }

    @Transactional
    public UpdateExecution updateBound(Long patientId, Patient patient) {
        // TODO 日志记录
        patient.setPatientId(patientId);
        int effectedRow = patientDao.updateBound(patient);
        if (1 != effectedRow) {
            return new UpdateExecution(false, "更新数据库失败");
        }
        else {
            return new UpdateExecution(true, patientDao.queryById(patientId));
        }
    }
}
