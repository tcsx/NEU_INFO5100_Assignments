import java.util.Scanner;

public class GameDriver{
    public static void main(String[] args) {
    	System.out.println("How many players are there?");
    	Scanner scanner = new Scanner(System.in);
    	String s = scanner.nextLine().trim();
    	int num = Integer.parseInt(s);
        Game game = new Game(num);
        game.playAGame();
        while(true){
	        System.out.println("Play another game (y/n)?");
	        s = scanner.nextLine().trim();
	        if ("n".equals(s)) {
	        	scanner.close();
				return;
			}
	        System.out.println("How many players are there?");
	        s = scanner.nextLine().trim();
	        num = Integer.parseInt(s);
	        game = new Game(num);
	        game.playAGame();
        }
    }
}