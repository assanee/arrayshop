package xyz.stepsecret.arrayshop.API;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import xyz.stepsecret.arrayshop.Model.Table_Model;

/**
 * Created by stepsecret on 7/9/2559.
 */
public interface Table_API {

    @GET("/array/api/v1/tabledata")
    public void Get_Table_API(@Query("api_key") String api_key, Callback<Table_Model> response);


}
