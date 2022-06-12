package main;

public class CommandStats implements ICommand{

    private String keyword;
    private Player player;

    public CommandStats(Player player) {
        this.player = player;
        this.keyword = "stats";
    }

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
    public void execute(String parameter) {
        System.out.println("HP: " + player.getHp() + '/' + player.getMaxHp() + " DMG: " + player.getDmg());

    }
}
