package com.example.saba.weatherapp.views;

import android.media.Image;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MainView {
    void renderTemp(@Nullable Double temp);

    void renderCityName(@Nullable String name);

    void renderIcon(@NotNull String img);
}
