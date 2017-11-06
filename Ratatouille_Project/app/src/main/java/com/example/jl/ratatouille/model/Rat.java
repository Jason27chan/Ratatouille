package com.example.jl.ratatouille.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

/**
 * Created by Emily Chang on 10/5/2017.
 */

public class Rat implements Parcelable {
    private static int numRats = 684643545;
    private int id;
    private Date date;
    private String loc_type;
    private int zip;
    private String address;
    private String city;
    private String borough;
    private String lat;
    private Double lng;

    /**
     * Default constructor for Rat with no data passed in
     */
    public Rat() {}

    /**
     * a constructor for the rat
     * with all parameters passed in
     *
     * @param date the date that the rat was located
     * @param loc_type the location type of the rat
     * @param zip the zip code that the rat is located at
     * @param address the address of the rat
     * @param city the city of the rat
     * @param borough the borough of the rat
     * @param lat the latitude at which the rat was found
     * @param lng the longitude at which the rat was found
     */
    public Rat(Date date, String loc_type, int zip, String address, String city, String borough, String lat, Double lng) {
        id = numRats++;
        this.date = date;
        this.loc_type = loc_type;
        this.zip = zip;
        this.address = address;
        this.city = city;
        this.borough = borough;
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * getter for the rat id
     * @return the id of the rat
     */
    public int getRatId() {
        return id;
    }

    /**
     * setter for the rat id
     * @param id the new id for the rat
     */
    public void setRatId(int id) {
        this.id = id;
    }

    /**
     * gets the date that the rat was found
     * @return the date that the rat was found
     */
    public Date getDate() {
        return date;
    }

    /**
     * sets the date at which the rat was found
     * @param date the date at which the rat was spotted
     */
    public void setDate(Date date) {
        if (date.equals("") || date == null) {
            return;
        }
        this.date = date;
    }

    /**
     * gets the location type of the rat
     * @return the location type of the rat
     */
    public String getLocType() {
        return loc_type;
    }

    /**
     * sets the location type for the rat
     * @param locType the new location type for the rat
     */
    public void setLocType(String locType) {
        this.loc_type = locType;
    }

    /**
     * gets the zip code of the rat
     * @return the zip code of the rat
     */
    public int getZip() {
        return zip;
    }

    /**
     * sets the new zip code for the rat
     * @param zip the new zip code for the rat
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * gets the address of the rat
     * @return the address of the rat
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets the new address for the rat
     * @param address the new address for the rat
     */
    public void setAddress(String address) {
        if (address.equals("") || address == null) {
            return;
        }
        this.address = address;
    }

    /**
     * gets the city that the rat is located in
     * @return the city that the rat is located in
     */
    public String getCity() {
        return city;
    }

    /**
     * sets the new city that the rat is located in
     * @param city the new city that the rat is located in
     */
    public void setCity(String city) {
        if (city.equals("") || city == null) {
            return;
        }
        this.city = city;
    }

    /**
     * gets the borough that the rat is in
     * @return the borough the rat is in
     */
    public String getBorough() {
        return borough;
    }

    /**
     * sets the borough that the rat is in
     * @param borough the new borough that the rat is in
     */
    public void setBorough(String borough) {
        this.borough = borough;
    }

    /**
     * gets the latitude at which the rat is at
     * @return the latitude at which the rat is at
     */
    public String getLatitude() {
        return lat;
    }

    /**
     * sets the new latitude at which the rat is at
     * @param latitude the new latitude of the rat
     */
    public void setLatitude(String latitude) {
        this.lat = latitude;
    }

    /**
     * gets the longitude at which the rat is at
     * @return the longitude at which the rat is at
     */
    public Double getLongitude() {
        return lng;
    }

    /**
     * sets the new longitude at which the rat is at
     * @param longitude the new longitude at which the rat is at
     */
    public void setLongitude(Double longitude) {
        this.lng = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(date.getTime());
        dest.writeString(loc_type);
        dest.writeInt(zip);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(borough);
        dest.writeString(lat);
        dest.writeDouble(lng);
    }

    public static final Parcelable.Creator<Rat> CREATOR = new Parcelable.Creator<Rat>() {
        /**
         * creating a new rat from parcel
         * @param in the parcel in which the object's data is stored
         * @return new instance of the parcelable class
         */
        public Rat createFromParcel(Parcel in) {
            return new Rat(in);
        }

        /**
         * creates a new array that can hold Rat objects that is a specific size
         * @param size size of the array
         * @return the new Rat array of the specified size
         */
        public Rat[] newArray(int size) {
            return new Rat[size];
        }
    };

    /**
     * creates the new instance of the parcelable class
     * @param in the parcel in which the data is to be stored
     */
    private Rat(Parcel in) {
        id = in.readInt();
        date = new Date(in.readLong());
        loc_type = in.readString();
        zip = in.readInt();
        address = in.readString();
        city = in.readString();
        borough = in.readString();
        lat = in.readString();
        lng = in.readDouble();
    }
}
