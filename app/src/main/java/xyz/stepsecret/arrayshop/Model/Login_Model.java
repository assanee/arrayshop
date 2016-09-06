package xyz.stepsecret.arrayshop.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Assanee on 8/7/2558.
 */
public class Login_Model {

    @SerializedName("error")
    private Boolean error ;

    @SerializedName("id_shop")
    private String id_shop;

    @SerializedName("shopname")
    private String shopname;

    @SerializedName("id_branch")
    private String id_branch;

    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("branchname")
    private String branchname;

    @SerializedName("mobile_number")
    private String mobile_number;

    @SerializedName("phone_number")
    private String phone_number;

    @SerializedName("logo")
        private String logo;

    @SerializedName("message")
        private String message;


    public Boolean getError() {
        return error;
    }

    public String getIdshop() {
        return id_shop;
    }

    public String getShopname() {
        return shopname;
    }

    public String getIdbranch() {
        return id_branch;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBranchname() {
        return branchname;
    }

    public String getMobilenumber() {
        return mobile_number;
    }

    public String getPhonenumber() {
        return phone_number;
    }

    public String getLogo() {
            return logo;
        }


    public String getMessage() {
            return message;
        }


}
