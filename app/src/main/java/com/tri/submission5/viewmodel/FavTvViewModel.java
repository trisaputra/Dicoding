package com.tri.submission5.viewmodel;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tri.submission5.domain.MovieFav;
import com.tri.submission5.domain.TvFav;
import com.tri.submission5.mapping.TvMapping;

import java.util.ArrayList;

import static com.tri.submission5.db.AppSchemas.TvColumns.CONTENT_URI;

public class FavTvViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TvFav>> data = new MutableLiveData<>();

    public LiveData<ArrayList<TvFav>> getData(){
        return data;
    }

    public void  setData(Context context){
        try {
            Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null);
            ArrayList<TvFav> tvFavs = TvMapping.mapCursorToArrayList(cursor);
            data.postValue(tvFavs);
        } catch (Exception e){
            Toast.makeText(context, "Failed FETCH DATABASE", Toast.LENGTH_SHORT).show();

        }
    }
}
