package ru.kpfu.itis.gnt;


import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) {
        WeatherApp app = new WeatherApp();
        try {
            app.init();
        } catch (MalformedURLException e) {
            System.out.println("INVALID URL");
        }
        app.start();
    }

}

