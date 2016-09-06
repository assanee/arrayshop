package xyz.stepsecret.arrayshop.API;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import xyz.stepsecret.arrayshop.Model.ServicesQueue_Model;

/**
 * Created by stepsecret on 6/9/2559.
 */
public interface ServicesQueue_API {

    @FormUrlEncoded
    @POST("/array/api/v1/servicesqueuebranch")
    public void ServicesQueue_API(@Field("api_key") String api_key, @Field("id_queue") String id_queue, Callback<ServicesQueue_Model> response);

}
