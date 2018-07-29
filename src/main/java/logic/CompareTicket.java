package logic;

import model.CoincidenceStatistic;
import model.Column;
import model.Ticket;

import java.util.*;

public class CompareTicket {
    private static List<Ticket> listTicket = new LinkedList<Ticket>();
    private static int countTicket = 0;

    public static boolean addUniqueTickets(Ticket ticket, int numberOfMatches){
        if(countTicket == 0){
            System.out.println("* Определите необходимое количество билетов в наборе!!!");
            return false;
        }
        for (Ticket tick :listTicket) {
            CoincidenceStatistic statistic = comparePairTicket(ticket, tick);
            if(statistic.getNumberCoincidence() > numberOfMatches){
                return  false;
            }
        }
        listTicket.add(ticket);
        return true;
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
                for (Integer integ2 : setNumber2){
                    if(integ1.equals(integ2)){
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
