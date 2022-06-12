package main;

public class CommandMove implements ICommand{

    private String keyword;
    private MyMap map;
    private Player player;
    private static final String OUT_OF_BOUNDS = "Out of Bounds";

    public CommandMove(Player player, MyMap map) {
        this.keyword = "move";
        this.player = player;
        this.map = map;
    }

    @Override
    public String getKeyword() {
        return keyword;
    }

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
