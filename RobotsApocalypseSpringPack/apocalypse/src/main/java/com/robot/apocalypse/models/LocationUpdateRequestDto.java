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
public class LocationUpdateRequestDto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long tbid;

    private String id;

    @Column(name = "locationlatitude")
    private String locationLatitude;

    @Column(name = "locationlongitude")
    private String locationLongitude;

    @Column(name = "datelocationupdated")
    private String dateLocationUpdated;

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

    public String getDateLocationUpdated() {
        return dateLocationUpdated;
    }

    public void setDateLocationUpdated(String dateLocationUpdated) {
        this.dateLocationUpdated = dateLocationUpdated;
    }
}
