package main;

import controller.Controller;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Financial Accounting ===");

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1 — Добавить запись");
            System.out.println("2 — Показать все записи");
            System.out.println("3 — Показать баланс");
            System.out.println("4 — Удалить запись");
            System.out.println("0 — Выход");
            System.out.print("Ваш выбор: ");

            String action = scanner.nextLine();

            switch (action) {

                case "1" -> {
                    System.out.println("Введите тип (INCOME/EXPENSE):");
                    String type = scanner.nextLine();

                    System.out.println("Введите категорию:");
                    String category = scanner.nextLine();

                    System.out.println("Введите сумму:");
                    String amount = scanner.nextLine();

                    System.out.println("Введите описание:");
                    String description = scanner.nextLine();

                    String request = """
                            ADD_RECORD
                            type=%s
                            category=%s
                            amount=%s
                            description=%s
                            """.formatted(type, category, amount, description);

                    System.out.println(controller.executeTask(request));
                }

                case "2" -> {
                    System.out.println(controller.executeTask("SHOW_ALL"));
                }

                case "3" -> {
                    System.out.println(controller.executeTask("SHOW_BALANCE"));
                }

                case "4" -> {
                    System.out.println("Введите ID записи для удаления:");
                    String id = scanner.nextLine();

                    String request = "DELETE_RECORD id=" + id;
                    System.out.println(controller.executeTask(request));
                }

                case "0" -> {
                    System.out.println("Выход из программы...");
                    return;
                }

                default -> System.out.println("Неизвестная команда. Попробуйте снова.");
            }
        }
    }
}


