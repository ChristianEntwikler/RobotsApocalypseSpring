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

import com.robot.apocalypse.models.LocationUpdateRequestDto;

/**
 *
 * @author cenebeli
 */
public interface UpdateLocation extends CrudRepository<LocationUpdateRequestDto, Long> {

  @Transactional
  @Procedure(procedureName = "proc_update_location")
  void updateLocation(@Param("plocationlatitude") String locationlatitude, @Param("plocationlongitude") String locationlongitude, @Param("pdatelocationupdated") String datelocationupdated, @Param("pid") String id);

}
