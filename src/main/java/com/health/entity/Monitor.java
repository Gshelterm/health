package com.health.entity;

// 监护器实体
public class Monitor {
    private long monitorId;
    private long patientId;
    private String address;
    private int port;
    // -1.停用 0.空闲 1.使用中
    private int status;


    public long getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(long monitorId) {
        this.monitorId = monitorId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "monitorId=" + monitorId +
                ", patientId=" + patientId +
                ", address='" + address + '\'' +
                ", port=" + port +
                ", status=" + status +
                '}';
    }
}
