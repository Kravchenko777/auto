package org.max.lesson3.seminar.accuweather;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.max.lesson3.seminar.accuweather.location.Location;

import javax.management.DescriptorKey;
import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта accuweather.com")
@Feature("Тестирвоание API Location API")
public class GetLocationTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест GetLocationTest - поиск объекта Location")
    @Description("Данный тест предназначен для поиска Location по ключу Samara")
    @Link("https://developer.accuweather.com/accuweather-locations-api/apis")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Получение объекта Location для Samara")
    @Owner("Кравченко Максим")
    void getLocation_autocomplete_returnSamara() {

        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/autocomplete?q=Samara")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(10,response.size());
        Assertions.assertEquals("Samara", response.get(0).getLocalizedName());
    }
}
