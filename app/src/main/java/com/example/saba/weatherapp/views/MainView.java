package com.example.saba.weatherapp.views;

import android.media.Image;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MainView {
    void renderTemp(@Nullable Integer temp);

    void renderCityName(@Nullable String name);

    void renderIcon(@NotNull String img);

    void renderCountryAndProvince(@Nullable String region, @Nullable String country);

    void renderStatusWeather(@Nullable String description);

    void renderHumidity(@Nullable Integer humidity);

    void renderMinAndMaxTemp(Integer min, Integer max);

    void velocityWind(Integer toInt);
}
