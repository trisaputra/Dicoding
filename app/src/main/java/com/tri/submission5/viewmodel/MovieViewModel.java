package com.tri.submission5.viewmodel;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tri.submission5.domain.MovieTvModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<MovieTvModels>> data = new MutableLiveData<>();
    private static final String API_KEY = "9da414da76778362a273d84187e76699";

    public void setData(@Nullable String cari) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieTvModels> dataList = new ArrayList<>();
        String url;

        if (cari == null) {
            url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=en-US";
        } else {
            url = "https://api.themoviedb.org/3/search/movie?api_key="+API_KEY+"&language=en-US&cari="+cari;
        }
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject resps = new JSONObject(result);
                    JSONArray list = resps.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject data = list.getJSONObject(i);
                        MovieTvModels model = new MovieTvModels();
                        model.setId(data.getInt("id"));
                        model.setTitle(data.getString("title"));
                        model.setReleaseDate(data.getString("release_date"));
                        model.setOverview(data.getString("overview"));
                        model.setPoster(data.getString("poster_path"));
                        model.setRating(String.valueOf(data.getDouble("vote_average")));
                        dataList.add(model);
                    }
                    data.postValue(dataList);
                } catch (JSONException e) {
                    Log.d("REPOSITORY", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("REPOSITORY", "Failed");
            }
        });
    }

    public LiveData<ArrayList<MovieTvModels>> getData() {
        return data;
    }

}
