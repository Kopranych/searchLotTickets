package lottery.verification;

import model.Cell;
import model.StatisticTickets;
import model.Ticket;
import model.TicketRow;
import ru.bpirate.vsrftools.Tools;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class VerificationWinningTickets {
    public static List<Integer> countNumber = new LinkedList<>();
    public static List<Ticket> listTicketFirstTour;
    public static List<Ticket> listTicketSecondTour;
    public static List<Ticket> listTicketNextTout;
    public static StatisticTickets statisticTickets = new StatisticTickets();

    public static void verificationWinningTicket(int number, int numberTour) {
        countNumber.add(number);
        if (numberTour == 1) {//проверяем есть ли выигрышные билеты в первом туре
            Iterator<Ticket> iteratorFirstTour = listTicketFirstTour.iterator();
            while(iteratorFirstTour.hasNext()){
                Ticket ticket = iteratorFirstTour.next();
                if(verificationFirstTour(ticket)){
                    statisticTickets.getListTicketWin().add(ticket);
                    iteratorFirstTour.remove();
                }
            }
        }else if (numberTour == 2) {
            Iterator<Ticket> iteratorSecondTout = listTicketSecondTour.iterator();
            while(iteratorSecondTout.hasNext()){
                Ticket ticket = iteratorSecondTout.next();
                if(verificationSecondTour(ticket)){
                    statisticTickets.getListTicketWin().add(ticket);
                    iteratorSecondTout.remove();
                }
            }
        }else if(numberTour == 3){
            Iterator<Ticket> itr = listTicketNextTout.iterator();
            while(itr.hasNext()){
                Ticket ticket = itr.next();
                boolean isWinner = verificationNextTour(ticket);
                if(isWinner){
                    statisticTickets.getListTicketWin().add(ticket);
                    itr.remove();
                }
            }
        }



    }

    public static boolean verificationFirstTour(Ticket ticket) {
        //первый тур
        boolean isWinner = true;
        for (TicketRow ticketRow : ticket.getTopField().getSetTicketRow()) {
            for (Cell cell : ticketRow.getSetCell()) {
                if (!cell.isCrossed()) {
                    isWinner = false;
                    break;
                }
            }
            if (isWinner) {
                Tools.customLogger("Билет номер " + ticket.getNumber() + " ВЫИГРАЛ В ПЕРВОМ ТУРЕ СОВПОДННИЕМ СТРОКИ " + ticketRow.getSetCell());
                ticket.setWinFirstTour(isWinner);
                return isWinner;
            }
            isWinner = true;
        }
        isWinner = true;
        for (TicketRow ticketRow : ticket.getBotField().getSetTicketRow()) {
            for (Cell cell : ticketRow.getSetCell()) {
                if (!cell.isCrossed()) {
                    isWinner = false;
                    break;
                }
            }
            if (isWinner) {
                Tools.customLogger("Билет номер " + ticket.getNumber() + " ВЫИГРАЛ В ПЕРВОМ ТУРЕ СОВПОДННИЕМ СТРОКИ " + ticketRow.getSetCell());
                ticket.setWinFirstTour(isWinner);
                return isWinner;
            }
            isWinner = true;
        }
        isWinner = false;
        return isWinner;
    }

    public static boolean verificationSecondTour(Ticket ticket) {
        //второй тур
        boolean isWinner = true;
        int countCrossedNumber = 0;
        int countCrossed = ticket.getCountIsCrossedNumber();
        for (TicketRow ticketRow : ticket.getTopField().getSetTicketRow()) {
            for (Cell cell : ticketRow.getSetCell()) {
                if (!cell.isCrossed()) {
                    isWinner = false;
                }else {
                    countCrossedNumber++;
                }
            }
        }
        if (isWinner) {
            Tools.customLogger(
                    "Билет номер " + ticket.getNumber() + " ВЫИГРАЛ ВО ВТОРОМ ТУРЕ ПО ВЕРХНЕМУ ПОЛЮ");
            ticket.setWinSecondfTour(isWinner);
            return isWinner;
        }
        isWinner = true;
        for (TicketRow ticketRow : ticket.getBotField().getSetTicketRow()) {
            for (Cell cell : ticketRow.getSetCell()) {
                if (!cell.isCrossed()) {
                    isWinner = false;
                }else {
                    countCrossedNumber++;
                }
            }
        }
        if (isWinner) {
            Tools.customLogger("Билет номер " + ticket.getNumber() + " ВЫИГРАЛ ВО ВТОРОМ ТУРЕ ПО НИЖНЕМУ ПОЛЮ");
            ticket.setWinSecondfTour(isWinner);
            return isWinner;
        } else if (countCrossedNumber == 15 && countNumber.size() == 15) {
            Tools.customLogger("!!!!!ДЖЕКПОТ!!!!!!! " +
                    "Билет номер " + ticket.getNumber() + " ВЫИГРАЛ ДЖЕКПОТ!!!!!!!! СОВПАДЕНИЕМ " + countCrossedNumber + " ЧИСЕЛ");
            isWinner = true;
            ticket.setWinJackpot(isWinner);
            return isWinner;
        }
        return isWinner;
    }

    public static boolean verificationNextTour(Ticket ticket) {
        //третий и последующий тур
        boolean isWinner = true;
        int countCrossedNumber = 0;

        for (TicketRow ticketRow : ticket.getTopField().getSetTicketRow()) {
            for (Cell cell : ticketRow.getSetCell()) {
                if (!cell.isCrossed()) {
                    isWinner = false;
                    return isWinner;
                }
                countCrossedNumber++;
            }
        }
        for (TicketRow ticketRow : ticket.getBotField().getSetTicketRow()) {
            for (Cell cell : ticketRow.getSetCell()) {
                if (!cell.isCrossed()) {
                    isWinner = false;
                    return isWinner;
                }
                countCrossedNumber++;
            }
        }
        if (isWinner) {
            Tools.customLogger("Билет номер " + ticket.getNumber() + " ВЫИГРАЛ В ПОСЛЕДУЮЩЕМ ТУРЕ СОВПАДЕНИЕМ ВСЕХ ЧИСЕЛ В БИЛЕТЕ = " + countCrossedNumber);
            ticket.setWinNextTour(isWinner);
            return isWinner;
        }
        return isWinner;
    }

    public static void crossedTicket(Ticket ticket, int number) {
        for (TicketRow ticketRow : ticket.getTopField().getSetTicketRow()) {
            for (Cell cell : ticketRow.getSetCell()) {
                if (cell.getValue() == number) {
                    cell.setCrossed(true);
                    return;
                }
            }
        }
        for (TicketRow ticketRow : ticket.getBotField().getSetTicketRow()) {
            for (Cell cell : ticketRow.getSetCell()) {
                if (cell.getValue() == number) {
                    cell.setCrossed(true);
                    return;
                }
            }
        }
    }
}
