package SmallExamples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadAFile {

    public static void main(String[] args) {

        try {
            File myFile = new File("MyText.txt"); //создаем объект класса File представляющий файл
            FileReader fileReader = new FileReader(myFile); //подключаем его к потоку для соединения FileReader

            BufferedReader reader = new BufferedReader(fileReader); //Создаем буфер и подключаем его к потоку для соединения

            String line = null;

            while ((line=reader.readLine()) != null) { //чтение происходит в цикле while
                System.out.println(line);
            }


        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
