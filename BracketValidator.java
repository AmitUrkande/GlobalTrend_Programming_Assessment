package com.any.assessment;

import java.util.Stack;

public class BracketValidator 
{
    public static boolean isValid(String s) 
    {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') 
            {
                stack.push(c);
            } else {
                if (stack.isEmpty()) 
                {
                    return false; 
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false; 
                }
            }
        }

        return stack.isEmpty(); 
    }

    public static void main(String[] args) 
    {
        String input1 = "({[]})"; 
        String input2 = "({[})"; 

        System.out.println("Input 1 is valid: " + isValid(input1));
        System.out.println("Input 2 is valid: " + isValid(input2));
    }
}

