package Lesson1.task3;


/*Написать код, который сериализирует и десериализирует в/из файла все значения полей класса, которые
 отмечены аннотацией @Save.*/

public class Main {
    public static void main(String[] args) {
        Serializer.serialize(new Student("Mike", 33, 3), "Mike");
        Student student = Serializer.deserialize("Mike.txt");
        System.out.println(student);
    }
}
