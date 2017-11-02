package com.example.jl.ratatouille.model;

/**
 * Created by jav on 11/2/2017.
 */

public class MSG {
    private boolean error;
    private String msg;

    public MSG() {
    }

    public MSG(boolean error, String msg) {
        super();
        this.error = error;
        this.msg = msg;
    }

    public boolean getError() {
        return error;
    }

   public void setError(boolean error) {
       this.error = error;
   }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
