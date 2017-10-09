package com.example.jl.ratatouille.model;

import static android.R.attr.id;

/**
 * Created by Emily Chang on 10/5/2017.
 */

public class Rat {
    private String id;
    private String date;
    private String locType;
    private String incidentZip;
    private String incidentAddress;
    private String city;
    private String borough;
    private String latitude;
    private String longitude;

    public Rat() {
        this("unknown", "unknown", "unknown", "unknown", "unknown", "unknown", "unknown", "unknown", "unknown");
    }

    public Rat(String id, String date, String locType, String incidentZip, String incidentAdress, String city, String borough, String latitude, String longitude) {
        this.id = id;
        this.date = date;
        this.locType = locType;
        this.incidentZip = incidentZip;
        this.incidentAddress = incidentAdress;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getRatId() {
        return id;
    }

    public void setRatId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (date.equals("") || date == null) {
            return;
        }
        this.date = date;
    }

    public String getLocType() {
        return locType;
    }

    public void setLocType(String locType) {
        this.locType = locType;
    }

    public String getIncidentZip() {
        return incidentZip;
    }

    public void setIncidentZip(String incidentZip) {
        this.incidentZip = incidentZip;
    }

    public String getIncidentAddress() {
        return incidentAddress;
    }

    public void setIncidentAddress(String incidentAddress) {
        if (incidentAddress.equals("") || incidentAddress == null) {
            return;
        }
        this.incidentAddress = incidentAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city.equals("") || city == null) {
            return;
        }
        this.city = city;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
