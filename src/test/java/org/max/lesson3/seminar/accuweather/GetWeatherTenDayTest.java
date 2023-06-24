package org.max.lesson3.seminar.accuweather;

import io.qameta.allure.*;
import io.restassured.http.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта accuweather.com")
@Feature("Тестирвоание API Weather API")
public class GetWeatherTenDayTest extends AccuweatherAbstractTest{

    @Test
    @DisplayName("Тест get_ten_day_return_401 (негативный) - поиск погоды за 10 дней")
    @Description("Негативный тест - проверка получения погоды за 10 дней")
    @Link("https://developer.accuweather.com/accuweather-locations-api/apis")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Вызов метода получения погоды за 10 дней")
    @Owner("Кравченко Максим")
    void get_ten_day_return_401() {

        given()
                .queryParam("apikey", getApiKey())
                .pathParam("version", "v1")
                .pathParam("location", 290396)
                .when()
                .request(Method.GET,getBaseUrl()+"/forecasts/{version}/daily/10day/{location}")
                .then()
                .statusCode(401);
    }
}
