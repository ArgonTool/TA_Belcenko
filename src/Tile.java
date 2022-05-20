import java.io.Serializable;

public class Tile implements Serializable{

    enum Type{
        TRAP,
        CHEST,
        ENTRANCE,
        EXIT,
        NOTHING,
        WALL
    }
    private Type tileType;

    enum Status{
        EXPLORED,
        NOT_EXPLORED
    }

    private Status status;
    private String text;
    private char icon;

    public Tile(Type tileType) {
        this.tileType = tileType;
        switch (tileType){
            case TRAP -> {
                this.text = "";
                this.icon = 'o';
            }
            case CHEST -> {
                this.text = "";
                this.icon = 'c';
            }
            case ENTRANCE -> {
                this.text = "";
            }
            case EXIT -> this.text = "";
            case NOTHING -> {
                this.text ="Nothing interesting";
                this.icon = '+';
            }
            case WALL -> {
                this.text="You shouldn't be in  a wall";
                this.icon = '0';
            }
        }
        this.status = Status.NOT_EXPLORED;
    }

    public char getIcon() {
        return icon;
    }

    public String getText(){
        return null;
    }
}
