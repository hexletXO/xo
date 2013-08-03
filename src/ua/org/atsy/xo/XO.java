/*
Copyright (c) 2013 Evgeniy Dolgikh

See the file LICENSE for copying permission.
*/
package ua.org.atsy.xo;
import java.util.Scanner;

public class XO {
    private boolean running = true;
    Scanner in;
    public XO() {
        in = new Scanner(System.in);
        System.out.println(askUserInt("Int request"));
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
            if(askUser("Exit? y/n").equals("y")) {
                running = false;
            }
        }
        in.close();
    }
    private void printQuestion(String question) {
        System.out.print(question + ": ");
    }
}
