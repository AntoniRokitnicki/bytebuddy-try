package com.github.antoni.bytebuddy;

import com.github.antoni.bytebuddy.advice.AddMethodAdvice;
import com.github.antoni.bytebuddy.advice.GetParametersAdvice;
import com.github.antoni.bytebuddy.advice.SetFieldAdvice;
import com.github.antoni.bytebuddy.advice.TimerAdvice;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String arguments, Instrumentation instrumentation) {
        System.out.printf("Agent %s executed%n", Agent.class.getName());
        new AgentBuilder.Default().with(new AgentBuilder.InitializationStrategy.SelfInjection.Eager())
                .type((ElementMatchers.any()))
                .transform((builder, typeDescription, classLoader, module) -> builder.method(ElementMatchers.any())
                        .intercept(Advice.to(TimerAdvice.class)))
                .transform((builder, type, classLoader, module) -> builder.method(ElementMatchers.nameContains("method4"))
                        .intercept(Advice.to(GetParametersAdvice.class)))
                .transform((builder, type, classLoader, module) -> builder.defineField("word", String.class)
                        .constructor(ElementMatchers.any()).intercept(Advice.to(SetFieldAdvice.class)))

                .transform((builder, typeDescription, classLoader, module) -> builder.defineMethod("method0", void.class, Visibility.PUBLIC)
                        .intercept(MethodDelegation.to(AddMethodAdvice.class)))

                //.with(AgentBuilder.Listener.StreamWriting.toSystemError())
                        .installOn(instrumentation);


    }
}
