package com.health.dto;

import com.health.entity.Patient;

public class InsertExecution {
    // 插入是否成功
    // 由service层处理
    private boolean insertSuccess;

    private Patient patient;

    private String info;

    public InsertExecution(boolean insertSuccess, Patient patient, String info) {
        this.insertSuccess = insertSuccess;
        this.patient = patient;
        this.info = info;
    }

    public InsertExecution(boolean insertSuccess, Patient patient) {
        this.insertSuccess = insertSuccess;
        this.patient = patient;
    }

    public InsertExecution(boolean insertSuccess, String info) {
        this.insertSuccess = insertSuccess;
        this.info = info;
    }

    public boolean isInsertSuccess() {
        return insertSuccess;
    }

    public void setInsertSuccess(boolean insertSuccess) {
        this.insertSuccess = insertSuccess;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
