package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherReportServiceTest {

    @InjectMocks
    private WeatherReportService weatherReportService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testGetWeatherReport() {
        double latitude = 37.8267;
        double longitude = -122.4233;
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + "b0cd9c5c597d8b663710e8ebc1373d92";

        WeatherApiResponse mockResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(15.0);
        main.setHumidity(80.0);
        mockResponse.setMain(main);

        when(restTemplate.getForObject(url, WeatherApiResponse.class)).thenReturn(mockResponse);

        WeatherReport result = weatherReportService.getWeatherReport(latitude, longitude);

        assertEquals(15.0, result.getTemperature());
        assertEquals(80.0, result.getHumidity());
    }
}

