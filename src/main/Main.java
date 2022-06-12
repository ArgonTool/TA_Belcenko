package main;

public class Main {


    public static void main(String[] args) throws Exception {

        UI ui = new UI();
        ui.start();

        /*
        try {
            map.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File f = new File("./src/saved_files/saved_map.txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }
}
