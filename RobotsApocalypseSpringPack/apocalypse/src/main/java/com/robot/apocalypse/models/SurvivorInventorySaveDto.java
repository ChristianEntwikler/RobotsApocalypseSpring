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
@Table(name = "survivor_inventory_master")
public class SurvivorInventorySaveDto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long itemid;

    @Column(name="itemname")
    private String inventoryName;

    @Column(name = "survivorid")
    private String survivorId;

    @Column(name = "dateuploaded")
    private String dateUploaded;

    public SurvivorInventorySaveDto() {
		
    }

    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
	this.itemid = itemid;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getSurvivorId() {
        return survivorId;
    }

    public void setSurvivorId(String survivorId) {
        this.survivorId = survivorId;
    }

    public String getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(String dateUploaded) {
        this.dateUploaded = dateUploaded;
    }
}
