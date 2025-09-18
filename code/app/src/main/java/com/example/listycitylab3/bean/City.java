package com.example.listycitylab3.bean;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String cityName;
    private String province;

    private static final String[] cityArray = {
        "Edmonton",
        "Vancouver",
        "Toronto",
        "Hamilton",
        "Denver",
        "Los Angeles"
    };

    private static final String[] provinceArray = {
        "AB",
        "BC",
        "ON",
        "ON",
        "CO",
        "CA"
    };

    public City() {
    }

    public City(String cityName, String province) {
        this.cityName = cityName;
        this.province = province;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityName() {
        return cityName;
    }

    public String getProvince() {
        return province;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", province='" + province + '\'' +
                '}';
    }

    public static List<City> getDefaultList () {
        List<City> cityList  = new ArrayList<>();
        for (int i = 0; i < cityArray.length; i++) {
            cityList.add(new City(cityArray[i], provinceArray[i]));
        }
        return cityList;
    }


}
