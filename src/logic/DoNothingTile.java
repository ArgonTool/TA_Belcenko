package logic;

/**
 * Class intended for tiles that don't have any special effects.
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class DoNothingTile extends Tile {

    /**
     * Constructor
     * @param tileType type of tile
     */
    public DoNothingTile(Type tileType) {
        super(tileType);
    }

    /**
     * Does nothing
     * @param player player
     */
    @Override
    public void activate(Player player) {

    }

    /**
     * Returns false
     * @return false
     */
    @Override
    public boolean isHidden() {
        return false;
    }
}
