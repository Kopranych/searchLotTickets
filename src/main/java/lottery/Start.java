package lottery;

import logic.CompareTicket;
import logic.ConvertingTicket;
import model.Ticket;
import ru.bpirate.vsrftools.Tools;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import static logic.CompareTicket.listUniqueTicket;
import static lottery.verification.VerificationWinningTickets.*;

public class Start {
    public static void main(String[] args) {
        Tools.customLogger("> > Начала работу программа проверки выигрышных билетов");
        ConvertingTicket.uploadTicket(new File("src\\main\\java\\data\\ticket\\" +  "2018-08-04T20-25-26-820").getPath());
        for (Ticket ticket : listUniqueTicket) {
            CompareTicket.movingFromRowToCell(ticket);
        }
        int numberOutBag = 1;
        listTicketFirstTour = new LinkedList<>(listUniqueTicket);
        listTicketSecondTour = new LinkedList<>(listUniqueTicket);
        listTicketNextTout = new LinkedList<>(listUniqueTicket);

        while(numberOutBag != 100) {
            Tools.customLogger("* Введите число");
            Scanner scanner = new Scanner(System.in);
            numberOutBag = scanner.nextInt();
            if(numberOutBag == 100){
                Tools.customLogger("* Игра закончена");
                break;
            }
            Iterator<Ticket> iteratorFirstTout = listTicketFirstTour.iterator();
            Iterator<Ticket> iteratorSecondTout = listTicketSecondTour.iterator();
            Iterator<Ticket> iteratorNextTout = listTicketNextTout.iterator();
            while(iteratorFirstTout.hasNext()){
                Ticket ticket = iteratorFirstTout.next();
                crossedTicket(ticket, numberOutBag);//вычеркиваем совпадающие цифры
            }
            while(iteratorSecondTout.hasNext()){
                Ticket ticket = iteratorSecondTout.next();
                crossedTicket(ticket, numberOutBag);//вычеркиваем совпадающие цифры
            }
            while(iteratorNextTout.hasNext()){
                Ticket ticket = iteratorNextTout.next();
                crossedTicket(ticket, numberOutBag);//вычеркиваем совпадающие цифры
            }

            verificationWinningTicket(numberOutBag);
        }
    }
}
