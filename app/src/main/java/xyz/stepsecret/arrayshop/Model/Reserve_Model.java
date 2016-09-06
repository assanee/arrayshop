package xyz.stepsecret.arrayshop.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stepsecret on 21/8/2559.
 */
public class Reserve_Model {


    @SerializedName("message")
    private String message ;

    @SerializedName("error")
    private Boolean error ;

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}
