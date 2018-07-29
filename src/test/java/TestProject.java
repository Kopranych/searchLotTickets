import com.codeborne.selenide.Configuration;
import logic.CompareTicket;
import model.CoincidenceStatistic;
import model.Ticket;
import org.junit.Test;
import org.openqa.selenium.By;
import selenideAction.Login;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestProject {
    @Test
    public void test() {
        Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromedriver_win32\\chromedriver.exe");
        open("http://google.com");
        $(By.name("q")).val("wiki").pressEnter();
    }

    @Test
    public void testFillTicket() {
        Ticket ticket = new Ticket(1556);
        CompareTicket.fillTicket(ticket);
        System.out.println(ticket.toString());
    }

    @Test
    public void testCompairePairTicket() {
        Ticket one = new Ticket(993);
        Ticket two = new Ticket(133);
        CompareTicket.fillTicket(one);
        CompareTicket.fillTicket(two);
        System.out.println("Превый билет " + one.toString());
        System.out.println("Второй билет " + two.toString());
        CoincidenceStatistic cs = CompareTicket.comparePairTicket(one, two);
        System.out.println(cs.toString());
    }

    @Test
    public void testaddUniqueTickets() {
        CompareTicket.setCountTicket(10);
        int i = 1;
        while(CompareTicket.getListTicket().size() < 10) {
            Ticket ticket = new Ticket(i);
            CompareTicket.fillTicket(ticket);
            boolean isAdded = CompareTicket.addUniqueTickets(ticket,i);
            if(isAdded){
                System.out.println("Билет номер " + i + " добавлен");
            }
            i++;
        }
        System.out.println(CompareTicket.getListTicket().size());
    }

    @Test
    public void testSetUp(){
        Login.setUp();
    }
}

