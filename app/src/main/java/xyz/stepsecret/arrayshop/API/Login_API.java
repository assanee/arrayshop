package xyz.stepsecret.arrayshop.API;

/**
 * Created by Assanee on 8/7/2558.
 */

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import xyz.stepsecret.arrayshop.Model.Login_Model;


public interface Login_API {

    @FormUrlEncoded
    @POST("/array/api/v1/branchlogin")
    public void SignIN(@Field("username") String username, @Field("password") String password, Callback<Login_Model> response);


}
