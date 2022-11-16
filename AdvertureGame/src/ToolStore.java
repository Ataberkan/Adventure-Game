import java.util.Scanner;

public class ToolStore extends NormalLocation {

	Scanner scan = new Scanner(System.in);

	ToolStore(Player player) {
		super(player, "Tool Store");
	}


	public boolean getLocation() {
		System.out.println("Money  : " + player.getMoney());
		System.out.println("1-) Weapons");
		System.out.println("2-) Armour");
		System.out.println("3-) Exit");
		System.out.print("Your choice : ");
		int selTool = scan.nextInt();
		int selItemID;
		switch (selTool) {
		case 1:
			selItemID = weaponMenu();
			buyWeapon(selItemID);
			break;
		case 2:
			selItemID = armorMenu();
			buyArmor(selItemID);
			break;
		default:
			break;
		}

		return true;
	}
	
	public int armorMenu() {
		System.out.println("1-) Entry Level          Money : 15 - Hasar : 1");
		System.out.println("2-) Intermediate Level   Money : 25 - Hasar : 3");
		System.out.println("3-) High Level           Money : 40 - Hasar : 5");
		System.out.println("4-) Exit");
		System.out.print("Your Choice : ");
		int selArmorID = scan.nextInt();
		return selArmorID;
	}
	
	public void buyArmor(int itemID) {
		int avoid = 0, price = 0;
		String aName = null;
		switch (itemID) {
		case 1:
			avoid = 1;
			aName = "Entry Level";
			price = 15;
			break;
		case 2:
			avoid = 3;
			aName = "Intermediate Level";
			price = 15;
			break;
		case 3:
			avoid = 5;
			aName = "High Level";
			price = 40;
			break;
		case 4:
			System.out.println("Exiting");
			break;
		default:
			System.out.println("Invalid choice! ");
			break;
		}

		if (price > 0) {
			if (player.getMoney() >= price) {
				player.getInv().setArmour(avoid);
				player.getInv().setaName(aName);
				player.setMoney(player.getMoney() - price);
				System.out.println("You bought "+aName + " Blocked Damage : " + player.getInv().getArmour());
				System.out.println("Money :" + player.getMoney());
			} else {
				System.out.println("You do not have enough money !");
			}
		}
	}

	public int weaponMenu() {
		System.out.println("1-) Gun    Para : 25 - Hasar : 2");
		System.out.println("2-) Sword     Para : 35 - Hasar : 3");
		System.out.println("3-) Rifle     Para : 45 - Hasar : 7");
		System.out.println("4-) Exit");
		System.out.print("Your choice : ");
		int selWeaponID = scan.nextInt();
		return selWeaponID;
	}

	public void buyWeapon(int itemID) {
		int damage = 0, price = 0;
		String wName = null;
		switch (itemID) {
		case 1:
			damage = 2;
			wName = "Gun";
			price = 25;
			break;
		case 2:
			damage = 3;
			wName = "Sword";
			price = 35;
			break;
		case 3:
			damage = 7;
			wName = "Rifle";
			price = 45;
			break;
		case 4:
			System.out.println("Exiting");
			break;
		default:
			System.out.println("Invalid choice");
			break;
		}

		if (price > 0) {
			if (player.getMoney() > price) {
				player.getInv().setDamage(damage);
				player.getInv().setwName(wName);
				player.setMoney(player.getMoney() - price);
				System.out.println("You bought "+wName + " Previous Damage :" + player.getDamage() + ", New Damage : "
						+ player.getTotalDamage());
				System.out.println("Money :" + player.getMoney());
			} else {
				System.out.println("You do not have enough money");
			}
		}
	}
	}
