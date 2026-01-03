package org.spjain.bds.oop;

public class EnumDemo {
    public static void main(String [] args) {
        Day today = Day.WEDNESDAY;
        System.out.println("Today is: " + today);

        switch (today) {
            case MONDAY:
                System.out.println("Start of the work week.");
                break;
            case WEDNESDAY:
                System.out.println("Midweek day.");
                break;
            case FRIDAY:
                System.out.println("End of the work week.");
                break;
            default:
                System.out.println("Just another day.");
        }

        Day someDay = Day.valueOf("FRIDAY");
        System.out.println("Some day is: " + someDay);
    }
}
