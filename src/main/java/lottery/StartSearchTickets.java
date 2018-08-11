package lottery;

import com.codeborne.selenide.Selenide;
import logic.CompareTicket;
import logic.ConvertingTicket;
import model.Ticket;
import org.apache.log4j.Logger;
import ru.bpirate.vsrftools.Tools;
import selenideAction.Login;
import selenideAction.WorkerInPage;

import java.io.IOException;
import java.util.Random;

import static logic.CompareTicket.*;
import static logic.ConvertingTicket.PATH;
import static logic.ConvertingTicket.dateString;

public class StartSearchTickets {
    public static void main(String[] args) {
//        workSpaceContains();
        workSpaceUnique();
//        workSpaceRandom();
    }

    public static void workSpaceUnique() {
        Login.setUp();
//        Login.login("ilja_kapr@mail.ru", "Kopranych25");
        CompareTicket.setCountTicket(10);
        String pathFolder = ConvertingTicket.createFolder("unique");
        Logger log = Logger.getLogger(StartSearchTickets.class);
        System.setProperty("logPath", PATH + dateString + "\\");
        Tools.customLogger(System.getProperty("logPath"));
        log.info("Начало работы метода набора билетов");
        log.info("Тип набора билетов: уникальные");
        log.info("Начальное количество одинаковых чисел во всех билетах: " + numberOfMatches);
        while (listUniqueTicket.size() < 10) {
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
                if (listUniqueTicket.size() > 9) {
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
        log.info("Конец подбора билетов");
        log.info("Максимальное количество одинаковых чисел во всех билетах: " + numberOfMatches);
        log.info("Количество повторных билетов " + listRepeatTicket.size());
        log.info("Всего просмотренно билетов " + listAllTicket.size());
    }

    public static void workSpaceContains() {
        Login.setUp();
//        Login.login("ilja_kapr@mail.ru", "Kopranych25");
        for (int i = 0; i < 90; i++) {
            listRangeNumber.add(i + 1);
        }
        String pathFolder = ConvertingTicket.createFolder("contains");
        Logger log = Logger.getLogger(StartSearchTickets.class);
        System.setProperty("logPath", PATH + dateString + "\\");
        Tools.customLogger(System.getProperty("logPath"));
        log.info("Начало работы метода набора билетов");
        log.info("Тип набора билетов: содержащие");
        log.info("Количество уникальных чисел содержащихся в билете в начале поиска: " + countUniqueNumbersPermission);
        while (listRangeNumber.size() > 0) {
            WorkerInPage.scanTicketOnPage();
            CompareTicket.movingFromFieldToColumns(listTempTicket);

            for (Ticket ticket : listTempTicket) {
                boolean isAddedTicket = CompareTicket.addTicketContainUniqueNumbers(ticket);
                if (isAddedTicket) {
                    WorkerInPage.selectTicket(ticket);
                    try {
                        ConvertingTicket.saveTicket(ticket, pathFolder);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (listRangeNumber.size() == 0) {
                    break;
                }
            }
            searchRepeatedTicket();
            listTempTicket.clear();
            WorkerInPage.updateListTicketOnPage();
            Selenide.sleep(1000);//задержка иначе добавляет во временный набор первый билет с прошлой страницы
            Tools.customLogger("* Количество билетов в наборе = " + listTicketContainUniqueNumbers.size());
            Tools.customLogger("* Количество чисел не содержащихся в билетах " + listRangeNumber.size());
        }
        for (Ticket ticket : listTicketContainUniqueNumbers) {
            ticket.displayTicket();
        }
        log.info("Конец подбора билетов");
        log.info("Количество билетов в наборе = " + listTicketContainUniqueNumbers.size());
        log.info("Количество повторных билетов " + listRepeatTicket.size());
        log.info("Всего просмотренно билетов " + listAllTicket.size());
    }

    public static void workSpaceRandom() {
        Login.setUp();
//        Login.login("ilja_kapr@mail.ru", "Kopranych25");
        for (int i = 0; i < 90; i++) {
            listRangeNumber.add(i + 1);
        }
        String pathFolder = ConvertingTicket.createFolder("random");
        Logger log = Logger.getLogger(StartSearchTickets.class);
        System.setProperty("logPath", PATH + dateString + "\\");
        Tools.customLogger(System.getProperty("logPath"));
        log.info("Начало работы метода набора билетов");
        log.info("Тип набора билетов: random");

        while (listRandomTicket.size() < 20) {
            WorkerInPage.scanTicketOnPage();
            CompareTicket.movingFromFieldToColumns(listTempTicket);
            Random rnd = new Random();
            Ticket ticket = listTempTicket.get(rnd.nextInt(9));
            listRandomTicket.add(ticket);

            WorkerInPage.selectTicket(ticket);
            try {
                ConvertingTicket.saveTicket(ticket, pathFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            searchRepeatedTicket();
            listTempTicket.clear();
            WorkerInPage.updateListTicketOnPage();
            Selenide.sleep(1000);//задержка иначе добавляет во временный набор первый билет с прошлой страницы
        }

        log.info("Конец подбора билетов");
        log.info("Количество билетов в наборе = " + listRandomTicket.size());
        log.info("Количество повторных билетов " + listRepeatTicket.size());
        log.info("Всего просмотренно билетов " + listAllTicket.size());
    }
}
