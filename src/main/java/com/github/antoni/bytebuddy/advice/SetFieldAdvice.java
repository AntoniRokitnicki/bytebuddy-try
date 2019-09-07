package com.github.antoni.bytebuddy.advice;

import net.bytebuddy.asm.Advice;

import java.util.UUID;

public class SetFieldAdvice {
    @Advice.OnMethodExit
    public static void exit(
            @Advice.FieldValue(value = "word", readOnly = false) String word) throws Exception {
        System.out.println("SetFieldAdvice: Setting field value");
        word = UUID.randomUUID().toString();
    }
}
