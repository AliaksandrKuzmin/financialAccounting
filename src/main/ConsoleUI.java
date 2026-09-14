package main;

import controller.Controller;

import java.util.Scanner;

public class ConsoleUI {

    private final Controller controller;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        System.out.println("==================================================");
        System.out.println("        FINANCIAL ACCOUNTING APPLICATION");
        System.out.println("==================================================");

        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1" -> addRecord();
                case "2" -> updateRecord();
                case "3" -> deleteRecord();
                case "4" -> showAll();
                case "5" -> showBalance();
                case "6" -> findByCategory();
                case "7" -> findByDate();
                case "8" -> findByPeriod();
                case "0" -> running = false;
                default -> System.out.println("Unknown command.");
            }
        }

        System.out.println("Application closed.");
    }

    private void printMenu() {
        System.out.println("\nChoose an action:");
        System.out.println("1 — Add record");
        System.out.println("2 — Update record");
        System.out.println("3 — Delete record");
        System.out.println("4 — Show all records");
        System.out.println("5 — Show balance");
        System.out.println("6 — Find by category");
        System.out.println("7 — Find by date");
        System.out.println("8 — Find by period");
        System.out.println("0 — Exit");
        System.out.print("Your choice: ");
    }

    private void addRecord() {
        System.out.print("Type (INCOME/EXPENSE): ");
        String type = scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Amount: ");
        String amount = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        String request = "ADD_RECORD\n" +
                "type=" + type + "\n" +
                "category=" + category + "\n" +
                "amount=" + amount + "\n" +
                "description=" + description;

        send(request);
    }

    private void updateRecord() {
        System.out.print("Record ID: ");
        String id = scanner.nextLine();

        System.out.print("New category: ");
        String category = scanner.nextLine();

        System.out.print("New amount: ");
        String amount = scanner.nextLine();

        System.out.print("New description: ");
        String description = scanner.nextLine();

        String request = "UPDATE_RECORD\n" +
                "id=" + id + "\n" +
                "category=" + category + "\n" +
                "amount=" + amount + "\n" +
                "description=" + description;

        send(request);
    }

    private void deleteRecord() {
        System.out.print("Record ID: ");
        String id = scanner.nextLine();

        String request = "DELETE_RECORD\nid=" + id;
        send(request);
    }

    private void showAll() {
        send("SHOW_ALL");
    }

    private void showBalance() {
        send("SHOW_BALANCE");
    }

    private void findByCategory() {
        System.out.print("Category: ");
        String category = scanner.nextLine();

        String request = "FIND_BY_CATEGORY\ncategory=" + category;
        send(request);
    }

    private void findByDate() {
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        String request = "FIND_BY_DATE\ndate=" + date;
        send(request);
    }

    private void findByPeriod() {
        System.out.print("From (YYYY-MM-DD): ");
        String from = scanner.nextLine();

        System.out.print("To (YYYY-MM-DD): ");
        String to = scanner.nextLine();

        String request = "FIND_BY_PERIOD\nfrom=" + from + "\n" + "to=" + to;
        send(request);
    }

    private void send(String request) {
        System.out.println("\n>>> REQUEST:\n" + request);
        String response = controller.doAction(request);
        System.out.println("<<< RESPONSE:\n" + response);
        System.out.println("--------------------------------------------------");
    }
}

