/**
 * BST Worksheet
 */

import java.util.*;

public class BSTManual {

/**
 * BST Worksheet
 * @author Kailey Wen
 * @since  November 13, 2023
 */

// No style for this file.	

public static ArrayList<String>  insertElements() {

	ArrayList<String> answer_pr1 = new ArrayList<>(11);

	/*
	 * make sure you add your answers in the following format:
	 * 
	 * answer_pr1.add("1"); --> level 1 (root level) of the output BST
	 * answer_pr1.add("2, X"); --> level 2 of the output BST
	 * answer_pr1.add("3, X, X, X"); --> level 3 of the output BST 
	 * 
	 * the above example is the same as the second pictoral example on your
	 * worksheet
	 */

	answer_pr1.add("100");
	answer_pr1.add("10, 101");
	answer_pr1.add("X, 50, X, 160");
	answer_pr1.add("X, X, 40, X, X, X, 125, X");
	answer_pr1.add("X, X, X, X, 24, 45, X, X, X, X, X, X, X, X, X, X");
	return answer_pr1;

}

public static ArrayList<String>  deleteElements() {

	ArrayList<String> answer_pr2 = new ArrayList<>(11);
	
	/*
	 * Please refer to the example in insertElements() if you lose track
	 * of how to properly enter your answers
	 */

	answer_pr2.add("54");
	answer_pr2.add("40, 75");
	answer_pr2.add("30, X, X, 90");
	answer_pr2.add("X, 35, X, X, X, X, X, X");
	return answer_pr2;

}

public static ArrayList<String>  traversals() {

	ArrayList<String> answer_pr3 = new ArrayList<>(11);
	
	/*
	 * In this one, you will add ONLY and EXACTLY 3 strings to answer_pr3
	 * 
	 * here's how you do it:
	 * 
	 * answer_pr3.add("1, 2, 3, 4, 5") --> in-order traversal result
	 * answer_pr3.add("1, 2, 3, 4, 5") --> pre-order traversal result
	 * answer_pr3.add("1, 2, 3, 4, 5") --> post-order traversal result
	 * 
	 * replace "1, 2, 3, 4, 5" with your actual answers
	 */
	answer_pr3.add("13, 26, 28, 41, 45, 48, 49, 52, 55, 56, 71, 77");
	answer_pr3.add("41, 26, 13, 28, 52, 48, 45, 49, 56, 55, 77, 71");
	answer_pr3.add("13, 28, 26, 45, 49, 48, 55, 71, 77, 56, 52, 41");
	return answer_pr3;

}


}