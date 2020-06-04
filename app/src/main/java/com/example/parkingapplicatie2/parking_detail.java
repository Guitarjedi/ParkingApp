package com.example.parkingapplicatie2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.parkingapplicatie2.MainActivity.EXTRA_PARKING;

public class parking_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_detail);

        Intent intent = getIntent();
        Parking p = (Parking)intent.getSerializableExtra(EXTRA_PARKING);

        String fullName = p.getName();
        String name = p.getDescription();
        String contactInfo = p.getContactInfo();
        int freePlaces = p.getParkingStatus().getAvailableCapacity();
        String address = p.getAddress();
        int totalCap = p.getParkingStatus().getTotalCapacity();


        TextView fullNameTV = findViewById(R.id.fullName);
        TextView nameTV = findViewById(R.id.name);
        TextView contactTV = findViewById(R.id.contactInfo);
        TextView freePlacesTV = findViewById(R.id.numberOfPlaces);
        TextView addressTV = findViewById(R.id.adres);
        TextView totalCapTV = findViewById(R.id.totalCap);


        fullNameTV.setText(fullName);
        nameTV.setText(name);
        contactTV.setText(contactInfo);
        freePlacesTV.setText(Integer.toString(freePlaces));
        addressTV.setText(address);
        totalCapTV.setText(Integer.toString(totalCap));

        double percentageFree = (double)freePlaces/(double)totalCap;
        if(percentageFree > 0.75){int green = Color.rgb(0, 128, 0); freePlacesTV.setBackgroundColor(green); }
        else if(percentageFree < 0.05){freePlacesTV.setBackgroundColor(Color.RED);}
        else{ int orange = Color.rgb(255, 165, 0);
            freePlacesTV.setBackgroundColor(orange); }

        addressTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] addressArray  = address.split(" ");
                String addressURL = addressArray[0]+"+"+ addressArray[1].split("\n")[0]+ "%2C+"+ addressArray[2]+"%2C";
                String uri = "https://www.google.com/maps/search/?api=1&query="  + addressURL;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });

    }
}
