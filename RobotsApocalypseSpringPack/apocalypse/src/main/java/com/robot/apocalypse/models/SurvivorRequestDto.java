/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robot.apocalypse.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author cenebeli
 */


@Entity
@Table(name = "survivor_master")
public class SurvivorRequestDto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long tbid;

    @Column(unique=true)
    private String id;

    private String idtype;

    @Column(name="fullname")
    private String fullName;

    private String age;
    private String gender;
    
    @Column(name="locationlatitude")
    private String locationLatitude;

    @Column(name="locationlongitude")
    private String locationLongitude;

    @Column(name="infected")
    private String infectedStatus;

    @Column(name="datecreated")
    private String datecreated;

    public SurvivorRequestDto() {
		
    }

    public long getTbid() {
	return tbid;
    }

    public void setTbid(long tbid) {
	this.tbid = tbid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getInfectedStatus() {
        return infectedStatus;
    }

    public void setInfectedStatus(String infectedStatus) {
        this.infectedStatus = infectedStatus;
    }

   
    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

   
}
