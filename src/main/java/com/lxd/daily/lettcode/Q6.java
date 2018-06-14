package com.lxd.daily.lettcode;

/**
 * Created by liaoxudong
 * Date:2018/6/5
 */

public class Q6 {

    static class Trie{
        private String value;
        private Trie left;
        private Trie right;
        private boolean insertLeft = true;

        /** Initialize your data structure here. */
        public Trie() {

        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if(this.value == null){
                this.value = word;
            }else{
                Trie node =  this;
                if(insertLeft) {
                    while (node.left != null) {
                        node = node.left;
                    }
                    node.left = new Trie();
                    node.left.insert(word);
                    insertLeft = false;
                }else {
                    while (node.right != null) {
                        node = node.right;
                    }
                    node.right = new Trie();
                    node.right.insert(word);
                    insertLeft = true;
                }
            }
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Trie node = this;
            if(word.equals(node.value)){// 检查根节点，如果符合条件直接返回
                return true;
            }
            // 否则遍历树查找
            boolean result = false;
            if(node.left != null){
                result = node.left.search(word);
                if(result){
                    return result;
                }
            }
            if(node.right != null){
                result = node.right.search(word);
                if(result){
                    return result;
                }
            }
            return result;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Trie node = this;
            if(node.value != null && node.value.startsWith(prefix)){// 检查根节点，如果符合条件直接返回
                return true;
            }
            // 否则遍历树查找
            boolean result = false;
            if(node.left != null){
                result = node.left.startsWith(prefix);
                if(result){
                    return result;
                }
            }
            if(node.right != null){
                result = node.right.startsWith(prefix);
                if(result){
                    return result;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("ab");
        System.out.println(trie.search("abc"));
        System.out.println(trie.search("ab"));
        System.out.println(trie.startsWith("abc"));
        System.out.println(trie.startsWith("ab"));
        trie.insert("ab");
        System.out.println(trie.search("abc"));
        System.out.println(trie.startsWith("abc"));
        trie.insert("abc");
        System.out.println(trie.search("abc"));
        System.out.println(trie.startsWith("abc"));
    }
}
