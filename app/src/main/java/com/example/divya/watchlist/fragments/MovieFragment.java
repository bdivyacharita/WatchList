package com.example.divya.watchlist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.Request;
import com.example.divya.watchlist.R;
import com.example.divya.watchlist.adapters.MovieAdapter;
import com.example.divya.watchlist.model.Movie;
import com.example.divya.watchlist.network.VolleySingleton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divya on 07-05-2017.
 */

public class MovieFragment extends Fragment {
    private static final String ARG_PAGE = "ARG_PAGE";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String URL = "https://api.themoviedb.org/3/movie/popular?api_key=6b7085c6deee4086616c8dae1c1ada12";
    private  VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private  RequestQueue requestQueue;

    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Movie> movieList;


    private int mPage;

    public static MovieFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);

       // args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
        MovieFragment fragment = new MovieFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
       // mParam1 = getArguments().getString(ARG_PARAM1);
       // mParam2 = getArguments().getString(ARG_PARAM2);

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", response.toString());
                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies,container,false);

       /*RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        StringRequest request = new StringRequest(com.android.volley.Request.Method.GET, "https://api.themoviedb.org/3/movie/popular?api_key=6b7085c6deee4086616c8dae1c1ada12", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), "RESPONSE" + response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "ERROR" +error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
        */
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        movieList = new ArrayList<>();
        mAdapter = new MovieAdapter(this.getActivity(), movieList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        prepareAlbums();
        return view;
    }

    private void prepareAlbums() {
        Movie m = new Movie("1");
        movieList.add(m);
        m = new Movie("2");
        movieList.add(m);
        m = new Movie("3");
        movieList.add(m);
        m = new Movie("4");
        movieList.add(m);
        m = new Movie("5");
        movieList.add(m);
    }
}
