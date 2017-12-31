package com.health.web;

import com.health.dto.HealthResult;
import com.health.dto.InsertExecution;
import com.health.dto.UpdateExecution;
import com.health.entity.Patient;
import com.health.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/health")
public class HealthController {
    @Autowired
    private HealthService healthService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Patient> patientList = healthService.getPatientAll();
        model.addAttribute("list", patientList);
        return "list";          // WEB_INF/jsp/list.jsp
    }

    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public String add() {
        return "add";
    }


    @RequestMapping(value = "/patient", method = RequestMethod.POST)
    @ResponseBody
    public HealthResult<InsertExecution> addPatient(Patient patient) {
        System.out.println(patient);
        InsertExecution insertExecution = healthService.insertPatient(patient);
        if (patient != null)
            return new HealthResult<InsertExecution>(true, insertExecution);
        else return new HealthResult<InsertExecution>(false, "没有输入病员");
    }

    @RequestMapping(value = "/{patientId}/data", method = RequestMethod.POST, produces = "text/plain")
    @ResponseBody
    public String receiveFromMonitor(@PathVariable ("patientId") Long patientId,
                                   @RequestParam ("pressure") double pressure,
                                   @RequestParam ("breath") double breath,
                                   @RequestParam ("temperature") double temperature
                                   ) {
        Patient patient = healthService.getPatient(patientId);
        System.out.println("pressure:" + pressure + " breath:" + breath + " temperature:" + temperature);
        if (patient.getPressureHigh() <= pressure || patient.getPressureLow() >= pressure ||
            patient.getBreathHigh() <= breath || patient.getBreathLow() >= breath ||
            patient.getTemperatureHigh() <= temperature || patient.getTemperatureLow() >= temperature) {
            healthService.alarm(patientId);
            System.out.println("need alarm");
            return "wrong";
        }
        System.out.println("need update");
        healthService.recordAndUpdateHealth(patientId, pressure, breath, temperature);
        return "OK";
    }

    @RequestMapping(value = "/{patientId}/log", method = RequestMethod.GET)
    public String log(@PathVariable ("patientId") Long patientId, Model model) {
        // TODO 引入日志框架
        // 显示某个病人的日志文件
        if (patientId == null) {
            return "redirect:/health/list";
        }
        Patient patient = healthService.getPatient(patientId);
        if (null == patient) {
            return "forward:/health/list";
        }
        model.addAttribute("patient", patient);
        return "log";
    }


    // 测试通过
    @RequestMapping(value = "/{patientId}/bound", method = RequestMethod.POST)
    @ResponseBody
    public HealthResult<UpdateExecution> setBound(@RequestBody Patient patient, @PathVariable("patientId") Long patientId) {
        System.out.println("setBound");
        System.out.println(patient);
        UpdateExecution updateExecution = healthService.updateBound(patientId, patient);
//        UpdateExecution updateExecution = healthService.updateBound(patientId, patient.getPressureHigh(), patient.getPressureLow(),
//                patient.getBreathHigh(), patient.getBreathLow(), patient.getTemperatureHigh(), patient.getTemperatureLow());
        if (null != updateExecution && patient != null && patientId != null) {
            return new HealthResult<UpdateExecution>(true, updateExecution);
        }
        else {
            return new HealthResult<UpdateExecution>(false, "缺少更新条件");
        }
    }

    @RequestMapping(value = "/{patientId}/frequency", method = RequestMethod.POST)
    @ResponseBody
    public HealthResult<UpdateExecution> setFrequency(@PathVariable("patientId") Long patientId,
                             String frequency) {
        // TODO
        UpdateExecution updateExecution = healthService.setFrequency(patientId, Integer.valueOf(frequency));
        if (null != updateExecution && patientId != null && frequency != null) {
            return new HealthResult<UpdateExecution>(true, updateExecution);
        }
        else {
            return new HealthResult<UpdateExecution>(false, "缺少更新条件");
        }
    }
}
