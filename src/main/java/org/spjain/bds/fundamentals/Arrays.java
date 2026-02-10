package org.spjain.bds.fundamentals;

public class Arrays {
    public static void main(String[] args) {
        // Declare and initialize an array of integers
        // Array representation:
        //              __________________________
        // numbers ---> | 10 | 20 | 30 | 40 | 50 |
        //              --------------------------
        //              |  0 |  1 |  2 |  3 |  4 |  (indices)
        //              --------------------------
        // numbers is on stack and points to the array on heap
        int[] numbers = {10, 20, 30, 40, 50};

      /*  // Access and print elements of the array
        System.out.println("Array Elements:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Element at index " + i + ": " + numbers[i]);
        }

        // Modify an element in the array
        numbers[2] = 99;
        System.out.println("\nAfter modifying the element at index 2:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Element at index " + i + ": " + numbers[i]);
        }*/

        // multidimensional array
        /*int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("\nMultidimensional Array Elements:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(matrix[1][1]);*/


        // a function that prints a pascals triangle using arrays
        // printPascalsTriangle(5);

     /*   // a function that creates an array of strings and prints them in reverse order
        String[] words = {"Hello", "World", "This", "Is", "Java"};
        System.out.println("\nStrings in Reverse Order:");
        for (int i = words.length - 1; i >= 0; i--) {
            System.out.println(words[i]);
        }*/

        // create an array of empty strings and fill it with user input
        String [] userInputs = new String[3];
        // what are the values initially in the array
        for (String initial : userInputs) {
            System.out.println("Initial value in a new array : " + initial);
        }
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("\nEnter 3 strings:");
        for (int i = 0; i < userInputs.length; i++) {
            System.out.print("String " + (i + 1) + ": ");
            userInputs[i] = scanner.nextLine();
        }
        System.out.println("\nYou entered:");
        for (String input : userInputs) {
            System.out.println(input);
        }
        scanner.close(); // close the scanner

        // call by reference example with arrays
        modifyArray(userInputs);
        // print the array again to see the changes
        System.out.println("\nArray Elements after modifyArray call:");
        for (String input : userInputs) {
            System.out.println(input);
        }
    }


    private static void printPascalsTriangle(int rows) {
        int[][] triangle = new int[rows][];
        for (int i = 0; i < rows; i++) {
            triangle[i] = new int[i + 1];
            triangle[i][0] = triangle[i][i] = 1; // First and last element of each row is 1
            for (int j = 1; j < i; j++) {
                triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
            }
        }

        System.out.println("\nPascal's Triangle:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows - i - 1; j++) {
                System.out.print(" "); // Print leading spaces
            }
            for (int j = 0; j < triangle[i].length; j++) {
                System.out.print(triangle[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void modifyArray(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].toUpperCase();
        }
        System.out.println("\nModified Array Elements (to Upper Case):");
    }
}
