package com.health.service;

import com.health.dto.InsertExecution;
import com.health.dto.UpdateExecution;
import com.health.entity.Patient;

import java.util.List;

public interface HealthService {
    List<Patient> getPatientAll();

    Patient getPatient(long patientId);

//    boolean insertPatient(long patientId, double pressureHigh, double pressureLow,
//    double breathHigh, double breathLow,
//    double temperatureHigh, double temperatureLow,
//    int frequency);
    InsertExecution insertPatient(Patient patient);

    UpdateExecution setFrequency(long patientId, int frequency);

    boolean alarm(long patientId);

    void recordAndUpdateHealth(long patientId, double pressure, double breath,
                               double temperature);

    UpdateExecution updateBound(Long patientId, Patient patient);
//    UpdateExecution updateBound(long patientId, double pressureHigh, double pressureLow, double breathHigh, double breathLow, double temperatureHigh, double temperatureLow);
}
