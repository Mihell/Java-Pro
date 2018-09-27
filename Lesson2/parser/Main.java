package parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();
        String result = "";
        File file = new File("json.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = "";
            while((line = br.readLine()) != null){
                result += line;
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        Person person = (Person)gson.fromJson(result, Person.class);
        System.out.println(person);
    }
}
