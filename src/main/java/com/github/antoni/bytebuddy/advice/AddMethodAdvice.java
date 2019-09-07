package com.github.antoni.bytebuddy.advice;

import net.bytebuddy.asm.Advice;

public class AddMethodAdvice {
    @Advice.OnMethodExit
    public static void dynamicMethod() throws Exception {
        System.out.println("AddMethodAdvice: new method");
    }
}

