package com.evs.gameoflife;

public class Main {

  public static void main(String[] args){
    Game game = new Game();
    long width = 10;
    long height = 10;

    if (args.length == 2){
      try {
        width = Long.valueOf(args[0]);
        height = Long.valueOf(args[1]);
      } catch (NumberFormatException e){
        System.out.println("Please set numerical arguments");
      }
    }

    game.play(width,height);

  }

}
