package com.health.entity;

// 病员实体
public class Patient {
    private long patientId;
    private double pressure;
    private double breath;
    private double temperature;
    private double pressureHigh;
    private double pressureLow;
    private double breathHigh;
    private double breathLow;
    private double temperatureHigh;
    private double temperatureLow;
    private int frequency;

    private Monitor monitor;

    /**
     * mybatis select语句需要无参构造函数
     */

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getBreath() {
        return breath;
    }

    public void setBreath(double breath) {
        this.breath = breath;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressureHigh() {
        return pressureHigh;
    }

    public void setPressureHigh(double pressureHigh) {
        this.pressureHigh = pressureHigh;
    }

    public double getPressureLow() {
        return pressureLow;
    }

    public void setPressureLow(double pressureLow) {
        this.pressureLow = pressureLow;
    }

    public double getBreathHigh() {
        return breathHigh;
    }

    public void setBreathHigh(double breathHigh) {
        this.breathHigh = breathHigh;
    }

    public double getBreathLow() {
        return breathLow;
    }

    public void setBreathLow(double breathLow) {
        this.breathLow = breathLow;
    }

    public double getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public double getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(double temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", pressure=" + pressure +
                ", breath=" + breath +
                ", temperature=" + temperature +
                ", pressureHigh=" + pressureHigh +
                ", pressureLow=" + pressureLow +
                ", breathHigh=" + breathHigh +
                ", breathLow=" + breathLow +
                ", temperatureHigh=" + temperatureHigh +
                ", temperatureLow=" + temperatureLow +
                ", frequency=" + frequency +
                ", monitor=" + monitor +
                '}';
    }
}
