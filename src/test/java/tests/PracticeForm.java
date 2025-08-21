package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }
    @Test
    void fillFormTest(){
        open("automation-practice-form");
        $("#firstName").setValue("Igor");
        $("#lastName").setValue("Fedorov");
        $("#userEmail").setValue("mimino@mail.ru");
        $("label[for='gender-radio-1']").click();
        $("#userNumber").setValue("8964991077");
        $(".react-datepicker__input-container #dateOfBirthInput").click();
        $("[aria-label='Choose Friday, August 22nd, 2025']").click();
        $("#subjectsInput").setValue("Biology");
        $("#subjectsInput").pressEnter();
        $("[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(new File("/Users/maxkanzas/Downloads/котя.webp"));
        $("#currentAddress").setValue("Sun Andreas 15");
        $("#react-select-3-input").setValue("Uttar Pradesh");
        $("#react-select-3-input").pressEnter();
        $("#react-select-4-input").setValue("Agra");
        $("#react-select-4-input").pressEnter();
        $("#submit").click();
        $(".modal-content").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table").shouldHave(
                text("Igor Fedorov"),
                text("mimino@mail.ru"),
                text("Male"),
                text("8964991077"),
                text("22 August,2025"),
                text("Biology"),
                text("Sports"),
                text("котя.webp"), // или имя вашего файла
                text("Sun Andreas 15"),
                text("Uttar Pradesh Agra")
        );
    }
}
