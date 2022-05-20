import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello world");
        Map map = new Map();
        map.generateMap();
        //map.render();
        try{
            String fileName= "src/saved_files/map.txt";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            String fileName= "src/saved_files/map.txt";
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Map map2 = (Map) ois.readObject();
            ois.close();
            map2.render();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
