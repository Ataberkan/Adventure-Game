
public class SafeHouse extends NormalLocation {

	SafeHouse(Player player) {
		super(player, "Safe House");
	}

	public boolean getLocation() {
		player.setHealth(player.getrHealth());
		System.out.println("You recovered");
		System.out.println("You are in Safe House!");
		return true;
	}
	
}
