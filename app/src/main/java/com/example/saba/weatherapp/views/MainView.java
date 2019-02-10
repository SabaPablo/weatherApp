package com.example.saba.weatherapp.views;

import android.media.Image;

import com.example.saba.weatherapp.adapters.WeathersAdapter;
import com.example.saba.weatherapp.model.Weather;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

public interface MainView {
    void renderTemp(@Nullable Integer temp);

    void renderCityName(@Nullable String name);

    void renderIcon(@NotNull String img);

    void renderCountryAndProvince(@Nullable String region, @Nullable String country);

    void renderStatusWeather(@Nullable String description);

    void renderHumidity(@Nullable Integer humidity);

    void renderMinAndMaxTemp(Integer min, Integer max);

    void velocityWind(Integer toInt);

    void renderfiveDays(@NotNull Map<String, Weather> weathers);

}
