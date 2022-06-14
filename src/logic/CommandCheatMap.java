package logic;

/**
 * Provides a cheat map to look at
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class CommandCheatMap implements ICommand {

    private final MyMap map;

    /**
     * Constructor
     * @param map map used
     */
    public CommandCheatMap(MyMap map) {
        this.map = map;
    }

    /**
     * Returns keyword.
     * @return keyword
     */
    @Override
    public String getKeyword() {
        return "cheat_map";
    }

    /**
     * Uses the render without player method to render a complete map.
     * @param parameter String fed to the command
     */
    @Override
    public void execute(String parameter) {
        map.render();
    }
}
