package com.github.antoni.bytebuddy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void method3() throws Exception {
        System.out.println("This is new method : method 3");
    }

    public static void method4(int number, String name, boolean bool) {
        System.out.println("number = [" + number + "], name = [" + name + "], bool = [" + bool + "]");
    }



    public static void localVariables() throws Exception {
        int i = 1;
        String s = "zzz";
        System.out.println("s = " + s);
        https://stackoverflow.com/questions/44684442/byte-buddy-stack-manipulation-how-to-work-with-local-variables-and-if-statement
        System.out.println("i = " + i);
    }

    public static void main(String[] args) throws Exception {
        method4(1,"Ludwik", true);
        System.out.println("methods="+Arrays.stream(Main.class.getDeclaredMethods()).map(Method::getName).collect(Collectors.joining(",")));
        System.out.println("fields="+Arrays.stream(Main.class.getDeclaredFields()).map(Field::getName).collect(Collectors.joining(",")));

        Main obj = new Main();
        System.out.println("fields-values="+Arrays.stream(Main.class.getDeclaredFields()).map(f-> {
            try {
                return f.get(obj).toString();
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }).collect(Collectors.joining(",")));
        System.out.println("method0 = " + Main.class.getMethod("method0").invoke(obj));
    }

}
