package com.example.listycitylab3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.listycitylab3.adapter.CityAdapter;
import com.example.listycitylab3.bean.City;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener{

    ListView cityListView;
    //    ArrayAdapter<String> cityAdapter;
    CityAdapter cityAdapter;
    List<City> dataList;

    Button deleteCityButton;
    FloatingActionButton addCityButton;


    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityListView = findViewById(R.id.city_list);
        addCityButton = findViewById(R.id.add_city_button);
        deleteCityButton = findViewById(R.id.delete_city_button);





//        String[] cities = {
//                "Edmonton",
//                "Vancouver",
//                "Moscow",
//                "Sydney",
//                "Berlin",
//                "Vienna",
//                "Tokyo",
//                "Beijing",
//                "Osaka",
//                "New Delhi",
//        };
        dataList = City.getDefaultList();
//        dataList = new ArrayList<>();
//        dataList.addAll(Arrays.asList(cities));
        cityListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityAdapter = new CityAdapter(this, dataList);
        cityListView.setAdapter(cityAdapter);


        setupButtons();
        setupListView();
    }

    private void setupButtons() {
        // ADD CITY Button
        addCityButton.setOnClickListener(v -> {

            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });

        // DELETE CITY Button
        deleteCityButton.setOnClickListener(v -> {
            if (selectedPosition == -1) {
                Toast.makeText(this, "Please select a city first", Toast.LENGTH_SHORT).show();
                return;
            }

            if(dataList.isEmpty()) {
                Toast.makeText(this, "No more city to delete", Toast.LENGTH_SHORT).show();
                return;
            }

//            String deletedCity = dataList.get(selectedPosition);
            City deletedCity = dataList.get(selectedPosition);
            dataList.remove(selectedPosition);
            cityAdapter.notifyDataSetChanged();
            selectedPosition = -1;
            cityListView.clearChoices();
            Toast.makeText(this, "Deleted: " + deletedCity, Toast.LENGTH_SHORT).show();
        });

    }

    private void setupListView() {
        cityListView.setOnItemClickListener((parent, view, position, id) -> {
            if(selectedPosition == position) {
                cityListView.setItemChecked(position, false);
                selectedPosition = -1;
                return;
            }
            selectedPosition = position;
            cityListView.setItemChecked(position, true);

        });

    }

    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }
}