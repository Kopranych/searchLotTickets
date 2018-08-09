package lottery.verification;

import model.Cell;
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


    public static void verificationWinningTicket(int number) {
        countNumber.add(number);
        if (countNumber.size() >= 5 && countNumber.size() <= 14) {//проверяем есть ли выигрышные билеты в первом туре
            Iterator<Ticket> iteratorFirstTout = listTicketFirstTour.iterator();
            while(iteratorFirstTout.hasNext()){
                Ticket ticket = iteratorFirstTout.next();
                if(verificationFirstTour(ticket)){
                    iteratorFirstTout.remove();
                }
            }
        }else if (countNumber.size() >= 15 && countNumber.size()<=29) {
            Iterator<Ticket> iteratorSecondTout = listTicketSecondTour.iterator();
            while(iteratorSecondTout.hasNext()){
                Ticket ticket = iteratorSecondTout.next();
                if(verificationSecondTour(ticket)){
                    iteratorSecondTout.remove();
                }
            }
        }else if(countNumber.size()>=30){
            Iterator<Ticket> itr = listTicketNextTout.iterator();
            while(itr.hasNext()){
                Ticket ticket = itr.next();
                boolean isWinner = verificationNextTour(ticket);
                if(isWinner){
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
                Tools.customLogger("Билет номер " + ticket.getNumber() + "ВЫИГРАЛ В ПЕРВОМ ТУРЕ СОВПОДННИЕМ СТРОКИ " + ticketRow.getSetCell());
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
                Tools.customLogger("Билет номер " + ticket.getNumber() + "ВЫИГРАЛ В ПЕРВОМ ТУРЕ СОВПОДННИЕМ СТРОКИ " + ticketRow.getSetCell());
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

        for (TicketRow ticketRow : ticket.getTopField().getSetTicketRow()) {
            for (Cell cell : ticketRow.getSetCell()) {
                if (!cell.isCrossed()) {
                    isWinner = false;
                    break;
                }
                countCrossedNumber++;
            }
            if (!isWinner) {
                break;
            }
        }
        if (isWinner) {
            Tools.customLogger(
                    "Билет номер " + ticket.getNumber() + " ВЫИГРАЛ ВО ВТОРОМ ТУРЕ ПО ВЕРХНЕМУ ПОЛЮ");
            return isWinner;
        }
        isWinner = true;
        for (TicketRow ticketRow : ticket.getBotField().getSetTicketRow()) {
            for (Cell cell : ticketRow.getSetCell()) {
                if (!cell.isCrossed()) {
                    isWinner = false;
                    break;
                }
                countCrossedNumber++;
            }
            if (!isWinner) {
                break;
            }
        }
        if (isWinner) {
            Tools.customLogger("Билет номер " + ticket.getNumber() + " ВЫИГРАЛ ВО ВТОРОМ ТУРЕ ПО НИЖНЕМУ ПОЛЮ");
            return isWinner;
        } else if (countCrossedNumber == 15 && countNumber.size() == 15) {
            Tools.customLogger("!!!!!ДЖЕКПОТ!!!!!!! " +
                    "Билет номер " + ticket.getNumber() + "ВЫИГРАЛ ДЖЕКПОТ!!!!!!!! СОВПАДЕНИЕМ " + countCrossedNumber + " ЧИСЕЛ");
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
