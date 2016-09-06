package xyz.stepsecret.arrayshop.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stepsecret on 6/9/2559.
 */
public class QueueBranch_Model {

    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private String[][] data;


    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String[][] getData() {
        return data;
    }


}
