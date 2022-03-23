/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robot.apocalypse.controller;

import com.robot.apocalypse.models.SurvivorRequestDto;
import com.robot.apocalypse.models.SurvivorRequestsDto;
import com.robot.apocalypse.models.SurvivorInventorySaveDto;
import com.robot.apocalypse.models.SurvivorInventoryRequestDto;
import com.robot.apocalypse.models.LocationUpdateRequestDto;
import com.robot.apocalypse.models.SurvivorInfectedRequestDto;
import com.robot.apocalypse.models.ResponseDto;
import com.robot.apocalypse.models.RobotsDto;
import com.robot.apocalypse.models.RobotDto;
import com.robot.apocalypse.models.SearchRobotsRequestDto;
import com.robot.apocalypse.models.PercentageResponseDto;
import com.robot.apocalypse.repositories.InsertSurvivor;
import com.robot.apocalypse.repositories.InsertInventory;
import com.robot.apocalypse.repositories.UpdateLocation;
import com.robot.apocalypse.repositories.UpdateInfectionStatus;
import com.robot.apocalypse.repositories.ListSurvivors;
import com.robot.apocalypse.util.Util;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import java.text.SimpleDateFormat;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author cenebeli
 */
@RestController
@RequestMapping("/RobotsApocalypse/services/api")
public class RobotsSurvivorsApiController {

@Value("${robotSys}")
private   String robotUrl;

@Autowired InsertSurvivor addSurvivor;
@Autowired InsertInventory addInventory;
@Autowired UpdateLocation changeLocation;
@Autowired UpdateInfectionStatus changeInfectionStatus;
@Autowired ListSurvivors fetchSurvivors;

@RequestMapping(value ="/insertSurvivor",produces = "application/json",method=RequestMethod.POST)
public ResponseEntity<ResponseDto>  InsertSurvivors(@RequestBody SurvivorRequestsDto request)
        {

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
            String timestampz=sdf2.format(timestamp2);
            
            ResponseDto resp = new ResponseDto();
            try
            {
            if((request.getId()!=null && !request.getId().isEmpty()) && (request.getLocationLatitude()!=null && !request.getLocationLatitude().isEmpty()) && (request.getLocationLongitude()!=null && !request.getLocationLongitude().isEmpty()) && (request.getAge()!=null && !request.getAge().isEmpty()) && (request.getGender()!=null && !request.getGender().isEmpty()) && (request.getIdtype()!=null && !request.getIdtype().isEmpty()) && (request.getInfectedStatus()!=null && !request.getInfectedStatus().isEmpty()) && (request.getName()!=null && !request.getName().isEmpty()))
            {
            SurvivorRequestDto req = new SurvivorRequestDto();
            System.out.println("request.getId(): " + request.getId());
            req.setId(request.getId());
            req.setIdtype(request.getIdtype());
            req.setFullName(request.getName());
            req.setAge(request.getAge());
            req.setGender(request.getGender());
            req.setLocationLatitude(request.getLocationLatitude());
            req.setLocationLongitude(request.getLocationLongitude());
            req.setInfectedStatus(request.getInfectedStatus());
            req.setDatecreated(timestampz);

            addSurvivor.save(req);
            if(req.getInfectedStatus().equalsIgnoreCase("Yes"))
            {
            changeInfectionStatus.updateInfectionStatus(req.getId(), req.getInfectedStatus(), req.getDatecreated());
            }
            
            resp.setResponseStatus("00");
            resp.setResponseMessage("Survivor data submitted successfully");
            
            try
            {
            if(request.getInventory()!=null && request.getInventory().size() > 0)
                    {
                    for(SurvivorInventoryRequestDto reqInvent : request.getInventory())
                    {
                    SurvivorInventorySaveDto reqi = new SurvivorInventorySaveDto();
                    reqi.setInventoryName(reqInvent.getInventoryName());
                    reqi.setSurvivorId(req.getId());
                    reqi.setDateUploaded(timestampz);

                     addInventory.save(reqi);  
                    }
                    }
                    
            }
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<ResponseDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
            
            return ResponseEntity.ok().body(resp);
            }
            else
            {
                resp.setResponseStatus("02");
                resp.setResponseMessage("All values required");
                return ResponseEntity.ok().body(resp);
            }
            
            }
            catch(DataIntegrityViolationException dup)
		{
			ResponseDto reply = new ResponseDto();
			reply.setResponseStatus("94");
			reply.setResponseMessage("Data flagged as duplicate");			
			return new ResponseEntity<ResponseDto>(reply, HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<ResponseDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
            

            

        }

@RequestMapping(value ="/updateLocation",produces = "application/json",method=RequestMethod.POST)
public ResponseEntity<ResponseDto>  UpdateLocation(@RequestBody LocationUpdateRequestDto request)
        {

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
            String timestampz=sdf2.format(timestamp2);
            
            ResponseDto resp = new ResponseDto();
            try
            {
            if((request.getId()!=null && !request.getId().isEmpty()) && (request.getLocationLatitude()!=null && !request.getLocationLatitude().isEmpty()) && (request.getLocationLongitude()!=null && !request.getLocationLongitude().isEmpty()))
            {
            request.setDateLocationUpdated(timestampz);
            changeLocation.updateLocation(request.getLocationLatitude(), request.getLocationLongitude(), request.getDateLocationUpdated(), request.getId());
            resp.setResponseStatus("00");
            resp.setResponseMessage("Survivor location updated successfully");
            
            return ResponseEntity.ok().body(resp);
            }
            else
            {
                resp.setResponseStatus("02");
                resp.setResponseMessage("All values required");
                return ResponseEntity.ok().body(resp);
            }
            
            }
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<ResponseDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        }

@RequestMapping(value ="/flagInfected",produces = "application/json",method=RequestMethod.POST)
public ResponseEntity<ResponseDto>  FlagInfected(@RequestBody SurvivorInfectedRequestDto request)
        {

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
            String timestampz=sdf2.format(timestamp2);
            
            ResponseDto resp = new ResponseDto();
            try
            {
            if((request.getId()!=null && !request.getId().isEmpty()) && (request.getInfectedStatus()!=null && !request.getInfectedStatus().isEmpty()))
            {
            request.setDateUpdated(timestampz);
            changeInfectionStatus.updateInfectionStatus(request.getId(), request.getInfectedStatus(), request.getDateUpdated());
            resp.setResponseStatus("00");
            resp.setResponseMessage("Survivor infection status updated successfully");
            
            return ResponseEntity.ok().body(resp);
            }
            else
            {
                resp.setResponseStatus("02");
                resp.setResponseMessage("All values required");
                return ResponseEntity.ok().body(resp);
            }
            
            }
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<ResponseDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        }


@RequestMapping(value ="/listInfected",produces = "application/json",method=RequestMethod.GET)
public ResponseEntity<List<SurvivorRequestDto>>  FlagInfected()
        {     
            List<SurvivorRequestDto> survivors = fetchSurvivors.findAll(Sort.by(Sort.Direction.ASC, "infectedStatus"));
            List<SurvivorRequestDto> resp = survivors.stream().filter(p -> (p.getInfectedStatus().equals("Yes"))).collect(Collectors.toList());
            return ResponseEntity.ok().body(resp);
            
        }

@RequestMapping(value ="/listNoninfected",produces = "application/json",method=RequestMethod.GET)
public ResponseEntity<List<SurvivorRequestDto>>  FlagNonInfected()
        {     
            List<SurvivorRequestDto> survivors = fetchSurvivors.findAll(Sort.by(Sort.Direction.ASC, "infectedStatus"));
            List<SurvivorRequestDto> resp = survivors.stream().filter(p -> (p.getInfectedStatus().equals("No"))).collect(Collectors.toList());
            return ResponseEntity.ok().body(resp);
            
        }

@RequestMapping(value ="/percentageInfected",produces = "application/json",method=RequestMethod.GET)
public ResponseEntity<PercentageResponseDto>  PercentageInfected()
        {   
            PercentageResponseDto resp = new PercentageResponseDto();
            List<SurvivorRequestDto> survivors = fetchSurvivors.findAll(Sort.by(Sort.Direction.ASC, "infectedStatus"));
            List<SurvivorRequestDto> res = survivors.stream().filter(p -> (p.getInfectedStatus().equals("Yes"))).collect(Collectors.toList());           
            Double percentage = Double.parseDouble(String.valueOf(res.size())) / Double.parseDouble(String.valueOf(survivors.size()));
            resp.setPercentageValue(String.format("%.2f", percentage));
            return ResponseEntity.ok().body(resp);
            
        }

@RequestMapping(value ="/percentageNoninfected",produces = "application/json",method=RequestMethod.GET)
public ResponseEntity<PercentageResponseDto>  PercentageNonInfected()
        {   
            PercentageResponseDto resp = new PercentageResponseDto();
            List<SurvivorRequestDto> survivors = fetchSurvivors.findAll(Sort.by(Sort.Direction.ASC, "infectedStatus"));
            List<SurvivorRequestDto> res = survivors.stream().filter(p -> (p.getInfectedStatus().equals("No"))).collect(Collectors.toList());
            Double percentage = Double.parseDouble(String.valueOf(res.size())) / Double.parseDouble(String.valueOf(survivors.size()));
            resp.setPercentageValue(String.format("%.2f", percentage));
            return ResponseEntity.ok().body(resp);
            
        }

@RequestMapping(value ="/listRobots",produces = "application/json",method=RequestMethod.GET)
public ResponseEntity< List<RobotDto>>  ListRobots()
        {    
            RobotsDto resp =  new Util().ListRobots(robotUrl);
            List<RobotDto> ret = new ArrayList<RobotDto>();
            if(resp.getData() != null && resp.getData().size() > 0)
            {
            ret = resp.getData().stream().sorted(Comparator.comparing(RobotDto::getCategory)).collect(Collectors.toList());
            }
            return ResponseEntity.ok().body(ret);
            
        }

@RequestMapping(value ="/searchRobots",produces = "application/json",method=RequestMethod.POST)
public ResponseEntity< List<RobotDto>>  SearchRobots(@RequestBody SearchRobotsRequestDto request)
        {    
            RobotsDto ret = new Util().ListRobots(robotUrl);
        List<RobotDto> resp = ret.getData().stream().filter(p -> ((request.getCategory()!=null && !request.getCategory().isEmpty()) && p.getCategory().equals(request.getCategory()))).collect(Collectors.toList());
            return ResponseEntity.ok().body(resp);
            
        }

}
