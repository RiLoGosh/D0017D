public class Main {

    public static void main(String[] args)
    {
        int a = 4, b = 4;
        a = metod(a, b);
        System.out.println("a = " + a + " och b = " + b);

    }

    public static int metod(int a, int b)
    {
        a = a + b;
        return b;
    }


}
