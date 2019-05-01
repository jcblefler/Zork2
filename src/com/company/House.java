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

    int secretRoll = random.nextInt(4);




    Room room1 = new Room("Foyer", "a Dead Scorpion", "North", (random.nextInt(1001) + random.nextDouble()));
    Room room2 = new Room("Front Room", "Piano", "South, East, West", (random.nextInt(1001) + random.nextDouble()));
    Room room3 = new Room("Library", "Spiders", "North, East", (random.nextInt(1001) + random.nextDouble()));
    Room room4 = new Room("Kitchen", "Bats", "North, West", (random.nextInt(1001) + random.nextDouble()));
    Room room5 = new Room("Dining Room", "Dust and an Empty Box", "South", (random.nextInt(1001) + random.nextDouble()));
    Room room6 = new Room("Vault", "3 Walking Skeletons", "East", (random.nextInt(1001) + random.nextDouble()));
    Room room7 = new Room("Parlor", "Treasure Chest", "West, South", (random.nextInt(1001) + random.nextDouble()));
    Room room8 = new Room("Secret Room", "Piles of Gold", "West", (random.nextInt(1001) + random.nextDouble()));

    HashMap<Integer, Room> rooms;

    HashMap<Integer, Boolean> roomsVisisted ;

    public House(){
        rooms = new HashMap<>();
        rooms.put(1, room1);
        rooms.put(2, room2);
        rooms.put(3, room3);
        rooms.put(4, room4);
        rooms.put(5, room5);
        rooms.put(6, room6);
        rooms.put(7, room7);
        rooms.put(8, room8);

        roomsVisisted = new HashMap<Integer, Boolean>();
        roomsVisisted.put(1, false);
        roomsVisisted.put(2, false);
        roomsVisisted.put(3, false);
        roomsVisisted.put(4, false);
        roomsVisisted.put(5, false);
        roomsVisisted.put(6, false);
        roomsVisisted.put(7, false);
        roomsVisisted.put(8, false);

    }

    public void gameStart(){
        System.out.println("You see a spooky house.");
        System.out.println("There is an amulet on the ground, do you want to pick it up? (y/n)");
        String userInputRandomRoom = scanner.next();
        if (userInputRandomRoom.equalsIgnoreCase("y")){
            room(random.nextInt(rooms.size()));

        } else {
            System.out.println("Do you want to go inside? (y/n)");
            String userInput = scanner.next();

            if (userInput.equalsIgnoreCase("y")){
                room(1);
            } else {
                gameEnd();
            }
        }
    }

    public void gameEnd(){

        int roomCount = 0;
        int ghost = random.nextInt(5);

        for (boolean value : roomsVisisted.values()) {
            if (value){
                roomCount++;
            }
        }
        System.out.println();
        if (roomCount == 0){
            System.out.println("You didn't go in the house.");
        } else {
            System.out.println("You visited " + roomCount + " out of " + rooms.size() + " rooms and kept $" + df2.format(getPlayerMoney()) + ".");
        }
        if (ghost == 4){
            System.out.println("There is a ghost following you!");
        }
        System.out.println("THANKS FOR PLAYING!");

    }

    public void room(int roomNumber){

        roomsVisisted.replace(roomNumber, true);
        System.out.println("\nYou are in the " + rooms.get(roomNumber).getRoomName() + " and you have $" + df2.format(getPlayerMoney()) + ".");
        if (rooms.get(roomNumber).getRoomMoney() > 0){
            System.out.println("You see " + rooms.get(roomNumber).getRoomContent() + " and $" + df2.format(rooms.get(roomNumber).getRoomMoney()) + ".");
            System.out.println("Do you want to pick up the money? (y/n)");
            String userInput = scanner.next();
            if (userInput.equalsIgnoreCase("y")){
                setPlayerMoney(rooms.get(roomNumber).getRoomMoney() + getPlayerMoney());
                rooms.get(roomNumber).setRoomMoney(0);
                System.out.println("You now have $" + df2.format(getPlayerMoney()));
            }
        } else {
            System.out.println("You see " + rooms.get(roomNumber).getRoomContent() + ".");
        }
        if (secretRoll == 4 && roomNumber == 6){
            System.out.println("You discovered a secret room. Press (enter) to go inside.");
        }
        System.out.println("You can go " + rooms.get(roomNumber).getDirection() + " or leave the house.");
        System.out.println("Where will you go?");
        System.out.println("(" + rooms.get(roomNumber).getDirection() + ", or Run)");

        move(roomNumber);

    }

    public void move(int roomNumber){
        String userInput = scanner.next();

        if (userInput.equalsIgnoreCase("run") || userInput.equalsIgnoreCase("north") || userInput.equalsIgnoreCase("east") || userInput.equalsIgnoreCase("south") || userInput.equalsIgnoreCase("west")){
            if (userInput.equalsIgnoreCase("run")){
                gameEnd();
            }

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
}
