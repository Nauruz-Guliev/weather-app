package ru.kpfu.itis.gnt;

import java.net.MalformedURLException;

public abstract class Application {
    public Application(){
        try {
            this.init();
        } catch (MalformedURLException e) {
            System.out.println("URL IS INVALID");
        }
        this.start();
    }

    public abstract void init() throws MalformedURLException;
    public abstract void start();
}
