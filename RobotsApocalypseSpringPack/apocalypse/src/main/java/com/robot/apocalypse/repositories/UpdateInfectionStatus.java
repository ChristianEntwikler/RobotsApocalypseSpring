/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robot.apocalypse.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.transaction.annotation.Transactional;

import com.robot.apocalypse.models.SurvivorInfectedRequestDto;

/**
 *
 * @author cenebeli
 */
public interface UpdateInfectionStatus extends CrudRepository<SurvivorInfectedRequestDto, Long> {

  @Transactional
  @Procedure(procedureName = "proc_update_infectionstatus")
  void updateInfectionStatus(@Param("pid") String id, @Param("pinfectedstatus") String infectedStatus, @Param("pdateupdated") String dateUpdated);


}
