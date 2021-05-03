package TestMancalaConsole;

import java.util.Scanner;

import MancalaBackend.*;
import MancalaBackend.MancalaBoard.PitSize;

public class MancalaConsole {
    public static void main(String[] args) {
        System.out.println("Mancala testing console");
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Board setting: Pit size [3, 4]");
        PitSize size = Integer.parseInt(sc.nextLine()) == 3 ? (PitSize) MancalaBoard.PitSize.Three : MancalaBoard.PitSize.Four;
        
        MancalaBoard board = new MancalaBoard(size);
        while(!board.isGameOver()) {
        	System.out.println("Board:");
            printBoard(board);
            
            String player = getTurnStr(board.getTurn());
            System.out.println("Turn: " + player);
            System.out.println(player+", choose the pit.");
            
            char pit = Character.toLowerCase(sc.nextLine().charAt(0));
            if(board.getTurn() == MancalaBoard.PlayerTypes.PlayerA) {
            	pit = (char) (pit - 'a');
            } else {
            	pit = (char) (('f'-'a')-(pit-'a'));
            }
            boolean freeTurn = board.pickUpStones(pit);
            if(!freeTurn) {
            	board.nextTurn();
            }
        }
        
        sc.close();
    }
    
    private static void printBoard(MancalaBoard board) {
    	MancalaPit[] playerAPits = board.getPlayerAPits();
    	MancalaPit[] playerBPits = board.getPlayerBPits();
    	StringBuffer output = new StringBuffer();
    	
    	output.append("   ");
    	for(int i = playerBPits.length-2; i >= 0; i--) {
    		output.append(" " + playerBPits[i].getContainedCluster().getStoneCount()+" ");
    	}
    	output.append("\n " + playerBPits[6].getContainedCluster().getStoneCount() + " ").append(" a  b  c  d  e  f ").append(" " + playerAPits[6].getContainedCluster().getStoneCount() + " \n");
    	output.append("   ");
    	for(int i = 0; i < playerAPits.length-1; i++) {
    		output.append(" " + playerAPits[i].getContainedCluster().getStoneCount()+" ");
    	}
    	System.out.println(output.toString());
    }
    
    private static String getTurnStr(MancalaBoard.PlayerTypes t) {
    	return t == MancalaBoard.PlayerTypes.PlayerA ? "PlayerA" : "PlayerB";
    }
}
