package com.tri.submission5.viewmodel;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tri.submission5.db.AppSchemas;
import com.tri.submission5.domain.MovieFav;
import com.tri.submission5.mapping.MovieMapping;

import java.util.ArrayList;

import static com.tri.submission5.db.AppSchemas.MovieColumns.CONTENT_URI;

public class FavMovieViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MovieFav>> data = new MutableLiveData<>();

    public LiveData<ArrayList<MovieFav>> getData(){
        return data;
    }

    public void setData(Context context){
        try{
            Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, null,  null, null);
            ArrayList<MovieFav> movieFavorite = MovieMapping.mapCursorToArrayList(cursor);
            data.postValue(movieFavorite);
        } catch (Exception e){
            Toast.makeText(context, "Failed FETCH DATABASE", Toast.LENGTH_SHORT).show();
        }
    }
}
