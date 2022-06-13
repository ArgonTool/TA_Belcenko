package main;

import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class used to handle battle of players with monsters on monster Tiles.
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class Battle {

    /**
     * List of commands recognized in battle.
     */
    private enum Command {
        ATTACK,
        DEFEND,
        FLEE
    }

    /**
     * Initiates a battle between the player and a monster.
     * When the player has not discovered the monster yet, then they are counted as surprised and the first attack is executed by the monster.
     * After reading an input from the player they can either attack, defend or flee.
     * Attack: The player attacks with their damage amount.
     * Defend: Temporarily halves the attack damage of the monster, harms the monster by 1 point of damage in recoil.
     * Flee: Has a 1/5 chance to exit the battle while not killing the monster or the player.
     * If the monster dies the battle ends.
     * If the player dies the battle ends.
     * If the player flees the battle ends.
     * @param player the participating player.
     * @param monster the participating monster.
     * @param surprised whether the player knows that the monster is o that tile.
     */
    public void battle(Player player, Monster monster, boolean surprised) {
        String mname = monster.getName();
        int mdamage = monster.getDmg();
        System.out.println("You encounter a " + mname);
        battleLoop: while (true) {
            if (surprised) {
                if (player.getPlayer_class() == Player.PClass.THIEF) {
                    surprised = false;
                    continue;
                }
                System.out.println(mname + " attacks for " + mdamage + " damage");
                player.harm(mdamage);
                surprised = false;
                if (player.isDead()){
                    break;
                }
            }
            int tempMonsterDamage = mdamage;
            System.out.println(mname + " HP: " + monster.getHp() + " Player HP: " + player.getHp() + "/" + player.getMaxHp());
            System.out.println("Attack | Defend | Flee");
            switch (read()) {
                case ATTACK -> {
                    System.out.println("Attack the " + mname + " for " + mdamage + " damage");
                    monster.harm(player.getDmg());
                    if (monster.isDead()) {
                        System.out.println(mname + " defeated!\nYou feel stronger");
                        player.increaseDamage();
                        break battleLoop;
                    }
                }
                case DEFEND -> {
                    System.out.println("Defending");
                    tempMonsterDamage /= 2;
                }
                case FLEE -> {
                    if(ThreadLocalRandom.current().nextInt(1, 6) == 5) {
                        System.out.println("You successfully flee");
                        break battleLoop;
                    } else {
                        System.out.println("Escape unsuccessful");
                    }
                }
            }
            System.out.println(mname + " attacks for " + tempMonsterDamage + " damage");
            player.harm(tempMonsterDamage);
            if(player.isDead()) {
                break;
            }
        }
    }

    /**
     * Reads user input and checks for 3 words.
     * @return enum of type Command according to the command given
     */
    public Command read() {
        Command out;
        scanner: while (true) {
            System.out.print("> ");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            switch (input.toLowerCase(Locale.ENGLISH)) {
                case "defend":
                    out = Command.DEFEND;
                    break scanner;
                case "attack":
                    out = Command.ATTACK;
                    break scanner;
                case "flee":
                    out = Command.FLEE;
                    break scanner;
                default:
                    System.out.println("Wrong input");
            }
        }
        return out;
    }
}