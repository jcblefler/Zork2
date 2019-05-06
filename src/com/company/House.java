package com.company;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class House {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    private static DecimalFormat df2 = new DecimalFormat("0.00");



    private double playerMoney;
    private int goblin = random.nextInt(7);;
    private int secretRoll = random.nextInt(4);
    private int lockedRoomOne = random.nextInt(5) + 3;
    private int lockedRoomTwo = random.nextInt(5) + 3;
    private int key;
    private String[] chestContent = {"nothing", "a ghost", "a book"};
    private int chestRandom = random.nextInt(chestContent.length);



    //create all of the rooms in the house and set the values
    Room room1 = new Room("Foyer", "a Dead Scorpion", "North", (random.nextInt(1001) + random.nextDouble()));
    Room room2 = new Room("Front Room", "Piano", "South, East, West", (random.nextInt(1001) + random.nextDouble()));
    Room room3 = new Room("Library", "Spiders", "North, East", (random.nextInt(1001) + random.nextDouble()));
    Room room4 = new Room("Kitchen", "Bats", "North, West", (random.nextInt(1001) + random.nextDouble()));
    Room room5 = new Room("Dining Room", "Dust and an Empty Box", "South", (random.nextInt(1001) + random.nextDouble()));
    Room room6 = new Room("Vault", "3 Walking Skeletons", "East", (random.nextInt(1001) + random.nextDouble()));
    Room room7 = new Room("Parlor", "Treasure Chest", "West, South", (random.nextInt(1001) + random.nextDouble()));
    Room room8 = new Room("Secret Room", "Piles of Gold", "West", (random.nextInt(1001) + random.nextDouble()));

    HashMap<Integer, Room> rooms;

    HashMap<Integer, Boolean> roomsVisited;
    HashMap<Integer, Boolean> roomsInspected;



    //Constructor
    public House(){
        //create a HashMap of all of the rooms created above
        rooms = new HashMap<>();
        rooms.put(1, room1);
        rooms.put(2, room2);
        rooms.put(3, room3);
        rooms.put(4, room4);
        rooms.put(5, room5);
        rooms.put(6, room6);
        rooms.put(7, room7);
        rooms.put(8, room8);

        //create a HashMap to check how many rooms the player has visits
        roomsVisited = new HashMap<Integer, Boolean>();
        roomsVisited.put(1, false);
        roomsVisited.put(2, false);
        roomsVisited.put(3, false);
        roomsVisited.put(4, false);
        roomsVisited.put(5, false);
        roomsVisited.put(6, false);
        roomsVisited.put(7, false);
        roomsVisited.put(8, false);

        //create a HashMap to check if player has inspected the room
        roomsInspected = new HashMap<Integer, Boolean>();
        roomsInspected.put(1, false);
        roomsInspected.put(2, false);
        roomsInspected.put(3, false);
        roomsInspected.put(4, false);
        roomsInspected.put(5, false);
        roomsInspected.put(6, false);
        roomsInspected.put(7, false);
        roomsInspected.put(8, false);
    }



    //run to start the game
    public void gameStart(){
        //sets the goblin in a random room
        rooms.get(goblin).setGoblin(true);

        //set the random doors to be locked
        rooms.get(lockedRoomOne).setLocked(true);
        rooms.get(lockedRoomTwo).setLocked(true);

        //prompt the user to start the game
        System.out.println("You see a spooky house.");

        //if user picks up the amulet they will start the game in a random room
        System.out.println("There is an amulet on the ground, do you want to pick it up? (y/n)");
        String userInputRandomRoom = scanner.next();
        if (userInputRandomRoom.equalsIgnoreCase("y")){
            room(random.nextInt(rooms.size()));

        } else {

            System.out.println("Do you want to go inside? (y/n)");
            String userInput = scanner.next();

            //if user enters house load the room method, else load gameEnd method
            if (userInput.equalsIgnoreCase("y")){
                room(1);
            } else {
                gameEnd();
            }
        }
    }

    //ends the game
    public void gameEnd(){

        int roomCount = 0;

        //.25 chance of a ghost following player
        int ghost = random.nextInt(5);

        //count how many rooms the user has visited by looking for true values in the roomsVisited HashMap
        for (boolean value : roomsVisited.values()) {
            if (value){
                roomCount++;
            }
        }
        System.out.println();
        if (roomCount == 0){
            System.out.println("You didn't go in the house.");
        } else {

            //display how many rooms the user visited and how much money they collected
            System.out.println("You visited " + roomCount + " out of " + rooms.size() + " rooms and kept $" + df2.format(getPlayerMoney()) + ".");
        }
        if (ghost == 4){
            System.out.println("There is a ghost following you!");
        }
        System.out.println("THANKS FOR PLAYING!");

    }

    //method to display what room the user is in
    public void room(int roomNumber){
        System.out.println(rooms.get(roomNumber).isLocked());
        System.out.println("\nYou are in the " + rooms.get(roomNumber).getRoomName() + " and you have $" + df2.format(getPlayerMoney()) + ".");

        //if user is in the same room as the goblin, set users money to zero and despawn goblin
        if (rooms.get(roomNumber).isGoblin() == true){
            System.out.println("You are grabbed by a goblin! It takes all of your money!");
            setPlayerMoney(0);
            System.out.println("The goblin runs away and you now have $" + df2.format(getPlayerMoney()));
            rooms.get(roomNumber).setRoomMoney(0);
            rooms.get(roomNumber).setGoblin(false);
        }

        //if the room has money, prompt the user to pick up money to add to user's total
        if (rooms.get(roomNumber).getRoomMoney() > 0){
            System.out.println("You see " + rooms.get(roomNumber).getRoomContent() + " and $" + df2.format(rooms.get(roomNumber).getRoomMoney()) + ".");
            System.out.println("Do you want to pick up the money? (y/n)");
            String userInput = scanner.next();
            if (userInput.equalsIgnoreCase("y")){
                setPlayerMoney(rooms.get(roomNumber).getRoomMoney() + getPlayerMoney());
                rooms.get(roomNumber).setRoomMoney(0);
                System.out.println("You now have $" + df2.format(getPlayerMoney()));
            }
        }
        //if room has zero money don't display money pick up prompt
        else {
            System.out.println("You see " + rooms.get(roomNumber).getRoomContent() + ".");
        }

        //player has visited the room set roomVisited for room to true
        roomsVisited.replace(roomNumber, false, true);


        //if player hasn't been inspected room then run inspect method
        if (roomsInspected.get(roomNumber) == false){
            System.out.println("Will you inspect anything? (y/n)");
            inspect(roomNumber);

        }

        if (!roomsInspected.get(6)) {
            if (secretRoll == 4 && roomNumber == 6) {
                System.out.println("You discovered a secret room. Press (enter) to go inside.");
            }
            System.out.println("You can go " + rooms.get(roomNumber).getDirection() + " or leave the house.");
            System.out.println("Where will you go?");
            System.out.println("(" + rooms.get(roomNumber).getDirection() + ", or Run)");

            move(roomNumber);
        }
    }


    //allows user to interact with items in the rooms
    public void inspect(int roomNumber){
        String userInput = scanner.next();

        //case corresponds to room number
        switch (roomNumber){
            case 1: if (userInput.equalsIgnoreCase("y")){
                System.out.println("You picked up the Dead Scorpion and put it in your pocket.\n");
                rooms.get(roomNumber).setRoomContent(" nothing.");
                roomsInspected.replace(roomNumber, true);
                }
                break;
            case 2: if (userInput.equalsIgnoreCase("y")){
                System.out.println("You found a key in the piano.\n");
                setKey(getKey() + 1);
                rooms.get(roomNumber).setRoomContent(" an empty piano");
                roomsInspected.replace(roomNumber, true);
                }
                break;
            case 3: if (userInput.equalsIgnoreCase("y")){
                if (getPlayerMoney() > 0){
                    System.out.println("You tried to pick up the Spiders.");
                    System.out.println("The Spiders bit you and stole half your money.");
                    System.out.println("You now have $" + df2.format((getPlayerMoney()/2)) + "\n");
                }
                roomsInspected.replace(roomNumber, true);

                }
                break;
            case 4: if (userInput.equalsIgnoreCase("y")){
                System.out.println("You reached for the bats and they flew out of reach.");
                roomsInspected.replace(roomNumber, true);
                }
                break;
            case 5: if (userInput.equalsIgnoreCase("y")) {
                System.out.println("What are you inspecting? (dust or box)");
                if (userInput.equalsIgnoreCase("dust")){
                    System.out.println("You reached for the dust and it blew away.");
                } else if (userInput.equalsIgnoreCase("box")){
                    System.out.println("You picked up the empty box.");
                    rooms.get(roomNumber).setRoomContent(" nothing.");
                }
                roomsInspected.replace(roomNumber, true);
                }
                break;
            case 6: if (userInput.equalsIgnoreCase("y")){
                System.out.println("You reached for the skeletons and they noticed you");
                System.out.println("They chased you out of the house and steal all of your money");
                setPlayerMoney(0);
                roomsInspected.replace(roomNumber, true);
                gameEnd();
                }
                break;
            case 7: if (userInput.equalsIgnoreCase("y")){
                System.out.println("You open the treasure chest and find " + chestContent[chestRandom] + " inside");
                if (chestContent[chestRandom].equalsIgnoreCase("a ghost")){
                    Hangman hangman = new Hangman();
                    System.out.println("The ghost wants to play game.");
                    System.out.println("Do you want to play? (y/n)");
                    String ghostInput = scanner.next();
                    if (ghostInput.equalsIgnoreCase("y")){
                        hangman.game();
                    } else {
                        System.out.println("The ghost flies away crying");
                    }
                } else if (chestContent[chestRandom].equalsIgnoreCase("a book")){
                    Magic8Ball magic8Ball = new Magic8Ball();
                    System.out.println("You pick up the book");
                    System.out.println("Inside the book it says \"Ask me anything\"");
                    System.out.println("Do you want to ask a question? (y/n)");
                    String bookInput = scanner.next();
                    if (bookInput.equalsIgnoreCase("y")){
                        magic8Ball.game();
                    } else {
                        System.out.println("The book snaps shut");
                    }
                }
                rooms.get(roomNumber).setRoomContent(" nothing.");
                roomsInspected.replace(roomNumber, true);
                }
                break;
            case 8: if (userInput.equalsIgnoreCase("y")){
                setPlayerMoney(getPlayerMoney() + 10000.00);
                System.out.println("You picked up the gold and now have " + getPlayerMoney());
                roomsInspected.replace(roomNumber, true);
                 }
                rooms.get(roomNumber).setRoomContent(" nothing.");
                break;
        }

    }

    public void move(int roomNumber){
        String userInput = scanner.next();

        //only recognizes the words [run, north, east, south, west] any other input will result in 'Please input again' and loops to top of method
        if (userInput.equalsIgnoreCase("run") || userInput.equalsIgnoreCase("north") || userInput.equalsIgnoreCase("east") || userInput.equalsIgnoreCase("south") || userInput.equalsIgnoreCase("west")){

            //input of run causes the gameEnd method to run
            if (userInput.equalsIgnoreCase("run")){
                gameEnd();
            }

            //case for each room and has which corresponding room should be loaded according to each inputs
            switch (roomNumber){
                case 1: if (userInput.equalsIgnoreCase("north")){
                    room(2);
                }
                    break;
                case 2: if (userInput.equalsIgnoreCase("south")){
                    room(1);
                } else if (userInput.equalsIgnoreCase("west")){
                    room(3);
                } else if (userInput.equalsIgnoreCase("east")){
                    room(4);
                }
                    break;
                case 3: if (userInput.equalsIgnoreCase("north")){
                    room(5);
                } else if (userInput.equalsIgnoreCase("east")){
                    room(2);
                }
                    break;
                case 4: if (userInput.equalsIgnoreCase("north")){
                    room(7);
                } else if (userInput.equalsIgnoreCase("west")){
                    room(2);
                }
                    break;
                case 5: if (userInput.equalsIgnoreCase("south")){
                    room(3);
                }
                    break;
                case 6: if (userInput.equalsIgnoreCase("east")){
                    room(7);
                } else if (secretRoll == 4 && userInput.equalsIgnoreCase("enter")){
                    room(8);
                }
                    break;
                case 7: if (userInput.equalsIgnoreCase("west")){
                    room(6);
                }else if (userInput.equalsIgnoreCase("south")){
                    room(4);
                }
                    break;
                case 8: if (userInput.equalsIgnoreCase("west")){
                    room(6);
                }
                    break;
            }
        } else {
            System.out.println("Please input again.");
            move(roomNumber);
        }

    }



    public double getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(double playerMoney) {
        this.playerMoney = playerMoney;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

}
