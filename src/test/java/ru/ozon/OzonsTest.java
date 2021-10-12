package ru.ozon;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OzonsTest {

    static final String URL = "https://www.ozon.ru/";

    @CsvSource({
            "Kick, батончик",
            "Pure, щетка"
    })
    @ParameterizedTest(name = "search with values{0}, {1}")
    void searchTestWithCsvSource(String brand, String type){
        open(URL);
        $("[name = text]").setValue(brand + " " + type).pressEnter();
        $("[data-widget = fulltextResultsHeader]").shouldHave(Condition.text(brand + " " + type));
    }

    @EnumSource(MenuItems.class)
    @ParameterizedTest(name = "check urls{0}")
    void checkUrlsWithEnum(MenuItems url){
        open(URL);
        $("[href = " + "\"" + url.getUrl() + "\"" + "]").click();
        WebDriverRunner.url().equals(Condition.text(URL + url.getUrl()));
    }

    static Stream<Arguments> ozonMethod(){
        return Stream.of(
                Arguments.of(
                        "Машинка", "xiaomi"
                )
        );
    }

    @MethodSource("ozonMethod")
    @ParameterizedTest(name = "search of {0}")
    void searchWithMethodSourse(String type, String brand){
        open(URL);
        $("[name = text]").setValue(brand + " " + type).pressEnter();
        $("[data-widget = fulltextResultsHeader]").shouldHave(Condition.text(brand + " " + type));
    }
}
