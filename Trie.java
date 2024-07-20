package com.any.assessment;

class TrieNode {
    private final TrieNode[] children;
    private boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26]; // Assuming lowercase letters a-z
        isEndOfWord = false;
    }

    public void insert(String word) {
        TrieNode curr = this;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode curr = this;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return curr.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode curr = this;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return true;
    }
}

public class Trie {
    public static void main(String[] args) {
        TrieNode root = new TrieNode();

        // Example usage
        root.insert("apple");
        root.insert("app");
        System.out.println(root.search("apple")); 
        System.out.println(root.search("app")); 
        System.out.println(root.startsWith("app")); 
        System.out.println(root.search("ap")); 
    }
}

