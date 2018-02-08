package com.example.jl.ratatouille.model;

/**
 * Class that represents an error message
 *
 * Created by Jasmine on 11/2/2017.
 */

public class MSG {
    private boolean error;
    private String msg;

    /**
     * the default constructor for MSG
     */
    public MSG() {
    }

    /**
     * constructor for MSG
     * @param error a boolean representing whether or not an error has occurred
     * @param msg the message that appears
     */
    public MSG(boolean error, String msg) {
        super();
        this.error = error;
        this.msg = msg;
    }

    /**
     * Gets the error
     * @return true or false based on whether or not there is an error
     */
    public boolean getError() {
        return error;
    }

    /**
     * Sets the error to true or false
     * @param error the new value for error
     */
   public void setError(boolean error) {
       this.error = error;
   }

    /**
     * gets the message
     * @return the message
     */
    public String getMsg() {
        return msg;
    }

    /**
     * sets a new message
     * @param msg the new message to be set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
