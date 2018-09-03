package Lesson1.task2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MySaver {
    private TextContainer textContainer;

    public MySaver(TextContainer textContainer) {
        this.textContainer = textContainer;
    }

    public MySaver() {
    }

    public void Save(){
        Class<?> cls = textContainer.getClass();
        String path = cls.getAnnotation(SaveTo.class).path();
        for(Method mtd : cls.getDeclaredMethods()){
            if(mtd.isAnnotationPresent(Saver.class)){
                try {
                    mtd.invoke(textContainer, path);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
