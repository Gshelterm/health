package com.health.dao;

import com.health.entity.Patient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PatientDao {
    /**
     * 根据病员id查询病人
     * @param id
     * @return
     */
    Patient queryById(long id);

    List<Patient> queryAll();

    /**
     * 增加一个病人，响应病人设置id
     * @param
     * @return
     */
//    int insertPatient(@Param("patientId") long patientId, @Param("pressureHigh") double pressureHigh, @Param("pressureLow") double pressureLow,
//                      @Param("breathHigh") double breathHigh, @Param("breathLow") double breathLow,
//                      @Param("temperatureHigh") double temperatureHigh, @Param("temperatureLow") double temperatureLow,
//                      @Param("frequency") int frequency);
    int insertPatient(Patient patient);

    /**
     * 根据监护器传送的数据更新病员的身体情况参数，service层记录日志
     * @param patientId
     * @param pressure
     * @param breath
     * @param temperature
     * @return
     */
    int updateHealth(@Param("patientId") long patientId, @Param("pressure") double pressure, @Param("breath")
            double breath, @Param("temperature") double temperature);

    /**
     * 根据id设置病人身体参数的阈值
     * @return
     */
//    int updateBound(@Param("patientId") long patientId, @Param("pressureHigh") double pressureHigh, @Param("pressureLow") double pressureLow,
//                 @Param("breathHigh") double breathHigh, @Param("breathLow") double breathLow,
//                 @Param("temperatureHigh") double temperatureHigh, @Param("temperatureLow") double temperatureLow);

    int updateBound(Patient patient);

    int setFrequency(@Param("patientId") long patientId, @Param("frequency") int frequency);
}
