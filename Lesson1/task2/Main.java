package Lesson1.task2;


/*Написать класс TextContainer, который содержит в себе строку. С помощью механизма аннотаций указать
1) в какой файл должен сохраниться текст
2) метод, который выполнит сохранение.
Написать класс Saver, который сохранит поле класса TextContainer в указанный файл.*/

public class Main {
    public static void main(String[] args) {
        MySaver mySaver = new MySaver(new TextContainer("Java Annotations second task!"));
        mySaver.Save();
    }
}
