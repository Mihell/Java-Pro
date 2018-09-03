package Lesson1.task1;

public class Tester {

    @Test(a = 2, b = 7)
    public static void test(int a, int b) {
        System.out.println(a + b);
    }

    public static void test2(int a, int b) {
        System.out.println(a / b);
    }
}
