import java.util.Scanner;

public abstract class BattleLocation extends Location {
	protected Obstacle obs;
	protected String award;
	
	Scanner scan = new Scanner(System.in);

	BattleLocation(Player player, String name, Obstacle obs,String award) {
		super(player);
		this.name = name;
		this.obs = obs;
		this.award = award;
	}

	public boolean getLocation() {
		int obsNumber = obs.number();
		System.out.println("You are here " + this.getName());
		System.out.println("Be careful, there are/is " + obsNumber + " " + obs.getName());
		System.out.print("<F>ight or <E>scape : ");
		String selCase = scan.nextLine();
		selCase = selCase.toUpperCase();
		if (selCase.equals("F")) {
			if (fight(obsNumber)) {
				System.out.println("You killed all enemies in " + this.getName() + "!");
				if(this.award.equals("Food") && player.getInv().isFood() == false) {
					System.out.println("You gain a " +this.award);
					player.getInv().setFood(true);
				}else if(this.award.equals("Water") && player.getInv().isWater() == false) {
					System.out.println("You gain a " +this.award);
					player.getInv().setWater(true); 
				} else if(this.award.equals("Firewood") && player.getInv().isFirewood() == false) {
					System.out.println("You gain a " +this.award);
					player.getInv().setFirewood(true);
				}
				return true;
			}
			if(player.getHealth() <= 0) {
				System.out.println("You died");
				return false;
			}
		}
		return true;
	}

	public boolean fight(int obsNumber) {
		playerStats();
		for(int i = 0; i < obsNumber; i++) {
			monsterStats();
			int defObsHealth = obs.getHealth();
			while(player.getHealth() > 0 && obs.getHealth() > 0) {
				
				System.out.print("<H>it or <E>scape : ");
				String selCase = scan.nextLine();
				selCase = selCase.toUpperCase();
				if(selCase.equals("H")) {
					System.out.println("You attacked the " + obs.getName() + "!");
					obs.setHealth(obs.getHealth() - player.getTotalDamage());
					afterAttack();
					if(obs.getHealth() > 0) {
						System.out.println(obs.getName() + " attacked you!");
						player.setHealth(player.getHealth() - (obs.getDamage() - player.getInv().getArmour()));
						afterAttack();
					}
				}else {
					return false;
				}
			}
			if(obs.getHealth() <= 0 && player.getHealth() > 0 ) {
				System.out.println("You killed your enemie! ");
				player.setMoney(player.getMoney() + obs.getAward());
				System.out.println("Money : " + player.getMoney());
				obs.setHealth(defObsHealth);
			}else {
				return false;
			}
			System.out.println("---------------------------");
		}
		return true;
	}
	public void playerStats() {
		System.out.println("==========================");
		System.out.println("Player Datas");
		System.out.println("Health -> " + player.getHealth());
		System.out.println("Damage -> " + player.getDamage());
		System.out.println("Money -> " + player.getMoney());
		if(player.getInv().getDamage() > 0) {
			System.out.println("Weapon -> " +player.getInv().getwName());
		}
		if(player.getInv().getArmour() > 0) {
			System.out.println("Armour ->" + player.getInv().getArmour());
		}
	}
	public void afterAttack() {
		System.out.println("Your health : " +player.getHealth());
		System.out.println(obs.getName() +"'s health : " +obs.getHealth());
		System.out.println();
	}
	public void monsterStats() {
		System.out.println("================================");
		System.out.println("Monster Datas");
		System.out.println("Health of monster -> " + obs.getHealth());
		System.out.println("Damage of monster -> " + obs.getDamage());
		System.out.println("Award of monster -> " + obs.getAward());
	}
}
