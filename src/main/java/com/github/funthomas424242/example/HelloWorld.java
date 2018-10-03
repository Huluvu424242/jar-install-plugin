package com.github.funthomas424242.example;

import com.google.common.math.LongMath;

public class HelloWorld {

    public static void main(String[] args){

        final long result = LongMath.saturatedAdd(40L,2L);
        System.out.println("I am working with com.google.math.LongMath from MarriottMoment.jar and the result of 40L + 2L is: "+result);
    }

}
