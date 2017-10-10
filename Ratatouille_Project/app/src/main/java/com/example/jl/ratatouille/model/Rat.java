package com.example.jl.ratatouille.model;

import android.os.Parcel;
import android.os.Parcelable;

import static android.R.attr.id;

/**
 * Created by Emily Chang on 10/5/2017.
 */

public class Rat implements Parcelable {
    private String id;
    private String date;
    private String locType;
    private String zip;
    private String address;
    private String city;
    private String borough;
    private String latitude;
    private String longitude;

    public Rat() {
        this("unknown", "unknown", "unknown", "unknown", "unknown", "unknown", "unknown", "unknown", "unknown");
    }

    public Rat(String id, String date, String locType, String zip, String address, String city, String borough, String latitude, String longitude) {
        this.id = id;
        this.date = date;
        this.locType = locType;
        this.zip = zip;
        this.address = address;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.equals("") || address == null) {
            return;
        }
        this.address = address;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(date);
        dest.writeString(locType);
        dest.writeString(zip);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(borough);
        dest.writeString(latitude);
        dest.writeString(longitude);
    }

    public static final Parcelable.Creator<Rat> CREATOR = new Parcelable.Creator<Rat>() {
        public Rat createFromParcel(Parcel in) {
            return new Rat(in);
        }

        public Rat[] newArray(int size) {
            return new Rat[size];
        }
    };

    private Rat(Parcel in) {
        id = in.readString();
        date = in.readString();
        locType = in.readString();
        zip = in.readString();
        address = in.readString();
        city = in.readString();
        borough = in.readString();
        latitude = in.readString();
        longitude = in.readString();
    }
}
