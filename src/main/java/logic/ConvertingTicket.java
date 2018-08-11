package logic;

import com.codeborne.selenide.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.StatisticTickets;
import model.Ticket;
import ru.bpirate.vsrftools.Tools;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static logic.CompareTicket.listUniqueTicket;
import static lottery.verification.VerificationWinningTickets.listTicketNextTout;

public class ConvertingTicket {
    public static final String PATH = "src\\main\\java\\data\\ticket\\";
    public static String dateString = LocalDateTime.now().toString();

    public static void saveTicket(Ticket ticket, String path) throws IOException {
        Writer write = null;
        try {
            write = new FileWriter(path + "\\" + ticket.getNumber() + ".json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(ticket, write);
        } finally {
            write.flush();
            write.close();
        }

    }

    public static String createFolder(String nameFolder) {

        dateString = dateString.replaceAll("[:\\.]", "-");
        File folder = new File(PATH + dateString + nameFolder);
        folder.mkdir();
        Configuration.reportsFolder = new String(folder.toString() + "\\screenshots");
        return folder.toString();
    }

    public static void uploadTicket(String path) {
        File dir = new File(path); //path указывает на директорию
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        for (File file : lst) {
            if(file.isDirectory()){
                continue;
            }
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                listUniqueTicket.add(gson.fromJson(reader, Ticket.class));

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } finally {

            }
        }
    }

    public static void saveInformation(StatisticTickets statisticTickets) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(PATH + "2018-08-11T16-09-47-218contains" + "\\"+ statisticTickets.getNumberTirage());
            writer.write(statisticTickets.displayStatistic(listTicketNextTout));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Tools.customLogger("Ошибка потока сохранения файла");
            e.printStackTrace();
        }
    }

    private static String searchOldFolderName(String path){
        return null;
    }
}
