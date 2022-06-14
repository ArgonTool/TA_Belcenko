package logic;

/**
 * Implements ICommand
 * Class used to move around the map, to change the position of the player later displayed
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class CommandMove implements ICommand {

    private final String keyword;
    private final MyMap map;
    private final Player player;
    private static final String OUT_OF_BOUNDS = "Out of Bounds";

    /**
     * Constructor, keyword is move.
     * @param player to change player position
     * @param map to know whether the movement is possible
     */
    public CommandMove(Player player, MyMap map) {
        this.keyword = "move";
        this.player = player;
        this.map = map;
    }

    /**
     * Returns keyword needed to activate command.
     * @return keyword
     */
    @Override
    public String getKeyword() {
        return keyword;
    }

    /**
     * Checks for parameter.
     * Checks input for direction, checks if it is possible to move in that direction, repositions player.
     * Checks if current position is a wall, returns player back to original position.
     * Reveals the map around the player.
     * @param direction parameter indicating movement direction
     */
    @Override
    public void execute(String direction) {
        if (direction == null) {
            System.out.println("No parameter");
            return;
        }
        int x = player.getPosX();
        int y = player.getPosY();
        boolean reveal = false;
        switch (direction) {
            case "up" -> {
                if (x - 1 < 0) {
                    System.out.println(OUT_OF_BOUNDS);
                    break;
                }
                player.setPosX(x - 1);
                reveal = true;
            }
            case "down" -> {
                if (x + 1 > map.getDimensions()[0] - 1) {
                    System.out.println(OUT_OF_BOUNDS);
                    break;
                }
                player.setPosX(x + 1);
                reveal = true;
            }
            case "left" -> {
                if (y - 1 < 0) {
                    System.out.println(OUT_OF_BOUNDS);
                    break;
                }
                player.setPosY(y - 1);
                reveal = true;
            }
            case "right" -> {
                if (y + 1 > map.getDimensions()[1] - 1) {
                    System.out.println(OUT_OF_BOUNDS);
                    break;
                }
                player.setPosY(y + 1);
                reveal = true;
            }
            default -> System.out.println("Wrong parameter");
        }
        if (map.getTile(player.getPosX(), player.getPosY()).getTileType() == Tile.Type.WALL) {
            System.out.println("Walked into wall");
            player.setPosX(x);
            player.setPosY(y);
        } else if (reveal) {
            map.reveal(player);
            map.render(player);
        }
    }
}
