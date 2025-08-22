package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }
    @Test
    void studentRegistrationFormShouldBeSubmittedSuccessfully() {
        open("automation-practice-form");

        // Удаляем мешающие элементы
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        // Заполнение формы
        $("#firstName").setValue("Igor");
        $("#lastName").setValue("Fedorov");
        $("#userEmail").setValue("mimino@mail.ru");
        $("label[for='gender-radio-1']").click();
        $("#userNumber").setValue("8964991077");

        // Выбор даты рождения
        $(".react-datepicker__input-container #dateOfBirthInput").click();
        $("[aria-label='Choose Friday, August 22nd, 2025']").click();

        // Выбор предмета
        $("#subjectsInput").setValue("Biology");
        $("#subjectsInput").pressEnter();

        // Выбор хобби
        $("[for='hobbies-checkbox-1']").click();

        // Загрузка картинки (исправленная строка)
        $("#uploadPicture").uploadFromClasspath("котя.webp");

        // Заполнение адреса
        $("#currentAddress").setValue("Sun Andreas 15");

        // Выбор штата и города
        $("#react-select-3-input").setValue("Uttar Pradesh");
        $("#react-select-3-input").pressEnter();
        $("#react-select-4-input").setValue("Agra");
        $("#react-select-4-input").pressEnter();

        // Отправка формы
        executeJavaScript("arguments[0].click();", $("#submit"));

        // Проверки
        $(".modal-content").shouldBe(Condition.visible);
        $("#example-modal-sizes-title-lg").shouldHave(Condition.text("Thanks for submitting the form"));

        $(".table").shouldHave(Condition.text("Igor Fedorov"));
        $(".table").shouldHave(Condition.text("mimino@mail.ru"));
        $(".table").shouldHave(Condition.text("Male"));
        $(".table").shouldHave(Condition.text("8964991077"));
        $(".table").shouldHave(Condition.text("22 August,2025"));
        $(".table").shouldHave(Condition.text("Biology"));
        $(".table").shouldHave(Condition.text("Sports"));
        $(".table").shouldHave(Condition.text("котя.webp"));
        $(".table").shouldHave(Condition.text("Sun Andreas 15"));
        $(".table").shouldHave(Condition.text("Uttar Pradesh Agra"));

        // Закрытие модального окна
        $("#closeLargeModal").click();
    }
}
