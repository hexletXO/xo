/*
Copyright (c) 2013 Evgeniy Dolgikh

See the file LICENSE for copying permission.
*/
package ua.org.atsy.xo;
import ua.org.atsy.xo.defs.GameType;
import java.util.Scanner;

public class XO {
    private boolean running = true;
    private GameStats stats;
    private Scanner in;
    public XO() {
        in = new Scanner(System.in);
        stats = new GameStats();
    }
    private String askUser(String question) {
        printQuestion(question);
        return  in.next();
    }
    private Integer askUserInt(String question) {
        printQuestion(question);
        while(!in.hasNextInt()) {
            System.out.println("Incorrect input. Integer required.");
            printQuestion(question);
            in.next();
        }
        return in.nextInt();
    }
    public void mainLoop() {
        while(running) {
            mainMenu();
        }
        in.close();
    }
    private void startNewGame(GameType type) {
        //stub
    }
    private void printQuestion(String question) {
        System.out.print(question + ": ");
    }
    private void mainMenu() {
        String answer;
        String menu = "Menu.\n" +
                "p - player vs player game\n" +
                "c - player vs cpu game\n" +
                "n - player vs player via LAN\n" +
                "s - show game stats" +
                "e - exit\n" +
                "Your choice [p/c/n/e]";
        while(true) {
            answer = askUser(menu);
            if(answer.equals("p")) {
                startNewGame(GameType.PVP);
            } else if(answer.equals("c")) {
                startNewGame(GameType.PVC);
            } else if(answer.equals("n")) {
                startNewGame(GameType.NET);
            } else if(answer.equals("s")) {
                stats.printStats();
            } else if(answer.equals("e")) {
                running = false;
                return;
            } else {
                System.out.println("Incorrect input!");
            }
        }
    }
}
