package com.health.dao;

import com.health.entity.Monitor;
import org.apache.ibatis.annotations.Param;

public interface MonitorDao {
    /**
     * 根据病员的id查询对应的监护器数据
     * @param patientId
     * @return
     */
    Monitor queryById(Long patientId);

    /**
     * 根据MonitorId 更新 patientId
     */
    int updatePatientId(@Param("monitorId") Long monitorId, @Param("newPatientId") Long newPatientId);


    /**
     * 查询空闲的监护器
     * @return
     */
    Monitor queryFreeMonitor();

    int updateStatus(@Param("monitorId") Long monitorId, @Param("status") int status);

    int updateMonitor(Monitor monitor);
}
