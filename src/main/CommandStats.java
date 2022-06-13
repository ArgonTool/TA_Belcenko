package main;

/**
 * Implements ICommand
 * Class intended to write out the current statistics of the player.
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class CommandStats implements ICommand {

    private final String keyword;
    private final Player player;

    /**
     * Constructor, keyword is stats
     * @param player current player
     */
    public CommandStats(Player player) {
        this.player = player;
        this.keyword = "stats";
    }

    /**
     * Returns keyword
     * @return keyword
     */
    @Override
    public String getKeyword() {
        return keyword;
    }

    /**
     * Prints out the current stats of the player, HP and DMG
     * @param parameter not needed here but for other commands
     */
    @Override
    public void execute(String parameter) {
        System.out.println("HP: " + player.getHp() + '/' + player.getMaxHp() + " DMG: " + player.getDmg());

    }
}
