package com.itxie.linkedlist;

import java.util.Stack;

public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("jack1");
        stack.add("jack2");
        stack.add("jack3");

        //出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}
