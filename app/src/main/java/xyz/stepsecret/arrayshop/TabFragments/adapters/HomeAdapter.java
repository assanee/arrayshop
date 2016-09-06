package xyz.stepsecret.arrayshop.TabFragments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import xyz.stepsecret.arrayshop.API.CancleQueue_API;
import xyz.stepsecret.arrayshop.API.ServicesQueue_API;
import xyz.stepsecret.arrayshop.Config.ConfigData;
import xyz.stepsecret.arrayshop.Model.CancleQueue_Model;
import xyz.stepsecret.arrayshop.Model.ServicesQueue_Model;
import xyz.stepsecret.arrayshop.R;
import xyz.stepsecret.arrayshop.TabFragments.HomeFragment;
import xyz.stepsecret.arrayshop.TabFragments.models.HomeModel;
import xyz.stepsecret.arrayshop.TinyDB.TinyDB;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<HomeModel> moviesList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_date_times, tv_number_book, tv_queue, tv_services, tv_delete, tv_tabletype;
        public ImageView img_person;
        public String id_queue;

        public RestAdapter restAdapter;
        public TinyDB Store_data;

        public MyViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_date_times = (TextView) view.findViewById(R.id.tv_datetimes);
            tv_number_book = (TextView) view.findViewById(R.id.tv_number_book);
            tv_queue = (TextView) view.findViewById(R.id.tv_queue);
            tv_tabletype = (TextView) view.findViewById(R.id.tv_tabletype);

            tv_services = (TextView) view.findViewById(R.id.tv_services);
            tv_delete = (TextView) view.findViewById(R.id.tv_delete);

            img_person = (ImageView) view.findViewById(R.id.img_person);

            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(ConfigData.API).build();

            Store_data = new TinyDB(mContext);


        }
    }
    public void show_failure(String message)
    {
        new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(message)
                .show();
    }


    public HomeAdapter(Context context, List<HomeModel> moviesList) {

        this.moviesList = moviesList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final HomeModel homeModel = moviesList.get(position);

        holder.id_queue = homeModel.getIdqueue();

        holder.tv_name.setText(homeModel.getName());
        holder.tv_date_times.setText(homeModel.getDate());
        holder.tv_number_book.setText(homeModel.getNumberbook());
        holder.tv_queue.setText(homeModel.getQueue());
        holder.tv_tabletype.setText(homeModel.getTable());

        Glide.with(mContext)
                .load(R.drawable.standingupman)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.img_person);

        holder.tv_services.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {


                new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(mContext.getResources().getString(R.string.areyousure))
                        .setCancelText(mContext.getResources().getString(R.string.cancel))
                        .setConfirmText(mContext.getResources().getString(R.string.sure))
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {

                                sDialog.setTitleText(mContext.getResources().getString(R.string.cancelled))
                                        .setConfirmText(mContext.getResources().getString(R.string.close))
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);


                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(final SweetAlertDialog sDialog) {


                                final ServicesQueue_API servicesQueue_api = holder.restAdapter.create(ServicesQueue_API.class);

                                servicesQueue_api.ServicesQueue_API(holder.Store_data.getString("api_key"), holder.id_queue, new Callback<ServicesQueue_Model>() {
                                    @Override
                                    public void success(ServicesQueue_Model result, Response response) {

                                        if(!result.getError()) {

                                            sDialog.setTitleText(mContext.getResources().getString(R.string.deleted))
                                                    .setConfirmText(mContext.getResources().getString(R.string.close))
                                                    .showCancelButton(false)
                                                    .setCancelClickListener(null)
                                                    .setConfirmClickListener(null)
                                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);


                                            HomeFragment.clearData();
                                        }
                                        else
                                        {
                                            show_failure(result.getMessage());
                                            Log.e(" TAG ","error");
                                        }



                                    }

                                    @Override
                                    public void failure(RetrofitError error) {

                                        show_failure(error.getMessage());
                                        Log.e(" TAG ","failure");

                                    }
                                });


                            }
                        })
                        .show();

            }
        });

        holder.tv_delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(mContext.getResources().getString(R.string.areyousure))
                        .setCancelText(mContext.getResources().getString(R.string.cancel))
                        .setConfirmText(mContext.getResources().getString(R.string.sure))
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {

                                sDialog.setTitleText(mContext.getResources().getString(R.string.cancelled))
                                        .setConfirmText(mContext.getResources().getString(R.string.close))
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);


                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(final SweetAlertDialog sDialog) {


                                final CancleQueue_API cancleQueue_api = holder.restAdapter.create(CancleQueue_API.class);

                                cancleQueue_api.CancleQueue_API(holder.Store_data.getString("api_key"), holder.id_queue, new Callback<CancleQueue_Model>() {
                                    @Override
                                    public void success(CancleQueue_Model result, Response response) {

                                        if(!result.getError()) {

                                            sDialog.setTitleText(mContext.getResources().getString(R.string.deleted))
                                                    .setConfirmText(mContext.getResources().getString(R.string.close))
                                                    .showCancelButton(false)
                                                    .setCancelClickListener(null)
                                                    .setConfirmClickListener(null)
                                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);


                                            HomeFragment.clearData();
                                        }
                                        else
                                        {
                                            show_failure(result.getMessage());
                                            Log.e(" TAG ","error");
                                        }



                                    }

                                    @Override
                                    public void failure(RetrofitError error) {

                                        show_failure(error.getMessage());
                                        Log.e(" TAG ","failure");

                                    }
                                });


                            }
                        })
                        .show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
