/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robot.apocalypse.repositories;

import com.robot.apocalypse.models.SurvivorRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Sort;

/**
 *
 * @author cenebeli
 */
public interface ListSurvivors extends JpaRepository<SurvivorRequestDto, Long>{

        @Override
	public List<SurvivorRequestDto> findAll(Sort sort);

}
