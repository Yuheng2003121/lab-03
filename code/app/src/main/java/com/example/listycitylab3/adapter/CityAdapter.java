package com.example.listycitylab3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.listycitylab3.bean.City;
import com.example.listycitylab3.R;

import java.util.List;

public class CityAdapter extends ArrayAdapter<City> {


    public CityAdapter(Context context, List<City> cityList) {

        super(context, 0, cityList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city = getItem(position);

        View view;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.city_item, parent, false);
        } else {
            view = convertView;
        }

        TextView cityView = view.findViewById(R.id.item_city);
        TextView provinceView = view.findViewById(R.id.item_province);

        cityView.setText(city.getCityName());
        provinceView.setText(city.getProvince());


        return view;
    }
}
