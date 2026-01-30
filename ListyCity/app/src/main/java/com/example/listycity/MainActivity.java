package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // Declaring variables for reference later
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);
        // Initialize new UI components
        Button addCityBtn = findViewById(R.id.add_city_button);
        Button deleteCityBtn = findViewById(R.id.delete_city_button);
        Button confirmBtn = findViewById(R.id.confirm_button);
        EditText cityEditText = findViewById(R.id.add_city_editText);
        LinearLayout addPanel = findViewById(R.id.add_panel);
        addCityBtn.setOnClickListener(v->addPanel.setVisibility(View.VISIBLE));

        confirmBtn.setOnClickListener(v ->
        {
            String newCity = cityEditText.getText().toString();
            if (!newCity.isEmpty())
            {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                cityEditText.setText(""); // Clear input
                addPanel.setVisibility(View.GONE); // Hide panel
            }
        });

        cityList.setOnItemClickListener((parent, view, position, id) ->
        {
            selectedPosition = position;
        });

        deleteCityBtn.setOnClickListener(v ->
        {
            if (selectedPosition != -1)
            {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
