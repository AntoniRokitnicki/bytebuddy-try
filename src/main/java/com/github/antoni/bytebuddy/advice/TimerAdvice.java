package com.github.antoni.bytebuddy.advice;

import net.bytebuddy.asm.Advice;

public class TimerAdvice {
    @Advice.OnMethodEnter
    static long enter(@Advice.Origin String method) throws Exception {
        return System.currentTimeMillis();
    }

    @Advice.OnMethodExit
    static void exit(@Advice.Origin String method, @Advice.Enter long start) throws Exception {
        System.out.println(method + " took " + (System.currentTimeMillis() - start) + " milliseconds ");
    }
}
