package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
                case "add" -> addTask();
                case "remove" -> System.out.println("Remove task"); //remove();
                case "list" -> showTaskList();
                case "exit" -> System.out.println("Exit program"); //exitProgram();
                default -> System.out.println("Please select a correct option.");
            }
        }
    }
    public static void addTask() {
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = new String[3];
        Scanner scan = new Scanner(System.in);
        System.out.println("Please add task description:");
        tasks[tasks.length - 1][0] = scan.nextLine();
        System.out.println("Please add task due date:");
        tasks[tasks.length - 1][1] = scan.nextLine();
        System.out.println("Is your task is important (true/false):");
        tasks[tasks.length - 1][2] = scan.nextLine();
    }
    public static void showTaskList() {
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(i + " : " + tasks[i][0] + " " + tasks[i][1] + " " + tasks[i][2]);
        }
    }
}