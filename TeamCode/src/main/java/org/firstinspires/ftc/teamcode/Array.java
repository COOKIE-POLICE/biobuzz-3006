package org.firstinspires.ftc.teamcode;

public class Array {
    public static boolean contains(int[] array, int target) {
        for (int number : array) {
            if (number == target) {
                return true;
            }
        }
        return false;
    }
}
