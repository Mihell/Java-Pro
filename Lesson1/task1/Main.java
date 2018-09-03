package Lesson1.task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* Создать аннотацию, которая принимает параметры для метода. Написать код, который вызовет метод,
  помеченный этой аннотацией, и передаст параметры аннотации в вызываемый метод.*/
public class Main {

    public static void main(String[] args) {
        Class<?> cl = Tester.class;
        for (Method m : cl.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)){
                Test an = m.getAnnotation(Test.class);
                int paramOne = an.a();
                int paramTwo = an.b();
                try {
                    m.invoke(null, paramOne, paramTwo);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}