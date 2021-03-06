import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import logic.CompareTicket;
import logic.ConvertingTicket;
import model.Ticket;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.bpirate.vsrftools.Tools;
import selenideAction.Login;
import selenideAction.WorkerInPage;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static logic.CompareTicket.*;
import static logic.ConvertingTicket.uploadTicket;

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
//        CoincidenceStatistic cs = CompareTicket.comparePairTicket(one, two);
//        System.out.println(cs.toString());
    }

    @Test
    public void testaddUniqueTickets() {
        CompareTicket.setCountTicket(10);
        int i = 1;
        while (CompareTicket.getListUniqueTicket().size() < 10) {
            Ticket ticket = new Ticket(i);
            CompareTicket.fillTicket(ticket);
            boolean isAdded = CompareTicket.addUniqueTickets(ticket);
            if (isAdded) {
                System.out.println("Билет номер " + i + " добавлен");
            }
            i++;
        }
        System.out.println(CompareTicket.getListUniqueTicket().size());
    }

    @Test
    public void testSetUp() {
        Login.setUp();
    }

    @Test
    public void testMoveFromFieldToColumn() {
        for (int i = 0; i < 10; i++) {
            Ticket ticket = new Ticket(i + 1);
            listUniqueTicket.add(ticket);
        }
        Set<Integer> setNum1 = new HashSet<Integer>();
        Set<Integer> setNum2 = new HashSet<Integer>();
        Set<Integer> setNum3 = new HashSet<Integer>();
        Set<Integer> setNum4 = new HashSet<Integer>();
        Set<Integer> setNum5 = new HashSet<Integer>();
        Set<Integer> setNum6 = new HashSet<Integer>();

        for (int i = 0; i < 5; i++) {
//            setNum1.add();

        }
        Random rnd = new Random();
        while (setNum1.size() < 5) {
//            setNum1.add((rnd.nextInt(10) + 1) + ((i - 1) * 10));
        }
    }

    @Test
    public void testworkSpase(){
        Login.setUp();
//        Login.login("ilja_kapr@mail.ru", "Kopranych25");
        CompareTicket.setCountTicket(10);
        String pathFolder = ConvertingTicket.createFolder("unique");
        final Logger log = Logger.getLogger(TestProject.class);
        System.setProperty("logPath.", pathFolder);
        log.info("Начало работы метода набора билетов");
        while(listUniqueTicket.size()<10) {
            WorkerInPage.scanTicketOnPage();
            CompareTicket.movingFromFieldToColumns(listTempTicket);

            for (Ticket ticket : listTempTicket) {
                boolean isAddedTicket = CompareTicket.addUniqueTickets(ticket);
                if (isAddedTicket) {
                    WorkerInPage.selectTicket(ticket);
                    try {
                        ConvertingTicket.saveTicket(ticket, pathFolder);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(listUniqueTicket.size()>9){
                    break;
                }
            }
            searchRepeatedTicket();
            listTempTicket.clear();
            WorkerInPage.updateListTicketOnPage();
            Selenide.sleep(1000);//задержка иначе добавляет во временный набор первый билет с прошлой страницы
            Tools.customLogger("* Значение numberOfMatches = " + numberOfMatches);
        }
        for (Ticket ticket : listUniqueTicket) {
            ticket.displayTicket();
        }
        log.info("Конец подборв билетов");
        log.info("Количество повторных билетов " + listRepeatTicket.size());
        log.info("Всего просмотренно билетов " + listAllTicket.size());
    }

    @Test
    public void testUploadJson(){
        uploadTicket(new File("src\\main\\java\\data\\ticket\\" +  "2018-08-04T20-25-26-820").getPath());
    }
}

