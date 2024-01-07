package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TaskManager {
    static String[][] tasks;
    public static void main(String[] args) {
        loadData();
        selectOption();
    }
    public static void loadData() {
        Path inputPath = Paths.get("tasks.csv");
        int lineNumber = 0;
        try {
            for (String line : Files.readAllLines(inputPath)) {
                lineNumber++;
            }
            tasks = new String[lineNumber][];
            lineNumber = 0;
            for (String line : Files.readAllLines(inputPath)) {
                tasks[lineNumber] = line.split(",");
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void selectOption() {
        Scanner scan = new Scanner(System.in);
        String[] options = {"add", "remove", "list", "exit"};
        String inputOption = "";
        while (!inputOption.equals("exit")) {
            System.out.println(ConsoleColors.BLUE + "Please select an option:");
            for (String option : options) {
                System.out.println(ConsoleColors.RESET + option);
            }
            inputOption = scan.next();
            switch (inputOption) {
                case "add" -> System.out.println("Add task"); //addTask();
                case "remove" -> System.out.println("Remove task"); //remove();
                case "list" -> System.out.println("List task"); //showTaskList();
                case "exit" -> System.out.println("Exit program"); //exitProgram();
                default -> System.out.println("Please select a correct option.");
            }
        }
    }
}