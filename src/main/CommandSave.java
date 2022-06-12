package main;

import java.io.*;

public class CommandSave implements ICommand {

    private Save save;

    public CommandSave(Game game, Player player, MyMap map) {
        this.save = new Save(game, player, map);
    }

    @Override
    public String getKeyword() {
        return "save";
    }

    @Override
    public void execute(String parameter) {
        try {
            File f = new File("./src/saved_files/gameSave");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(save);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
