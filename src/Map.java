import java.io.Serializable;

public class Map implements Serializable{
    Tile[][] arr;
    int size = 40;

    public void generateMap(){
        arr = new Tile[size/2][size];
        for (int i = 0; i < size/2; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = new Tile(Tile.Type.NOTHING);
            }
        }
    }

    public void render(){
        StringBuffer buff = new StringBuffer();
        for (Tile[] row : arr) {
            for (Tile column: row) {
                try {
                    buff.append(column.getIcon());
                }catch(NullPointerException e){
                    System.out.println("ERROR: MAP GENERATION");
                    return;
                }
            }
            buff.append('\n');
        }
        System.out.println(buff.toString());
    }

    /*public static void serializeDataOut()throws IOException {
        String fileName= "map.txt";
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject();
        oos.close();
    }*/

}
