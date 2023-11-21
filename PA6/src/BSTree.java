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
     * @throws NullPointerException if key is null
     */
    public boolean insert(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (findKey(key)) {
            return false;
        }
        root = insertHelper(root, key);
        nelems++;
        return true;
    }
    //helper method for insert
    private BSTNode insertHelper(BSTNode node, T key) {
        if (node == null) {
            return new BSTNode(null, null, key);
        }
        //compare key with current node
        int compare = key.compareTo(node.getKey());
        //if compare is negative, go to the left
        if (compare < 0) {
            node.setLeft(insertHelper(node.getLeft(), key));
        }
        //if compare is positive, go to the right
        else if (compare > 0) {
            node.setRight(insertHelper(node.getRight(), key));
        }
        return node;
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
        return findKeyHelper(root, key);
    }
    private boolean findKeyHelper(BSTNode node, T key) {
        if (node == null) {
            return false;
        }
        int compare = key.compareTo(node.getKey());
        if (compare == 0) {
            return true;
        }
        else if (compare < 0) {
            return findKeyHelper(node.getLeft(), key);
        }
        else {
            return findKeyHelper(node.getRight(), key);
        }
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
        insertDataHelper(root, key, data);
    }
    //insert data helper method
    private void insertDataHelper(BSTNode node, T key, T data) {
        //as long as the node isn't null, continue with the recursion
        if (node != null) {
            int compare = key.compareTo(node.getKey());
            //if compare is zero, add the data to the LinkedList of the currnode
            if (compare == 0) {
                node.addNewInfo(data);
            }
            // if compare is negative, go to the left
            else if (compare < 0) {
                insertDataHelper(node.getLeft(), key, data);
            }
            //if compare is positive, go to the right
            else {
                insertDataHelper(node.getRight(), key, data);
            }
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
        return findDataListHelper(root, key);
    }
    //findDataList helper method
    private LinkedList<T> findDataListHelper(BSTNode node, T key) {
        if (node != null) {
            int compare = key.compareTo(node.getKey());
            // if compare is zero, return the LinkedList of currnode
            if (compare == 0) {
                return node.getDataList();
            }
            // if compare is negative, go left
            if (compare < 0) {
                return findDataListHelper(node.getLeft(), key);
            }
            // if compare is positive, go right
            else {
                return findDataListHelper(node.getRight(), key);
            }
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
        //checks to see if the node is a leaf
        if (root.getLeft() == null && root.getRight() == null) {
            return 0;
        }
        //checks to see if there is a right node, but not left
        else if (root.getLeft() == null && root.getRight() != null) {
            return 1 + findHeightHelper(root.getRight());
        }
        //checks to see if there is a left node, but not right
        else if (root.getLeft() != null && root.getRight() == null) {
            return 1 + findHeightHelper(root.getLeft());
        }
        //checks to see if there is a right node and left node
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
