package com.rk.movieslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rk.movieslist.Adapters.MovieListAdapter;
import com.rk.movieslist.Application.MoviesList;
import com.rk.movieslist.Interfaces.OnMovieSearch;
import com.rk.movieslist.Models.MovieList;
import com.rk.movieslist.Utils.Urls;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,OnMovieSearch {

    private RecyclerView imageList;
    private EditText searchedMovieName;
    private Button search;
    private OnMovieSearch onMovieSearch;
    public static LinearLayout dragPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        onMovieSearch = (OnMovieSearch) this;
    }

    private void initializeViews() {
        dragPlace = (LinearLayout) findViewById(R.id.dragPlace);
        searchedMovieName = (EditText) findViewById(R.id.searchedMovieName);
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(this);

        imageList = (RecyclerView) findViewById(R.id.imageList);
        imageList.setNestedScrollingEnabled(false);
        LinearLayoutManager llImage = new LinearLayoutManager(this);
        //llImage.setAutoMeasureEnabled(true);
        llImage.setOrientation(LinearLayoutManager.HORIZONTAL);
        imageList.setLayoutManager(llImage);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.search:
                String searchedMovie = searchedMovieName.getText().toString();
                if(searchedMovie.equals("")){
                    Toast.makeText(this,"Type a valid movie name",Toast.LENGTH_LONG).show();
                }else {
                    getResultsForSearchedMoview(searchedMovie);
                }

        }
    }

    private void getResultsForSearchedMoview(String searchedMovie) {
        String url = Urls.MOVIE_SEARCH_URL+ searchedMovie;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response != null){
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    MovieList movieList = gson.fromJson(response.toString(),MovieList.class);
                    onMovieSearch.onResult(movieList);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("MovieList", "volleyerroe");
                Toast.makeText(getApplicationContext(),"No Internet", Toast.LENGTH_LONG).show();
            }
        });

        MoviesList.getInstance().getRequestQueue().add(jsonObjectRequest);
    }


    @Override
    public void onResult(MovieList movieList) {
        if(movieList == null){
            Toast.makeText(this,"No Data", Toast.LENGTH_LONG).show();
        }else {
            MovieListAdapter adapter = new MovieListAdapter(this,movieList);
            imageList.setAdapter(adapter);
        }
    }
}
