package selenideAction;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.bpirate.vsrftools.Tools;

import java.util.logging.Level;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Login {
    public static WebDriver driver;
    public static void setUp(){
        Tools.customLogger("> > Начал работу метод конфигурирации драйвера для работы с браузером");
        Configuration.holdBrowserOpen = true;
        Configuration.reportsFolder = new String("src\\main\\java\\data\\screenshots");
        Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
        ChromeDriverManager.getInstance().setup();
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(caps);
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();
        open("https://www.stoloto.ru/ruslotto/game");
    }

    public static void login(String login, String password){
        /*Tools.customLogger("> > Логинимся на сайт");
        $$(byClassName("pseudo")).findBy(text("Войти")).click();
        $$(byClassName("pretty_form_item_content")).findBy(visible).$(byName("login")).sendKeys(login);
        $$(byClassName("password")).findBy(visible).$(byName("password")).sendKeys(password);
        $$(byClassName("submit_button_container")).findBy(text("Войти")).click();*/
        $(byClassName("global_logo")).click();
        $(byClassName("abbr_ruslotto")).click();
        /*ElementsCollection colElement = $$(byClassName("widget__inner"));
        colElement.findBy(visible).$$(byClassName("btn-small")).findBy(text("Нет, спасибо")).click();*/
    }
}
