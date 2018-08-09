package logic;

import model.*;
import ru.bpirate.vsrftools.Tools;

import java.util.*;

public class CompareTicket {
    public static List<Ticket> listUniqueTicket = new LinkedList<Ticket>();
    public static List<Ticket> listTempTicket = new LinkedList<Ticket>();
    public static Set<Ticket> listAllTicket = new HashSet<>();
    public static Set<Ticket> listRepeatTicket = new HashSet();
    public static int numberOfMatches = 5;
    private static int countTicket = 0;

    public static boolean addUniqueTickets(Ticket ticket) {
        Tools.customLogger("> > Начал работу метод добавления уникльных билетов");
        if (countTicket == 0) {
            Tools.customLogger("* Определите необходимое количество билетов в наборе!!!");
            return false;
        }
        for (Ticket tick : listUniqueTicket) {
            CoincidenceStatistic statistic = comparePairTicket(ticket, tick);
            if (statistic.getNumberCoincidence() > numberOfMatches) {
                return false;
            }
        }
        Tools.customLogger("* Добавляю уникальный билет номер " + ticket.getNumber());
        listUniqueTicket.add(ticket);
        Tools.customLogger("* В наборе " + listUniqueTicket.size() + " билетов");
        if (numberOfMatches < 10) {
            numberOfMatches++;
        }
        return true;
    }

    public static void movingFromFieldToColumns(List<Ticket> listTicket) {
        Tools.customLogger("> > Начал работу метод переноса строк в колонки");
        for (Ticket ticket : listTicket) {
            Set<TicketRow> setRow = ticket.getTopField().getSetTicketRow();
            fromRowsToColumn(setRow, ticket);
            setRow = ticket.getBotField().getSetTicketRow();
            fromRowsToColumn(setRow, ticket);
        }
    }

    private static void fromRowsToColumn(Set<TicketRow> setRow, Ticket ticket) {
        for (TicketRow ticketRow : setRow) {
            for (Integer i : ticketRow.getSetNumber()) {
                if (i < 10) {
                    for (Column col : ticket.getSetColumn()) {
                        if (col.getNumberColumn() == 1)
                            col.getSetNumber().add(i);
                    }
                } else if (i >= 10 && i < 20) {
                    for (Column col : ticket.getSetColumn()) {
                        if (col.getNumberColumn() == 2)
                            col.getSetNumber().add(i);
                    }
                } else if (i >= 20 && i < 30) {
                    for (Column col : ticket.getSetColumn()) {
                        if (col.getNumberColumn() == 3)
                            col.getSetNumber().add(i);
                    }
                } else if (i >= 30 && i < 40) {
                    for (Column col : ticket.getSetColumn()) {
                        if (col.getNumberColumn() == 4)
                            col.getSetNumber().add(i);
                    }
                } else if (i >= 40 && i < 50) {
                    for (Column col : ticket.getSetColumn()) {
                        if (col.getNumberColumn() == 5)
                            col.getSetNumber().add(i);
                    }
                } else if (i >= 50 && i < 60) {
                    for (Column col : ticket.getSetColumn()) {
                        if (col.getNumberColumn() == 6)
                            col.getSetNumber().add(i);
                    }
                } else if (i >= 60 && i < 70) {
                    for (Column col : ticket.getSetColumn()) {
                        if (col.getNumberColumn() == 7)
                            col.getSetNumber().add(i);
                    }
                } else if (i >= 70 && i < 80) {
                    for (Column col : ticket.getSetColumn()) {
                        if (col.getNumberColumn() == 8)
                            col.getSetNumber().add(i);
                    }
                } else if (i >= 80 && i < 91) {
                    for (Column col : ticket.getSetColumn()) {
                        if (col.getNumberColumn() == 9)
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

    private static CoincidenceStatistic comparePairTicket(Ticket oneTicket, Ticket twoTicket) {
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

    public static void searchRepeatedTicket() {

        Iterator<Ticket> itr = listTempTicket.iterator();
        while (itr.hasNext()) {
            Ticket ticket = itr.next();
            for (Ticket AllTicked : listAllTicket) {
                if (AllTicked.getNumber() == ticket.getNumber()) {
                    Tools.customLogger("!!!!!!!!!!!!!!Билеты повторяются номер!!!!!!!!!!!!!!!! " + ticket.getNumber());
                    listRepeatTicket.add(AllTicked);
                    Tools.customLogger("!!!!!!!!!!!!!!Количество повторных билетов " + listRepeatTicket.size() + "!!!!!!!!!!!!!!!!!!!!!!!!");
                    itr.remove();

                }
            }
        }
        listAllTicket.addAll(listTempTicket);
        Tools.customLogger("*********************** Всего билетов " + listAllTicket.size() + "*****************************");
    }

    public static List<Ticket> getListUniqueTicket() {
        return listUniqueTicket;
    }

    public static void setCountTicket(int countTicket) {
        CompareTicket.countTicket = countTicket;
    }

    public static void movingFromRowToCell(Ticket ticket){
        for (TicketRow row : ticket.getTopField().getSetTicketRow()) {
            row.setSetCell(new HashSet<Cell>());
            for (Integer i : row.getSetNumber()) {

                row.getSetCell().add(new Cell(i));
            }
        }
        for (TicketRow row : ticket.getBotField().getSetTicketRow()) {
            row.setSetCell(new HashSet<Cell>());
            for (Integer i : row.getSetNumber()) {
                row.getSetCell().add(new Cell(i));
            }
        }
    }
}
