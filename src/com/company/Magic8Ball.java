package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Magic8Ball {

    public static int randomNumber(int x){
        Random random = new Random();
        int num = random.nextInt(x);
        return num;
    }

    public void game(){
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> reply = new ArrayList<String>();

        reply.add("It is certain");
        reply.add("It is decidedly so");
        reply.add("Without a doubt");
        reply.add("Yes definitely");
        reply.add("You may rely on it");
        reply.add("As I see it, yes");
        reply.add("Most likely");
        reply.add("Outlook good");
        reply.add("Yes");
        reply.add("Signs point to yes");
        reply.add("Reply hazy, try again");
        reply.add("Ask again later");
        reply.add("Better not tell you now");
        reply.add("Cannot predict now");
        reply.add("Concentrate and ask again");
        reply.add("Don't count on it");
        reply.add("My reply is no");
        reply.add("My sources say no");
        reply.add("Outlook not so good");
        reply.add("Very doubtful");


        String input = "";

        while(!input.equalsIgnoreCase("n") ){
            System.out.print("YOU ASK: ");
            input = scanner.nextLine();

            System.out.println();
            System.out.print("Book SAYS: " + reply.get(randomNumber(reply.size())) + "\n");

            System.out.println("\nDo you have another question for the book?  (y/n)");
            input = scanner.nextLine();

            System.out.println();
        }
        System.out.println("The book snaps shut");
    }
}
