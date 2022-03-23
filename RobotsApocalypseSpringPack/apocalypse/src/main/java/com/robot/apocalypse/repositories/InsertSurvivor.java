/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robot.apocalypse.repositories;

import com.robot.apocalypse.models.SurvivorRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author cenebeli
 */
public interface InsertSurvivor extends JpaRepository<SurvivorRequestDto, Long> {
}
