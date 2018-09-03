package Lesson1.task2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

@SaveTo(path = "file.txt")
public class TextContainer {
    private String str;

    public TextContainer(String str) {
        this.str = str;
    }

    public TextContainer() {
    }

    @Saver
    public void save(String filePath){

        try(PrintWriter pw = new PrintWriter(filePath)){
            pw.println(str);

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
