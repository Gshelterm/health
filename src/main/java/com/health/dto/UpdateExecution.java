package com.health.dto;

import com.health.entity.Patient;

public class UpdateExecution {
    // 更新是否成功
    // 由service层处理
    private boolean updateSuccess;

    private Patient patient;

    private String info;

    public UpdateExecution(boolean updateSuccess, Patient patient, String info) {
        this.updateSuccess = updateSuccess;
        this.patient = patient;
        this.info = info;
    }

    public UpdateExecution(boolean updateSuccess, Patient patient) {
        this.updateSuccess = updateSuccess;
        this.patient = patient;
    }

    public UpdateExecution(boolean updateSuccess, String info) {
        this.updateSuccess = updateSuccess;
        this.info = info;
    }

    public boolean isUpdateSuccess() {
        return updateSuccess;
    }

    public void setUpdateSuccess(boolean updateSuccess) {
        this.updateSuccess = updateSuccess;
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
