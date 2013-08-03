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
    }
    private String askUser(String question) {
        System.out.print(question + ": ");
        return  in.next();
    }
    public void mainLoop() {
        while(running) {
            if(askUser("Exit? y/n").equals("y")) {
                running = false;
            }
        }
        in.close();
    }
}
