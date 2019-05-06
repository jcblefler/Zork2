package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    static String randomWord(){
        Random random = new Random();

        ArrayList<String> words = new ArrayList<String>();
        words.add("tree");
        words.add("rain");
        words.add("bear");
        words.add("encourage");
        words.add("promise");
        words.add("soup");
        words.add("chess");
        words.add("insurance");
        words.add("pancakes");
        words.add("stream");
        words.add("envelope");
        words.add("shoe");
        words.add("cellphone");
        words.add("handbag");
        words.add("accordion");
        words.add("suboptimal");
        words.add("lemonade");
        words.add("guarantee");
        words.add("computer");
        words.add("coffee");
        words.add("mouse");
        words.add("assignment");
        words.add("college");
        words.add("keyboard");
        words.add("information");
        words.add("technology");
        words.add("mitochondria");
        words.add("epidermis");
        words.add("paranormal");
        words.add("coral");
        words.add("anemone");
        words.add("urchin");
        words.add("quercus");
        words.add("lupin");
        words.add("ursus");
        words.add("education");
        words.add("experience");
        words.add("waffles");


        int ranNum = random.nextInt(words.size());
        return words.get(ranNum);

    }


    public void game(){
        Scanner scanner = new Scanner(System.in);

        int guessleft = 0;
        String input;
        String word = randomWord();
        boolean correct;


        String[] wordLetters = word.split("");
        System.out.println(wordLetters.length);

        //creates an ArrayList of UnderScores with length equal to the correct word
        ArrayList<String> blankWord = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++){
            blankWord.add("_ ");
        }

        System.out.println("Welcome, let's play hangman!");
        System.out.print("Here is the word I am thinking of: ");
        //displays the blank/guess line
        for (int i=0; i < blankWord.size(); i++){
            System.out.print(blankWord.get(i));
        }


        while(guessleft < 6){

            correct = false;

            //prompts user for guess
            System.out.print("\n\nEnter letter or word guess: ");
            input = scanner.nextLine();

            //if input equals to the word exits the program
            if (input.equalsIgnoreCase(word)){
                System.out.println("\nYou Won!");
                break;
            }

            //check if guess input equals a letter in the word, replaces _ with the correct letter
            System.out.print("Your guess so far: ");
            for (int i=0; i <word.length(); i++){
                if(input.equalsIgnoreCase(wordLetters[i])){
                    blankWord.set(i, input + " ");
                    correct = true;
                }
            }

            //displays the blank/guess line
            for (int i=0; i < blankWord.size(); i++){
                System.out.print(blankWord.get(i));
            }

            //converts the blank/guess line into a string to check it against the word
            //to see if the player wins
            StringBuilder sb = new StringBuilder();
            for (String s : blankWord){
                sb.append(s);
            }
            String check = sb.toString();

            if (word.equalsIgnoreCase(check.replaceAll("\\s+",""))){
                System.out.println("\nYou Won!");
                break;
            }

            //if input is wrong then adds one to guess left and displays text
            if (correct == false){
                guessleft++;
                System.out.println("\nYou have guessed incorrectly " + guessleft + "/6 times.");
            }





        }
        System.out.println("The ghost flies away laughing");
        System.out.println();
    }


}
