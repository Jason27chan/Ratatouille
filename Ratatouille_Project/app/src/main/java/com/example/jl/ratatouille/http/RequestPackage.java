package com.example.jl.ratatouille.http;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jav on 10/24/2017.
 */

public class RequestPackage implements Parcelable {
    private String endPoint;
    private String method = "GET";
    private Map<String, String> params = new HashMap<>();

    /**
     * Returns the end point of the request package
     * @return end point
     */
    public String getEndpoint() {
        return endPoint;
    }

    /**
     * Sets the request package's end point as the param value
     * @param endPoint the new end point value
     */
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * gets the method variable
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * sets the method variable to the string parameter
     * @param method the new mathod variable
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * gets the parameters for the request package
     * @return the parameters for the request package
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * sets the params for the request package
     * @param params the new params
     */
    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    /**
     * sets the param's value to the new key and values
     * @param key the key for the hash map
     * @param value the value to enter
     */
    public void setParam(String key, String value) {
        params.put(key, value);
    }

    /**
     * go through the params to find the encoded params value of
     * UTF-8
     * @return the string of the key and value of the encoded params
     */
    public String getEncodedParams() {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            String value = null;
            try {
                value = URLEncoder.encode(params.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(key).append("=").append(value);
        }
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.endPoint);
        dest.writeString(this.method);
        dest.writeInt(this.params.size());
        for (Map.Entry<String, String> entry : this.params.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    /**
     * default constructor
     */
    public RequestPackage() {
    }

    /**
     * reads in the the parcel to create anew request package
     * @param in the parcel of data that creates the request package
     */
    protected RequestPackage(Parcel in) {
        this.endPoint = in.readString();
        this.method = in.readString();
        int paramsSize = in.readInt();
        this.params = new HashMap<String, String>(paramsSize);
        for (int i = 0; i < paramsSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.params.put(key, value);
        }
    }

    /**
     * Creates a new parcelable
     */
    public static final Parcelable.Creator<RequestPackage> CREATOR = new Parcelable.Creator<RequestPackage>() {
        @Override
        public RequestPackage createFromParcel(Parcel source) {
            return new RequestPackage(source);
        }

        @Override
        public RequestPackage[] newArray(int size) {
            return new RequestPackage[size];
        }
    };
}
