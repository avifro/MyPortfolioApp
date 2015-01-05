package com.avifro;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: froelica
 * Date: 05/01/2015
 * Time: 14:46
 */
public class Main {

    public static void main(String[] args) {
        final MyPortfolioApp app = MyPortfolioApp.getInstance();
        app.run();
    }

}
