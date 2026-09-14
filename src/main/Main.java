package main;

import controller.CommandProvider;
import controller.Controller;

public class Main {

     static void main(String[] args) {

        Controller controller = initApp();

        if (controller == null) {
            System.err.println("Critical initialization error.");
            return;
        }

        ConsoleUI ui = new ConsoleUI(controller);
        ui.start();
    }

    private static Controller initApp() {
        try {
            CommandProvider commandProvider = new CommandProvider();
            return new Controller(commandProvider);
        } catch (Exception e) {
            System.err.println("Critical error while starting the application: " + e.getMessage());
            return null;
        }
    }
}



