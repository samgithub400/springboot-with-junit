package com.ios.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

  public static void main(String[] args) {

  String input[]={"My","name","is","HahAhaha.."};

   String result= Arrays.toString(input);
    System.out.println(result);

    String newResult = Arrays.stream(input).collect(Collectors.joining());
    System.out.println(newResult);




    String input1="abbcccddddeeeee";
    //a=1
    //b=2
    //c=3
    //d=4
    //e=5






    //System.out.println(collect);


  }





}
