package xyz.stepsecret.arrayshop.TabFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import xyz.stepsecret.arrayshop.API.QueueBranch_API;
import xyz.stepsecret.arrayshop.Config.ConfigData;
import xyz.stepsecret.arrayshop.Model.QueueBranch_Model;
import xyz.stepsecret.arrayshop.R;
import xyz.stepsecret.arrayshop.TabFragments.adapters.HomeAdapter;
import xyz.stepsecret.arrayshop.TabFragments.models.HomeModel;
import xyz.stepsecret.arrayshop.TinyDB.TinyDB;


public class HomeFragment extends Fragment {

    private static List<HomeModel> HomeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private static HomeAdapter mAdapter;
    private static RestAdapter restAdapter;
    private static TinyDB Store_data;
    private static SwipeRefreshLayout mSwipeRefreshLayout;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.home_fragment, container, false);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(ConfigData.API).build();

        Store_data = new TinyDB(getContext());

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.holo_red, R.color.holo_orange, R.color.holo_green);

        mAdapter = new HomeAdapter(getContext(),HomeList);


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                final HomeModel Homes = HomeList.get(position);
                //Toast.makeText(getContext(), Homes.getId_Home() + " is selected!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                clearData();
            }
        });


        clearData();


        return v;
    }
    public static void clearData() {
        HomeList.clear(); //clear list
        mAdapter.notifyDataSetChanged(); //let your adapter know about the changes and reload view.
        prepareMovieData();
    }

    private static void onRefreshCompleted() {

        mSwipeRefreshLayout.setRefreshing(false);
    }


    private static void prepareMovieData() {

        final QueueBranch_API queueBranch_api = restAdapter.create(QueueBranch_API.class);

        queueBranch_api.Get_Queue_API(Store_data.getString("api_key"), new Callback<QueueBranch_Model>() {
            @Override
            public void success(QueueBranch_Model result, Response response) {

                if(!result.getError() && result.getData().length > 0) {

                    for(int i = 0 ; i < result.getData().length ; i++)
                    {
                        String id_queue = result.getData()[i][0];
                        String name = result.getData()[i][7]+" "+result.getData()[i][8];
                        String date = result.getData()[i][9];
                        String number_book = result.getData()[i][4];
                        String queue = result.getData()[i][5];
                        String table = result.getData()[i][3];

                        HomeModel queues = new HomeModel( id_queue, name, date, number_book, queue, table);
                        HomeList.add(queues);


                    }

                    mAdapter.notifyDataSetChanged();
                    onRefreshCompleted();

                }
                else
                {
                    //show_failure(result.getMessage());
                    onRefreshCompleted();
                    Log.e(" TAG ","error");
                }



            }

            @Override
            public void failure(RetrofitError error) {

                //show_failure(error.getMessage());
                onRefreshCompleted();
                Log.e(" TAG ","failure");

            }
        });




      }





    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private HomeFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final HomeFragment.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


}
