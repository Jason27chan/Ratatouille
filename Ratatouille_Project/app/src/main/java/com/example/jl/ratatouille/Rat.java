package com.example.jl.ratatouille;

/**
 * Created by Emily Chang on 10/5/2017.
 */

public class Rat {
    private int id;
    private int date;
    private String locType;
    private int incidentZip;
    private String incidentAddress;
    private String city;
    private String borough;
    private int latitude;
    private int longitude;

    public Rat(int id, int date, String locType, int incidentZip, String incidentAdress, String city, String borough, int latitude, int longitude) {
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

    public int getRatId() {
        return id;
    }

    public int getDate() {
        return date;
    }

    public String getLocType() {
        return locType;
    }

    public int getIncidentZip() {
        return incidentZip;
    }

    public String getIncidentAddress() {
        return incidentAddress;
    }

    public String city() {
        return city;
    }

    public String getBorough() {
        return borough;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }
}
