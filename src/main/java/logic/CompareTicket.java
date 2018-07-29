package logic;

import model.CoincidenceStatistic;
import model.Column;
import model.Ticket;
import model.TicketRow;

import java.util.*;

public class CompareTicket {
    public static List<Ticket> listTicket = new LinkedList<Ticket>();
    private static int countTicket = 0;

    public static boolean addUniqueTickets(Ticket ticket, int numberOfMatches) {
        if (countTicket == 0) {
            System.out.println("* Определите необходимое количество билетов в наборе!!!");
            return false;
        }
        for (Ticket tick : listTicket) {
            CoincidenceStatistic statistic = comparePairTicket(ticket, tick);
            if (statistic.getNumberCoincidence() > numberOfMatches) {
                return false;
            }
        }
        listTicket.add(ticket);
        return true;
    }

    public static void movingFromFieldToColumns() {
        for (Ticket ticket : listTicket) {
            Set<TicketRow> setRow = ticket.getTopField().getSetTicketRow();
            fromRowsToColumn(setRow, ticket);
            setRow = ticket.getBotField().getSetTicketRow();
            fromRowsToColumn(setRow, ticket);
        }
    }

    public static void fromRowsToColumn(Set<TicketRow> setRow, Ticket ticket){
        for (TicketRow  ticketRow: setRow){
            for(Integer i: ticketRow.getSetNumber()){
                if(i<11){
                    for (Column col : ticket.getSetColumn()) {
                        if(col.getNumberColumn() == 1)
                            col.getSetNumber().add(i);
                    }
                }else if(i>=11 && i<21){
                    for (Column col : ticket.getSetColumn()) {
                        if(col.getNumberColumn() == 2)
                            col.getSetNumber().add(i);
                    }
                }else if(i>=21 && i<31){
                    for (Column col : ticket.getSetColumn()) {
                        if(col.getNumberColumn() == 3)
                            col.getSetNumber().add(i);
                    }
                }else if(i>=31 && i<41){
                    for (Column col : ticket.getSetColumn()) {
                        if(col.getNumberColumn() == 4)
                            col.getSetNumber().add(i);
                    }
                }else if(i>=41 && i<51){
                    for (Column col : ticket.getSetColumn()) {
                        if(col.getNumberColumn() == 5)
                            col.getSetNumber().add(i);
                    }
                }else if(i>=51 && i<61){
                    for (Column col : ticket.getSetColumn()) {
                        if(col.getNumberColumn() == 6)
                            col.getSetNumber().add(i);
                    }
                }else if(i>=61 && i<71){
                    for (Column col : ticket.getSetColumn()) {
                        if(col.getNumberColumn() == 7)
                            col.getSetNumber().add(i);
                    }
                }else if(i>=71 && i<81){
                    for (Column col : ticket.getSetColumn()) {
                        if(col.getNumberColumn() == 8)
                            col.getSetNumber().add(i);
                    }
                }else if(i>=81 && i<91){
                    for (Column col : ticket.getSetColumn()) {
                        if(col.getNumberColumn() == 9)
                            col.getSetNumber().add(i);
                    }
                }
            }
        }
    }

    public static void fillTicket(Ticket ticket) {
        Random rnd = new Random();
        for (int i = 1; i <= 9; i++) {
            Column col = new Column(i);
            ticket.getSetColumn().add(col);
            Set<Integer> setNumber = col.getSetNumber();
            while (setNumber.size() < 3) {
                setNumber.add((rnd.nextInt(10) + 1) + ((i - 1) * 10));
            }
        }
    }

    public static CoincidenceStatistic comparePairTicket(Ticket oneTicket, Ticket twoTicket) {
        CoincidenceStatistic cs = new CoincidenceStatistic();
        Iterator it1 = oneTicket.getSetColumn().iterator();
        Iterator it2 = twoTicket.getSetColumn().iterator();
        while (it1.hasNext()) {
            Column col1 = (Column) it1.next();
            Column col2 = (Column) it2.next();
            Set<Integer> setNumber1 = col1.getSetNumber();
            Set<Integer> setNumber2 = col2.getSetNumber();
            for (Integer integ1 : setNumber1) {
                for (Integer integ2 : setNumber2) {
                    if (integ1.equals(integ2)) {
                        cs.getSetIdenticalNumber().add(integ1.intValue());
                    }
                }

            }

        }
        return cs;
    }

    public static List<Ticket> getListTicket() {
        return listTicket;
    }

    public static void setCountTicket(int countTicket) {
        CompareTicket.countTicket = countTicket;
    }
}
