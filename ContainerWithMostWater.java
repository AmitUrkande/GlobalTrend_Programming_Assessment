package com.any.assessment;

public class ContainerWithMostWater 
{
	public static int maxArea(int[] height) 
	{
        if (height == null || height.length < 2) 
        {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int containerHeight = Math.min(height[left], height[right]);
            int area = width * containerHeight;
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) 
            {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
    public static void main(String[] args) 
    {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Maximum water that can be contained is: " + maxArea(height));
    }

    
}

