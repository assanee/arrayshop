package xyz.stepsecret.arrayshop.API;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import xyz.stepsecret.arrayshop.Model.QueueBranch_Model;

/**
 * Created by stepsecret on 6/9/2559.
 */
public interface QueueBranch_API {

    @GET("/array/api/v1/queuebranch")
    public void Get_Queue_API(@Query("api_key") String api_key, Callback<QueueBranch_Model> response);

}
