package org.spjain.bds.fundamentals;

public class ControlFlow {
    public static void main(String[] args) {
        int number = -10;

        // If-Else Statement
       /* if (number > 0) {
            System.out.println(number + " is a positive number.");
        } else if ((number < 0) && (number != 0)) {
            System.out.println(number + " is a negative number.");
        } else {
            System.out.println("The number is zero.");
        }*/

        // Switch Statement
       /* int day = 3;
        String dayName;
        switch (day) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
            case 7:
                dayName = "Sunday";
                break;
            default:
                dayName = "Invalid day";
        }
        System.out.println("Day " + day + " is " + dayName + ".");*/

        // For Loop
       /* System.out.println("For Loop:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Iteration " + i);
        }*/

        // While Loop
        System.out.println("While Loop:");
        int count = 1;
        while (count <= 5) {
            System.out.println("Count " + count);
            count++;
        }

        // Do-While Loop
       /* System.out.println("Do-While Loop:");
        int num = 1;
        do {
            System.out.println("Number " + num);
            num++;
        } while (num <= 5);*/

        // Break and Continue
       /* System.out.println("Break and Continue:");
        for (int i = 1; i <= 10; i++) {
            if (i == 6) {
                System.out.println("Breaking the loop at i = " + i);
                break; // Exit the loop when i is 6
            }
            if (i % 2 == 0) {
                System.out.println("Continuing at even i = " + i);
                continue; // Skip even numbers
            }
            System.out.println("Current i = " + i);
        }*/

        // Switch expression (Java 14+)
       /* int month = 4;
        String monthName = switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "Invalid month";
        };
        System.out.println("Month " + month + " is " + monthName + ".");*/

        // Nested Loops
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
