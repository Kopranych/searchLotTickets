package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Ticket;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;

public class ConvertingTicket {

    public static void saveTicket(Ticket ticket, String path) throws IOException {
        Writer write = null;
        try {
            write = new FileWriter(path + "\\" + ticket.getNumber() + ".json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(ticket, write);
        }finally {
            write.flush();
            write.close();
        }

    }

    public static String createFolder(){
        String dateString = LocalDateTime.now().toString();
        dateString = dateString.replaceAll("[:\\.]", "-");
        File folder = new File("src\\main\\java\\data\\ticket\\" + dateString);
        folder.mkdir();
        return folder.toString();
    }

    public static void uploadTicket(String path){}
}
