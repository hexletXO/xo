/*
Copyright (c) 2013 Evgeniy Dolgikh

See the file LICENSE for copying permission.
*/
package ua.org.atsy.xo;
import ua.org.atsy.xo.defs.GameType;
import ua.org.atsy.xo.player.Player;
import java.util.Scanner;
import java.util.Random;

public class XO {
    private boolean running = true;
    private GameStats stats;
    private Scanner in;
    private Player player1;
    private Player player2;
    private Player turnPlayer;
    private Field field;
    public XO() {
        in = new Scanner(System.in);
        stats = new GameStats();
        field = new Field();
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
        player1 = new Player(askUser("Enter name for first player"),'x');
        if(type == GameType.PVP)
            player2 = new Player(askUser("Enter name for second player"),'o');
        else if(type == GameType.PVC) {
            System.out.println("This game type not implementer yet");
            return;
        }
        else {
            System.out.println("This game type not implementer yet");
            return;
        }
        stats.resetTurns();
        field.eraseField();
        firstTurnPlayerRandom();
        gameLoop();
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
    private void firstTurnPlayerRandom() {
        Random rnd = new Random();
        if(rnd.nextInt(100)%2 == 0)
            turnPlayer = player1;
        else
            turnPlayer = player2;
        System.out.println("In this round " + turnPlayer.getName() + " chosen for first turn");
    }
    private void gameLoop() {
        String out = "\nGame menu.\n" +
                "x,x - make turn(enter h for details)\n" +
                "s - print stats\n" +
                "p - print field again\n" +
                "h - help\n" +
                "a - abort match\n";
        String help = "";
        String finishedGameMessage = "Game finished.\n";
        String answer;
        while(true) {
            field.printField();
            answer = askUser(out);
            if(answer.equals("s"))
                stats.printStats();
            else if(answer.equals("p"))
                field.printField();
            else if(answer.equals("h"))
                System.out.println(help);
            else if(answer.equals("a")) {
                field.eraseField();
                stats.resetTurns();
                return;
            } else if(answer.length() == 3 && answer.charAt(1) == ',') {
                if(!doTurn(answer)) {
                    System.out.println("Incorrect coordinates, or cell already used!");
                    continue;
                }

            } else {
                System.out.println("Incorrect input!");
                continue;
            }
            if(field.haveWinner()) {
                if(player1.getSymbol() == field.getWinner()) {
                    finishedGameMessage += "Winner is " + player1.getName();
                    stats.increaseScores(1);
                } else {
                    stats.increaseScores(1);
                    finishedGameMessage += "Winner is " + player2.getName();
                }
                stats.increaseGameFinished();
                System.out.println(finishedGameMessage);
                stats.printStats();
                return;
            }
            else if(field.isDraw(false)) {
                finishedGameMessage += "Is no winner in this match\n";
                stats.increaseGameFinished();
                System.out.println(finishedGameMessage);
                stats.printStats();
                return;
            }
            switchPlayer();
        }
    }
    private int[] parseCoordinates(String answer) {
        String[] tmp = answer.split(",");
        int[] result = new int[2];
        result[0] = Integer.parseInt(tmp[0]);
        result[1] = Integer.parseInt(tmp[1]);
        return result;
    }
    private boolean doTurn(String answer) {
        int[] cellCoordinates = parseCoordinates(answer);
        for(int val: cellCoordinates) {
            if(val <= 0 || val >= field.getFieldSize()+1 || field.getCellValue(cellCoordinates[0]-1,
                    cellCoordinates[1]-1) != ' ')
                return false;
        }
        field.setCellValue(cellCoordinates[0]-1, cellCoordinates[1]-1, turnPlayer.getSymbol());
        stats.increaseTurn();
        return true;
    }
    private void switchPlayer() {
        if(turnPlayer == player1)
            turnPlayer = player2;
        else
            turnPlayer = player1;
    }
}
