package Lesson1.task3;

public class Student {
    @Save
    private String name;

    @Save
    private int age;

    private int group;

    public Student(String name, int age, int group) {
        this.name = name;
        this.age = age;
        this.group = group;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", group=" + group +
                '}';
    }
}
