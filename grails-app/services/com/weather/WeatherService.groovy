package com.weather

import grails.transaction.Transactional
import org.springframework.beans.factory.InitializingBean
import com.weather.model.*

@Transactional
class WeatherService{

    private static final int YEARS = 10;
    private static final int DAYS_IN_A_YEAR = 365;


    public void calculateWeather() {
        log.info "Calculating weather!"

        Planet ferengi = new Planet(1, 500, true);
        Planet betasoide = new Planet(3, 2000, true);
        Planet vulcano = new Planet(5, 1000, false);

        Galaxy galaxy = new Galaxy(ferengi, betasoide, vulcano);

        int days = YEARS * DAYS_IN_A_YEAR;
        for (int i = 0; i < days; i++) {
            def w = new Weather(i, galaxy.getWeather())
            w.save()
            galaxy.nextDay();
        }

    }

    public Weather getWeather(final Integer day) {
        if(!day) return null
        return Weather.findByDay(day)
    }

}
