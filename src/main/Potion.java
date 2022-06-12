package main;

public class Potion extends Tile{

    /**
     * Constructor for potion
     */
    public Potion() {
        super(Type.POTION);
    }

    /**
     * After activation restores the player's health
     * @param player which player to restore
     */
    @Override
    public void activate(Player player) {
        player.restore();
        System.out.println("You've found a potion. After you drink it you feel revitalised\nHP " + player.getHp() + "/" + player.getMaxHp());
    }
}
