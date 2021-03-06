package logic;

/**
 * Extends Tile
 * Class representing a poison Tile
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class Potion extends Tile {

    private boolean deactivate;

    /**
     * Constructor for potion
     */
    public Potion() {
        super(Type.POTION);
        this.deactivate = false;
    }

    /**
     * After activation restores the player's health
     * @param player which player to restore
     */
    @Override
    public void activate(Player player) {
        if (deactivate) {
            System.out.println("Remains of a drunken potion");
            return;
        }
        player.restore();
        System.out.println("You've found a potion. After you drink it you feel revitalised\nHP " + player.getHp() + "/" + player.getMaxHp());
        deactivate = true;
    }

    /**
     * Returns false, needed for other tiles.
     * @return false
     */
    @Override
    public boolean isHidden() {
        return false;
    }

}
