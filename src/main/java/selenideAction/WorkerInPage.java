package selenideAction;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.Ticket;
import model.TicketRow;
import ru.bpirate.vsrftools.Tools;

import java.util.*;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static logic.CompareTicket.listTicket;

public class WorkerInPage {


    public static void scanTicketOnPage() {
        Tools.customLogger("> > Начал работу мтод сканирования билетов на странице");
        SelenideElement element = $(byClassName("stage"));
        Tools.customLogger("* Ищу все билеты на странице");
        ElementsCollection collectionTicket = element.$$(byClassName("bingo_ticket"));
        for (SelenideElement ticketElement : collectionTicket) {
            ElementsCollection collectionRowsTicket = ticketElement.$$(byClassName("numbers"));
            String ticketNumber = ticketElement.$(byClassName("ticket_id")).getText();
            ticketNumber = ticketNumber.substring(4);
            Ticket ticket = new Ticket(Integer.valueOf(ticketNumber));
            for (SelenideElement row : collectionRowsTicket) {
                String rowTicket = row.getText();
                String[] arrayNumberInString = rowTicket.split(" ");
                Set<Integer> setNumber = new HashSet<Integer>();
                for (int i = 0; i < arrayNumberInString.length; i++) {
                    setNumber.add(Integer.valueOf(arrayNumberInString[i]));
                }
                TicketRow ticketRow = new TicketRow(setNumber);
                if(ticket.getTopField().getSetTicketRow().size()<3){
                    ticket.getTopField().getSetTicketRow().add(ticketRow);
                }else{
                    ticket.getBotField().getSetTicketRow().add(ticketRow);
                }
            }
            listTicket.add(ticket);
        }
    }

    public static void selectTicket(Ticket ticket) {
    }

    public static void addTicketToBacket() {
    }

    public static void updateTicket() {
    }
}
