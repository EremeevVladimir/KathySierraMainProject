package Book.GameSaver;

import java.io.*;

public class GameSaver {
    public static void main(String[] args) {
        GameCharacter one = new GameCharacter(50, "Elf", new String[]{"bow", "sword", "brass knuckles"});
        GameCharacter two = new GameCharacter(200, "Troll", new String[]{"claws", "large axe"});
        GameCharacter three = new GameCharacter(120, "Mage", new String[]{"magick", "invisibility"});

        try {
            ObjectOutputStream os = new ObjectOutputStream((new FileOutputStream("Game.ser")));
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        one = null;
        two = null;
        three = null;

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Game.ser"));
            GameCharacter oneRestore = (GameCharacter) inputStream.readObject();
            GameCharacter twoRestore = (GameCharacter) inputStream.readObject();
            GameCharacter threeRestore = (GameCharacter) inputStream.readObject();

            System.out.println("1st character type: " + oneRestore.getType());
            System.out.println("2nd character type: " + twoRestore.getType());
            System.out.println("3rd character type: " + threeRestore.getType());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
