package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    static final String FILE_NAME = "tasks.csv";
    static String[][] tasks;
    public static void main(String[] args) {
        tasks = loadData(FILE_NAME);
        selectOption();
    }
    public static String[][] loadData(String fileName) {
        Path inputPath = Paths.get(fileName);
        String[][] tab = null;
        if (!Files.exists(inputPath)) {
            System.out.println("File not exist.");
            System.exit(0);
        }
        try {
            List<String> strings = Files.readAllLines(inputPath);
            tab = new String[strings.size()][];
            for (int i = 0; i < strings.size(); i++) {
                tab[i] = strings.get(i).split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;
    }
    public static void selectOption() {
        Scanner scan = new Scanner(System.in);
        String[] options = {"add", "remove", "list", "exit"};
        String inputOption = "";
        while (!inputOption.equals("exit")) {
            System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
            for (String option : options) {
                System.out.println(option);
            }
            inputOption = scan.next();
            switch (inputOption) {
                case "add" -> addTask();
                case "remove" -> removeTask();
                case "list" -> showTaskList();
                case "exit" -> exitProgram();
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
    public static void removeTask() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select number to remove:");
        int taskNumber = -1;
        while (taskNumber < 0) {
            while (!scan.hasNextInt()) {
                System.out.println("Incorrect argument passed. Please give number greater or equal 0:");
                scan.next();
            }
            taskNumber = scan.nextInt();
            if (taskNumber < 0) {
                System.out.println("Incorrect argument passed. Please give number greater or equal 0:");
            }
        }
        try {
            tasks = ArrayUtils.remove(tasks, taskNumber);
            System.out.println("Value was successfully deleted.");
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
    public static void exitProgram() {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            for (String[] line : tasks) {
                writer.append(line[0] + ",");
                writer.append(line[1] + ",");
                writer.append(line[2] + "\n");
            }
            System.out.println(ConsoleColors.RED + "Bye,  bye.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}