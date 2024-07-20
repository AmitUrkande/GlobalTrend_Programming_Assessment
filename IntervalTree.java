package com.any.assessment;
import java.util.ArrayList;
import java.util.List;

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}

class IntervalNode {
    Interval interval;
    int max;
    IntervalNode left;
    IntervalNode right;

    IntervalNode(Interval interval) {
        this.interval = interval;
        this.max = interval.end;
    }
}

public class IntervalTree 
{
    private IntervalNode root;

    public void insertInterval(int start, int end) 
    {
        Interval newInterval = new Interval(start, end);
        root = insert(root, newInterval);
    }

    private IntervalNode insert(IntervalNode node, Interval interval) 
    {
        if (node == null) 
        {
            return new IntervalNode(interval);
        }

        if (interval.start < node.interval.start) 
        {
            node.left = insert(node.left, interval);
        } 
        else 
        {
            node.right = insert(node.right, interval);
        }

        node.max = Math.max(node.max, interval.end);
        return node;
    }

    public void deleteInterval(int start, int end) 
    {
        Interval intervalToDelete = new Interval(start, end);
        root = delete(root, intervalToDelete);
    }

    private IntervalNode delete(IntervalNode node, Interval interval) 
    {
        if (node == null) 
        {
            return null;
        }

        if (interval.start < node.interval.start) 
        {
            node.left = delete(node.left, interval);
        } 
        else if (interval.start > node.interval.start || interval.end != node.interval.end)
        {
            node.right = delete(node.right, interval);
        } 
        else 
        {
            if (node.left == null) 
            {
                return node.right;
            } 
            else if (node.right == null) 
            {
                return node.left;
            }

            IntervalNode minNode = findMin(node.right);
            node.interval = minNode.interval;
            node.right = delete(node.right, minNode.interval);
        }

        node.max = Math.max(node.interval.end, Math.max(maxEnd(node.left), maxEnd(node.right)));
        return node;
    }

    private IntervalNode findMin(IntervalNode node) 
    {
        while (node.left != null) 
        {
            node = node.left;
        }
        return node;
    }

    private int maxEnd(IntervalNode node) 
    {
        return (node == null) ? Integer.MIN_VALUE : node.max;
    }

    public List<Interval> findOverlappingIntervals(int start, int end) 
    {
        Interval queryInterval = new Interval(start, end);
        List<Interval> result = new ArrayList<>();
        findOverlappingIntervals(root, queryInterval, result);
        return result;
    }

    private void findOverlappingIntervals(IntervalNode node, Interval query, List<Interval> result) 
    {
        if (node == null) 
        {
            return;
        }

        if (isOverlapping(node.interval, query)) 
        {
            result.add(node.interval);
        }

        if (node.left != null && node.left.max >= query.start) 
        {
            findOverlappingIntervals(node.left, query, result);
        }

        findOverlappingIntervals(node.right, query, result);
    }

    private boolean isOverlapping(Interval i1, Interval i2) 
    {
        return i1.start <= i2.end && i2.start <= i1.end;
    }

    public static void main(String[] args) 
    {
        IntervalTree tree = new IntervalTree();

        tree.insertInterval(15, 20);
        tree.insertInterval(10, 30);
        tree.insertInterval(17, 19);
        tree.insertInterval(5, 20);
        tree.insertInterval(12, 15);
        tree.insertInterval(30, 40);

        System.out.println("Overlapping intervals with [14, 16]: " + tree.findOverlappingIntervals(14, 16));
        System.out.println("Overlapping intervals with [21, 23]: " + tree.findOverlappingIntervals(21, 23));

        tree.deleteInterval(10, 30);
        System.out.println("Overlapping intervals with [14, 16] after deleting [10, 30]: " + tree.findOverlappingIntervals(14, 16));
    }
}

