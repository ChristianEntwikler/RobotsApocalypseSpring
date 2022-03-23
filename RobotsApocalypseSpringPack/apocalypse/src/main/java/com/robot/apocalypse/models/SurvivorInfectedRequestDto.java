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
public class SurvivorInfectedRequestDto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long tbid;

    private String id;
    private String infectedStatus;
    private String dateUpdated;

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

    public String getInfectedStatus() {
        return infectedStatus;
    }

    public void setInfectedStatus(String infectedStatus) {
        this.infectedStatus = infectedStatus;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
