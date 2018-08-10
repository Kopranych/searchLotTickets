package logic;

import com.codeborne.selenide.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Ticket;
import ru.bpirate.vsrftools.Tools;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static logic.CompareTicket.listUniqueTicket;

public class ConvertingTicket {

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

    public static String createFolder() {
        String dateString = LocalDateTime.now().toString();
        dateString = dateString.replaceAll("[:\\.]", "-");
        File folder = new File("src\\main\\java\\data\\ticket\\" + dateString);
        folder.mkdir();
        Configuration.reportsFolder = new String(folder.toString() + "\\screenshots");
        return folder.toString();
    }

    public static void uploadTicket(String path) {
        File dir = new File(path); //path указывает на директорию
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        for (File file : lst) {
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

    public static void saveInformation(String path, String write) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path + name);
            writer.write(write);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Tools.customLogger("Ошибка потока сохранения файла");
            e.printStackTrace();
        }
    }

    public static void statisticsCollection() {

    }
}
