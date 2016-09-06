package xyz.stepsecret.arrayshop.API;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import xyz.stepsecret.arrayshop.Model.CancleQueue_Model;

/**
 * Created by stepsecret on 6/9/2559.
 */
public interface CancleQueue_API {

    @FormUrlEncoded
    @POST("/array/api/v1/canclequeuebranch")
    public void CancleQueue_API(@Field("api_key") String api_key, @Field("id_queue") String id_queue, Callback<CancleQueue_Model> response);


}
