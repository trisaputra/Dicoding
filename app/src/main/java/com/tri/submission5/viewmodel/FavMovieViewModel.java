package com.tri.submission5.viewmodel;

import android.content.Context;
import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tri.submission5.domain.MovieFav;

import java.util.ArrayList;

public class FavMovieViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MovieFav>> data = new MutableLiveData<>();

    public LiveData<ArrayList<MovieFav>> getData(){
        return data;
    }

//    public void setData(Context context){
//        try{
//            Cursor cursor = context.getContentResolver().query(CONTENT_URI,)
//        }
//    }
}
