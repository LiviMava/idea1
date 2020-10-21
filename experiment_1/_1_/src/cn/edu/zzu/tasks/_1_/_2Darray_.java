package cn.edu.zzu.tasks._1_;

import java.util.Random;

public class _2Darray_ {
    public static void main(String[] args) {
        Random random = new Random();
        int rand = 0;
        int[][] arrays = new int [3][4];
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[0].length; j++) {
                rand = random.nextInt(100);
                arrays[i][j] = rand;
            }
        }
        for (int[] a : arrays) {
            for (int b : a) {
                System.out.println(b+" ");
            }
        }
    }
}
