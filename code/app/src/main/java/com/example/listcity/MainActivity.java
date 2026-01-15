package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> datalist;

    Button addCityButton, deleteCityButton, confirmAddButton;
    EditText cityInput;

    boolean deleteMode = false; // STATE FLAG

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.top_bar);
        setSupportActionBar(toolbar);


        cityList = findViewById(R.id.city_list);
        addCityButton = findViewById(R.id.add_city_button);
        deleteCityButton = findViewById(R.id.delete_city_button);
        confirmAddButton = findViewById(R.id.confirm_add_button);
        cityInput = findViewById(R.id.city_input);

        String[] cities = {
                "Edmonton", "Vancouver", "Moscow", "Sydney",
                "Berlin", "Vienna", "Tokyo", "Beijing",
                "Osaka", "New Delhi"
        };

        datalist = new ArrayList<>(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(
                this,
                R.layout.content,
                datalist
        );

        cityList.setAdapter(cityAdapter);

        // ADD CITY BUTTON
        addCityButton.setOnClickListener(v -> {
            deleteMode = false;
            cityInput.setVisibility(View.VISIBLE);
            confirmAddButton.setVisibility(View.VISIBLE);
        });

        // CONFIRM ADD
        confirmAddButton.setOnClickListener(v -> {
            String city = cityInput.getText().toString().trim();

            if (!city.isEmpty()) {
                datalist.add(city);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
            }

            cityInput.setVisibility(View.GONE);
            confirmAddButton.setVisibility(View.GONE);
        });

        // DELETE MODE BUTTON
        deleteCityButton.setOnClickListener(v -> {
            deleteMode = true;
        });

        // LIST ITEM CLICK
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            if (deleteMode) {
                datalist.remove(position);
                cityAdapter.notifyDataSetChanged();
                deleteMode = false; // exit delete mode after one delete
            }
        });
    }
}
