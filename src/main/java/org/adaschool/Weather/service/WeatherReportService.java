package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherReportService {

    private static final String API_KEY = "5f58fd4cdbaa237e65828421fdd73d78";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherReportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherReport getWeatherReport(double latitude, double longitude) {
        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;
        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

        WeatherReport report = new WeatherReport();
        WeatherApiResponse.Main main = response.getMain();
        report.setTemperature(main.getTemperature());
        report.setHumidity(main.getHumidity());

        return report;
    }
}
