package com.example.parkingapplicatie2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ParkingAdapter.OnItemClickListener{

    public static final String EXTRA_FULLNAME = "fullName";
    public static final String EXTRA_FREEPLACES = "freePlaces";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_ADRES = "adres";
    public static final String EXTRA_CONTACTINFO = "contactInfo";
    public static final String EXTRA_TOTALCAP = "totalCap";
    public static final String EXTRA_PARKING = "clickerParking";



    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Parking> parkings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        parseJson();
    }
    private void parseJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://datatank.stad.gent/4/mobiliteit/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<List<Parking>> call = jsonPlaceHolder.getParking();

        call.enqueue((new Callback<List<Parking>>() {
            @Override
            public void onResponse(Call<List<Parking>> call, Response<List<Parking>> response) {
                if(!response.isSuccessful()){return;}
                if(response.isSuccessful() && response.body() != null)
                {
                    parkings = new ArrayList<>(response.body());

                    mAdapter = new ParkingAdapter(parkings);
                    mRecyclerView.setAdapter(mAdapter);
                    ((ParkingAdapter)mAdapter).setOnItemClickListener(MainActivity.this);

                }
            }


            @Override
            public void onFailure(Call<List<Parking>> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                 builder.setMessage(t.getMessage())
                 .setTitle("Error");
                 AlertDialog dialog = builder.create();
                 dialog.show();
            }
        }));
    }

    @Override
    public void onItemClick(int position) {

        Intent detailIntent = new Intent(this,parking_detail.class);
        Parking clickedParking = parkings.get(position);
        detailIntent.putExtra(EXTRA_PARKING, clickedParking);
        startActivity(detailIntent);
    }
}
