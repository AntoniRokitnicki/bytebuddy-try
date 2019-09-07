package com.github.antoni.bytebuddy.advice;

import net.bytebuddy.asm.Advice;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GetParametersAdvice {
    @Advice.OnMethodExit
    public static void getParametrs(@Advice.Origin String method, @Advice.AllArguments Object[] para) throws Exception {
        System.out.println("GetParametersAdvice: " + method + " has params " + Arrays.stream(para).map(Object::toString).collect(Collectors.joining(",")));
    }
}
