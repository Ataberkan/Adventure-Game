import java.util.Scanner;

public class Game {
	Player player;
	Location location;
	Scanner scan = new Scanner(System.in);

	public void login() {

		System.out.println("Welcome to Advanture Game !");
		System.out.println("Please enter your name : ");
		String playerName = scan.nextLine();
		player = new Player(playerName);
		player.selectCha();
		start();

	}

	public void start() {
		while (true) {
			System.out.println();
			System.out.println("====================================");
			System.out.println();
			System.out.println("Please select a place you want to be there!");
			System.out.println("1-) Safe House =====> You will be safe and you will recover!");
			System.out.println("2-) Cave =====> Possible to encounter with zombie between 1-3!");
			System.out.println("3-) Forest =====> Possible to encounter with vampire between 1-3!");
			System.out.println("4-) River =====> Possible to encounter with bear between 1-3!");
			System.out.println("5-) ToolStore =====> You can put armour and weapon on!");
			int selLoc = scan.nextInt();
			while(selLoc < 1 || selLoc > 5) {
				System.out.println("Please select valid number !");
				selLoc = scan.nextInt();
			}
			switch (selLoc) {
			case 1: 
				location = new SafeHouse(player);
			break;
			case 2: 
				location = new Cave(player);
				break;
			case 3: 
				location = new Forest(player);
				break;
			case 4: 
				location = new River(player);
				break;
			case 5:
				location = new ToolStore(player);
				break;
			default: 
				location = new SafeHouse(player);
			break;
			}
			if(location.getClass().getName() == "SafeHouse") {
				if(player.getInv().isFirewood() && player.getInv().isFood() && player.getInv().isWater()) {
					System.out.println("Congratulations you won!");
					break;
				}
			}
			if(!location.getLocation()) {
				System.out.println("Game over!");
				break;
			}
		}
	}
}
