package Lesson1.task3;

import java.io.*;
import java.lang.reflect.Field;


public class Serializer {
    public static void serialize(Student student, String fileName){
        Class<?> cls = student.getClass();

        try(PrintWriter pw = new PrintWriter(fileName + ".txt")){
            for(Field field : cls.getDeclaredFields()){
                if(field.isAnnotationPresent(Save.class)){
                    field.setAccessible(true);
                    if(field.getType() == int.class){
                        pw.print(field.getName() + "=" + field.getInt(student) + ";");
                    }
                    if(field.getType() == String.class){
                        pw.print(field.getName() + "=" + (String)field.get(student) + ";");
                    }
                    //can be added other types: char, long,...
                }
            }


        } catch (FileNotFoundException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    public static Student deserialize(String fileName){
        Student student = new Student();
        Class<?> cls = student.getClass();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line = br.readLine();
            String[] values = line.split(";");
            for(String value : values){
                for(Field field : cls.getDeclaredFields()){
                    if(field.getName().equals(value.substring(0, value.indexOf("=")))){
                        field.setAccessible(true);
                        if(field.getType() == String.class) {
                            field.set(student, value.substring(value.indexOf("=") + 1));
                        }
                        if(field.getType() == int.class) {
                            field.setInt(student, Integer.valueOf(value.substring(value.indexOf("=") + 1)));
                        }
                        //can be added other types: char, long,...
                    }
                }
            }

        }catch (IOException | IllegalAccessException e){
            e.printStackTrace();
        }
        return student;
    }
}
