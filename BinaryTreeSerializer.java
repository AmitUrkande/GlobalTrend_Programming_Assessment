package com.any.assessment;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode 
{
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val)
    {
        this.val = val;
    }
}

public class BinaryTreeSerializer 
{
    private static final String DELIMITER = ",";

    public String serialize(TreeNode root) 
    {
        if (root == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) 
    {
        if (node == null) 
        {
            sb.append("null").append(DELIMITER);
        } else {
            sb.append(node.val).append(DELIMITER);
            serializeHelper(node.left, sb);
            serializeHelper(node.right, sb);
        }
    }

    public TreeNode deserialize(String data) 
    {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) 
    {
        String val = queue.poll();
        if (val.equals("null")) 
        {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);
        return node;
    }

    public static void main(String[] args) 
    {
        BinaryTreeSerializer serializer = new BinaryTreeSerializer();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String serialized = serializer.serialize(root);
        System.out.println("Serialized tree: " + serialized);

        TreeNode deserializedRoot = serializer.deserialize(serialized);
    }
}
