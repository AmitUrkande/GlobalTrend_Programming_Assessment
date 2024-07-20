package com.any.assessment;

public class PalindromeChecker 
{

    public static boolean isPalindrome(String s) 
    {
        StringBuilder normalized = new StringBuilder();
        for (char c : s.toCharArray()) 
        {
            if (Character.isLetterOrDigit(c)) 
            {
                normalized.append(Character.toLowerCase(c));
            }
        }

        int left = 0;
        int right = normalized.length() - 1;
        while (left < right) 
        {
            if (normalized.charAt(left) != normalized.charAt(right)) 
            {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
    public static void main(String[] args)
    {
        String input = "A man, a plan, a canal: Panama";
        boolean isPalindrome = isPalindrome(input);
        System.out.println("Is the input string a palindrome? " + isPalindrome);
    }
}

