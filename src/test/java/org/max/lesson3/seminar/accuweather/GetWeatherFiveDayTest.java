package org.max.lesson3.seminar.accuweather;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.max.lesson3.home.accuweather.weather.Weather;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта accuweather.com")
@Feature("Тестирвоание API Weather API")
public class GetWeatherFiveDayTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест getWeatherFiveDay_shouldReturn - поиск погоды за 5 дней")
    @Description("Данный тест предназначен для получения данных о погоде за 5 дней")
    @Link("https://developer.accuweather.com/accuweather-locations-api/apis")
    @Severity(SeverityLevel.NORMAL)
    @Story("Вызов метода получения погоды за 5 дней")
    @Owner("Кравченко Максим")
    void getWeatherFiveDay_shouldReturn() {

        Weather response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/5day/290396")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().as(Weather.class);

        Assertions.assertEquals(5,response.getDailyForecasts().size());
    }
}
