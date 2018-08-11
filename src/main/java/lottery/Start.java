package lottery;

import logic.ConvertingTicket;
import model.Ticket;
import ru.bpirate.vsrftools.Tools;

import java.io.File;
import java.util.*;

import static logic.CompareTicket.listUniqueTicket;
import static lottery.verification.VerificationWinningTickets.*;

public class Start {

    public static void main(String[] args) {
        Tools.customLogger("> > Начала работу программа проверки выигрышных билетов");
        Tools.customLogger("* Введите номер тиража");
        Scanner scan = new Scanner(System.in);
        statisticTickets.setNumberTirage(scan.nextInt());
        Set<Integer> setNumberOutBag = new HashSet();
        List<Integer> listRangeNumber = new ArrayList();
        for (int i = 0; i < 90; i++) {
            listRangeNumber.add(i, i + 1);
        }
        int countEnteredNumber = 0;

        ConvertingTicket.uploadTicket(new File("src\\main\\java\\data\\ticket\\" + "2018-08-11T16-09-47-218contains").getPath());
/*        for (Ticket ticket : listUniqueTicket) {
            CompareTicket.movingFromRowToCell(ticket);
        }*/
        int numberOutBag = 1;
        listTicketFirstTour = new LinkedList(listUniqueTicket);
        listTicketSecondTour = new LinkedList(listUniqueTicket);
        listTicketNextTout = new LinkedList(listUniqueTicket);

        while (numberOutBag != 100) {
            Tools.customLogger("* Ход " + ++countEnteredNumber + " введите число от 1 до 90");
            Scanner scanner = new Scanner(System.in);
            numberOutBag = scanner.nextInt();
            if (numberOutBag == 100) {
                Tools.customLogger("* Игра закончена");
                break;
            }
            if (setNumberOutBag.add(numberOutBag) & numberOutBag <= 90) {
                Tools.customLogger("* Введите номер тура");
                Scanner scanner1 = new Scanner(System.in);
                int numTour = scan.nextInt();

                listRangeNumber.remove(new Integer(numberOutBag));
                Iterator<Ticket> iteratorFirstTout = listTicketFirstTour.iterator();
                Iterator<Ticket> iteratorSecondTout = listTicketSecondTour.iterator();
                Iterator<Ticket> iteratorNextTout = listTicketNextTout.iterator();
                while (iteratorFirstTout.hasNext()) {
                    Ticket ticket = iteratorFirstTout.next();
                    crossedTicket(ticket, numberOutBag);//вычеркиваем совпадающие цифры
                }
                while (iteratorSecondTout.hasNext()) {
                    Ticket ticket = iteratorSecondTout.next();
                    crossedTicket(ticket, numberOutBag);//вычеркиваем совпадающие цифры
                }
                while (iteratorNextTout.hasNext()) {
                    Ticket ticket = iteratorNextTout.next();
                    crossedTicket(ticket, numberOutBag);//вычеркиваем совпадающие цифры
                }

                verificationWinningTicket(numberOutBag, numTour, listRangeNumber);
            } else {
                Tools.customLogger("Такая цифра " + numberOutBag + " уже была");
                Tools.customLogger("Попробуйте цифру из списка");
                for (Integer i : listRangeNumber) {
                    System.out.print(i + ", ");
                }
            }
        }
        Tools.customLogger("Оставшиеся цифры");
        for (Integer i : listRangeNumber) {
            System.out.print(i + ", ");
        }
        statisticTickets.setListRemainingNumbers(listRangeNumber);
        ConvertingTicket.saveInformation(statisticTickets);
    }
        /*
        * TODO выводить статистику по всем билетам в конце
        * TODO проверить на невыпавшие числа
        * статистику записывать в файл в папку с набором билетов
        * в файле записать номера билетов, сколько совпавших чисел для каждого билета
        * показать выигрышные билеты в каком туре они выиграли
        * какой номер тиража и название типа коллекции билетов*/
}

