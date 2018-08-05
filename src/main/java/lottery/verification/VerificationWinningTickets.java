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


    public static void verificationWinningTicket(List<Ticket> ticketList, int number) {
        countNumber.add(number);
        for (Ticket ticket : ticketList) {
            crossedTicket(ticket, number);//вычеркиваем совпадающие цифры
        }
        if (countNumber.size() >= 5 || countNumber.size() <= 14) {//проверяем есть ли выигрышные билеты в первом туре
            for (Ticket ticket : ticketList) {
                verificationFirstTour(ticket);
            }
        }else if (countNumber.size() >= 15 || countNumber.size()<=29) {
            for (Ticket ticket : ticketList) {
                verificationSecondTour(ticket);
            }
        }else if(countNumber.size()>=30){
            Iterator<Ticket> itr = ticketList.iterator();
            while(itr.hasNext()){
                Ticket ticket = itr.next();
                boolean isWinner = verificationNextTour(ticket);
                if(isWinner){
                    itr.remove();
                }
            }
        }



    }

    public static void verificationFirstTour(Ticket ticket) {
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
                return;
            }
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
                return;
            }
        }


    }

    public static void verificationSecondTour(Ticket ticket) {
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
            return;
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
            return;
        } else if (countCrossedNumber == 15 && countNumber.size() == 15) {
            Tools.customLogger("!!!!!ДЖЕКПОТ!!!!!!! " +
                    "Билет номер " + ticket.getNumber() + "ВЫИГРАЛ ДЖЕКПОТ!!!!!!!! СОВПАДЕНИЕМ 15 ЧИСЕЛ");
            return;
        }
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
