package com.example.pablorjd.CheckThisOut.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.pablorjd.CheckThisOut.BuildConfig;
import com.example.pablorjd.CheckThisOut.BusquedaMovies;
import com.example.pablorjd.CheckThisOut.PaginationAdapter;
import com.example.pablorjd.CheckThisOut.R;
import com.example.pablorjd.CheckThisOut.api.MovieApi;
import com.example.pablorjd.CheckThisOut.api.MovieService;
import com.example.pablorjd.CheckThisOut.model.Result;
import com.example.pablorjd.CheckThisOut.model.Movies;
import com.example.pablorjd.CheckThisOut.utils.PaginationScrollListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment{

    private static final String TAG = "HomeFragment";

    public static int PAGE = 1;
    public static String API_KEY = BuildConfig.ApiKey;
    public static String LANGUAGE = "es-CL";
    public static String CATEGORY = "now_playing";

    PaginationAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    RecyclerView rv;
    ProgressBar progressBar;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 2;
    private int currentPage = PAGE_START;

    private MovieService movieService;

    RecyclerViewHeader header;

    private EditText etbusqueda;
    private Button btnBusqueda;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initUI(rootView);
        adapter = new PaginationAdapter(getContext());

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);

        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(adapter);

        rv.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


        //init service and load data
        movieService = MovieApi.getClient().create(MovieService.class);

        loadFirstPage();

        header.attachTo(rv);


        btnBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BusquedaMovies.class);
                intent.putExtra("busqueda",etbusqueda.getText().toString());
                getContext().startActivity(intent);
            }
        });


        return rootView;
    }

    private void initUI(View view){
        header = (RecyclerViewHeader) view.findViewById(R.id.header);
        rv = (RecyclerView) view.findViewById(R.id.main_recycler);
        progressBar = (ProgressBar) view.findViewById(R.id.main_progress);
        etbusqueda = (EditText) view.findViewById(R.id.etBusqueda);
        btnBusqueda = (Button) view.findViewById(R.id.btnBuscar);
    }

    private void loadFirstPage() {
        Log.d(TAG, "loadFirstPage: ");

        callTopRatedMoviesApi().enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                // Got data. Send it to adapter

                List<Result> results = fetchResults(response);
                progressBar.setVisibility(View.GONE);
                adapter.addAll(results);

                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    /**
     * @param response extracts List<{@link Result>} from response
     * @return
     */
    private List<Result> fetchResults(Response<Movies> response) {
        Movies movies = response.body();
        return movies.getResults();
    }

    private void loadNextPage() {
        Log.d(TAG, "loadNextPage: " + currentPage);

        callTopRatedMoviesApi().enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                adapter.removeLoadingFooter();
                isLoading = false;

                List<Result> results = fetchResults(response);
                adapter.addAll(results);

                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    /**
     * Performs a Retrofit call to the top rated movies API.
     * Same API call for Pagination.
     * As {@link #currentPage} will be incremented automatically
     * by @{@link PaginationScrollListener} to load next page.
     */
    private Call<Movies> callTopRatedMoviesApi() {
        return movieService.getMovies(
                CATEGORY,
                API_KEY,
                LANGUAGE,
                PAGE
                );
    }
}
