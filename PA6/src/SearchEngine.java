/*
 * Name: Kailey Wen and Mia Jerphagnon
 * PID:  A16979798 and A16821297
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 * 
 * @author Kailey Wen and Mia Jerphagnon
 * @since  November 19, 2023
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                for (String castMember: cast) {
                    castMember = castMember.toLowerCase();

                    movieTree.insert(castMember);
                    if (!movieTree.findDataList(castMember).contains(movie)) {
                        movieTree.insertData(castMember, movie);
                    }

                    ratingTree.insert(castMember);
                    if (!ratingTree.findDataList(castMember).contains(rating)) {
                        ratingTree.insertData(castMember, rating);
                    }
                }

                for (String studioName : studios) {
                    studioName = studioName.toLowerCase();

                    studioTree.insert(studioName);
                    if (!studioTree.findDataList(studioName).contains(movie)) {
                        studioTree.insertData(studioName, movie);
                    }
                }
                // populate three trees with the information you just read
                // hint: create a helper function and reuse it to build all three trees

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        LinkedList<String> documents;
        // process query
        String[] keys = query.toLowerCase().split(" ");

        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful
        if (keys.length == 1) {
            documents = searchTree.findDataList(query);
            print(query, documents);
        }
        LinkedList<String> intersectionResults = new LinkedList<>(searchTree.findDataList(keys[0]));
        for (int i = 1; i < keys.length; i++) {
            intersectionResults.retainAll(searchTree.findDataList(keys[i]));
        }
        print(query, intersectionResults);
        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful
        for (String key: keys) {
            documents = searchTree.findDataList(key);
            print(key, documents);
        }
    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        BSTree<String> movieTree = new BSTree<>();
        BSTree<String> studioTree = new BSTree<>();
        BSTree<String> ratingTree = new BSTree<>();
        BSTree<String>[] searchTrees = new BSTree[]{movieTree, studioTree, ratingTree};
        // initialize search trees

        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);

        // populate search trees
        populateSearchTrees(movieTree, studioTree, ratingTree, fileName);
        //populateSearchTrees();
        // choose the right tree to query
        StringBuilder queryBuilder = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            queryBuilder.append(args[i]).append(" ");
        }
        String query = queryBuilder.toString().trim();
        if (searchKind >= 0 && searchKind < searchTrees.length) {
            searchMyQuery(searchTrees[searchKind], query);
        }
    }
}
