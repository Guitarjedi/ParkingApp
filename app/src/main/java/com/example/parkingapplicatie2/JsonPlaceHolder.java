package com.example.parkingapplicatie2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolder {
    @GET("bezettingparkingsrealtime.json")
    Call<List<Parking>> getParking();
}
