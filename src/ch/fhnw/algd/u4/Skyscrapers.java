package ch.fhnw.algd.u4;

import java.util.Random;

public class Skyscrapers {
    final static Random R = new Random();
   
    public static void main(String[] args) {
        int[][] block = generateRandomBlock();
        printBlock(block);
    }
    
    /**
     * Generiert einen quadratischen Block der Grösse {@link size} nach dem Schema: 
     * 1 2 3 4
     * 2 3 4 1
     * 3 4 1 2
     * 4 1 2 3
     * 
     * @param size
     * @return block
     */
    public static int[][] generateBasicBlock(int size) {
        int[][] block = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                block[x][y] = ((x + y) % size) + 1;
            }
        }
        return block;
    }
    
    /**
     * Counts the amount of visible skyscrapers in each row, column from each side
     * read matrix as:
     * North array[0] left to right
     * East array[1] top to bottom
     * South array[2] left to right
     * West array[3] top to bottom
     * 
     * @param block
     * @return
     */
    public static int[][] calculateVisibilities(int[][] block) {
        int[][] vis = new int[4][block.length];
//        for 
    }

    public static int[][] generateRandomBlock() {
        int size = R.nextInt(500);
        int[][] block = generateBasicBlock(size);
        block = shuffleBlock(block, 20000);
        return block;
    }
    
    public static int[][] shuffleBlock(int[][] block, int shuffles) {
        int size = block.length;
        for (int i = 0; i < shuffles; i++) {
            // Shuffle rows
            int row1 = R.nextInt(size);
            int row2 = R.nextInt(size);
            int[] buff = block[row1];
            block[row1] = block[row2];
            block[row2] = buff;
            // Shuffle columns
            int col1 = R.nextInt(size);
            int col2 = R.nextInt(size);
            int buf;
            for (int j = 0; j < size; j++) {
                buf = block[j][col1];
                block[j][col1] = block[j][col2];
                block[j][col2] = buf;
            }
        }        
        return block;
    }
    
    
    public static void printBlock(int[][] block) {
        System.out.println("----------------------");
        for (int x = 0; x < block.length; x++) {
            for (int y = 0; y < block[0].length; y++) {
                System.out.print(block[x][y] + "\t");
            }
            System.out.print("\n");
        }
        System.out.println("----------------------");
    }
}
