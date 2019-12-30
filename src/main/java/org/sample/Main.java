package org.sample;

public class Main {

    public static void main(String[] args) throws Exception
    {
        doRegular();
        doReflection();
    }

    public static void doRegular() throws Exception
    {
        long start = System.currentTimeMillis();
        for (int i=0; i<1000000; i++)
        {
            String a = new String("test");
            a.length();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void doReflection() throws Exception
    {
        long start = System.currentTimeMillis();
        for (int i=0; i<1000000; i++)
        {
            String a = (String) Class.forName("java.lang.String").newInstance();
            a.length();
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}