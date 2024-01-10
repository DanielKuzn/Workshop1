package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;

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
        tasks = loadData(FILE_NAME, ",");
        selectOption();
    }
    public static String[][] loadData(String fileName, String separator) {
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
                tab[i] = strings.get(i).split(separator);
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
                case "remove" -> {
                    removeTask(tasks, getTheNumber());
                    System.out.println("Value was successfully deleted.");
                }
                case "list" -> showTaskList(tasks);
                case "exit" -> {
                    exitProgram(FILE_NAME, tasks);
                    System.out.println(ConsoleColors.RED + "Bye, bye.");
                    System.exit(0);
                }
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
    public static void showTaskList(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void removeTask(String[][] tab, int index) {
        try {
            if (index < tab.length) {
                tasks = ArrayUtils.remove(tab, index);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Element not exist in tab");
        }
    }
    public static int getTheNumber() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select number to remove.");
        String n = scan.nextLine();
        while (!isNumberGreaterEqualZero(n)) {
            System.out.println("Incorrect argument passed. Please give number greater or equal 0");
            n = scan.nextLine();
        }
        return Integer.parseInt(n);
    }
    public static boolean isNumberGreaterEqualZero(String input) {
        if (NumberUtils.isParsable(input)) {
            return Integer.parseInt(input) >= 0;
        }
        return false;
    }
    public static void exitProgram(String fileName, String[][] tab) {
        Path dir = Paths.get(fileName);
        String[] lines = new String[tasks.length];
        for (int i = 0; i < tab.length; i++) {
            lines[i] = String.join(",", tab[i]);
        }
        try {
            Files.write(dir, Arrays.asList(lines));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}