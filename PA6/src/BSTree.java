/*
 * Name: Kailey Wen and Mia Jerphagnon
 * PID:  A16979798 and A16821297
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Kailey Wen and Mia Jerphagnon
 * @since  November 15, 2023
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            dataList = new LinkedList<T>();
            this.left = left;
            this.right = right;
            this.key = key;
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setLeft(BSTNode newleft) {
            left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setRight(BSTNode newright) {
            right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            return dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        BSTNode root = new BSTNode(null, null, null);
        int nelems;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (findKey(key)) {
            return false;
        }
        if (root == null) {
            root = new BSTNode(null, null, key);
        }
        Integer currKey = (Integer) root.getKey();
        Integer paramKey = (Integer) key;
        if (paramKey > currKey){
            BSTree newTree = new BSTree();
            newTree.root = root.right;
            newTree.insert(key);
        }
        if (paramKey < currKey){
            BSTree newTree = new BSTree();
            newTree.root = root.left;
            newTree.insert(key);
        }
        return false;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            return false;
        }
        Integer currKey = (Integer) root.getKey();
        Integer paramKey = (Integer) key;
        if (Objects.equals(currKey, paramKey)) {
            return true;
        }
        if (paramKey > currKey){
            BSTree newTree = new BSTree();
            newTree.root = root.right;
            newTree.findKey(key);
        }
        if (paramKey < currKey){
            BSTree newTree = new BSTree();
            newTree.root = root.left;
            newTree.findKey(key);
        }
        return false;
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If eaither key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (data == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        Integer currKey = (Integer) root.getKey();
        Integer paramKey = (Integer) key;
        if (currKey == paramKey) {
            root.addNewInfo(data);
        }
        if (paramKey > currKey){
            BSTree newTree = new BSTree();
            newTree.root = root.right;
            newTree.insertData(key, data);
        }
        if (paramKey < currKey){
            BSTree newTree = new BSTree();
            newTree.root = root.left;
            newTree.insertData(key, data);
        }
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        Integer currKey = (Integer) root.getKey();
        Integer paramKey = (Integer) key;
        if (currKey == paramKey) {
            return root.getDataList();
        }
        if (paramKey > currKey){
            BSTree newTree = new BSTree();
            newTree.root = root.right;
            newTree.findDataList(key);
        }
        if (paramKey < currKey){
            BSTree newTree = new BSTree();
            newTree.root = root.left;
            newTree.findDataList(key);
        }
        return null;
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        if (root == null) {
            return -1;
        }
        return findHeightHelper(root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root.getLeft() == null && root.getRight() == null) {
            return 0;
        }
        else if (root.getLeft() == null && root.getRight() != null) {
            return 1 + findHeightHelper(root.getRight());
        }
        else if (root.getLeft() != null && root.getRight() == null) {
            return 1 + findHeightHelper(root.getLeft());
        }
        else if (root.getLeft() != null && root.getRight() != null){
            int leftHeight = findHeightHelper(root.getLeft());
            int rightHeight = findHeightHelper(root.getRight());
            return 1 + Math.max(leftHeight, rightHeight);
        }
        return 0;
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {
        Stack<BSTNode> iterStack;
        BSTNode currNode;
        public BSTree_Iterator() {
            iterStack = new Stack<BSTNode>();
            currNode = getRoot();
            iterStack.push(currNode);
            // push nodes on the left path to stack
            while (currNode.getLeft() != null) {
                iterStack.push(currNode.getLeft());
                currNode = currNode.getLeft();
            }

        }

        public boolean hasNext() {
            return iterStack.isEmpty();
        }

        public T next() {
            BSTNode nextNode = iterStack.pop();
            if (nextNode.getRight() != null) {
                currNode = nextNode.getRight();
                iterStack.push(currNode);
            }
            // push nodes of the left path of the right node to stack
            while (currNode.getLeft() != null) {
                iterStack.push(currNode.getLeft());
                currNode = currNode.getLeft();
            }
            return nextNode.getKey();
        }

    }

    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }

    public T levelMax(int level) {

        return null;
    }
}
