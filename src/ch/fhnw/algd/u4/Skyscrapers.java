package ch.fhnw.algd.u4;

import java.util.Random;

public class Skyscrapers {
    final static Random R = new Random();

    public static void main(String[] args) {
        double start = System.currentTimeMillis();
        int[][] block = generateRandomBlock();
        int[][] vis = calculateVisibilities(block);
        System.out.println(System.currentTimeMillis() - start + "ms");
        printBlock(block);
        printBlock(vis);
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    /**
     * Generiert einen quadratischen Block der Grösse {@link size} nach dem Schema: 1 2 3 4 2 3 4 1
     * 3 4 1 2 4 1 2 3
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

    public static int[][] generateRandomBlock() {
        int size = R.nextInt(500);
        int[][] block = generateBasicBlock(size);
        block = shuffleBlock(block);
        return block;
    }

    public static int[][] shuffleBlock(int[][] block) {
        int size = block.length;
        int shuffles = size * 2;
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

    /**
     * Counts the amount of visible skyscrapers in each row, column from each side.
     * 
     * @param block
     * @return
     */
    public static int[][] calculateVisibilities(int[][] block) {
        int[][] vis = new int[block.length + 2][block.length + 2];
        // calculate east - west visibilities
        for (int i = 0; i < block.length; i++) {
            int visW = 0;
            int maxW = 0;
            int visE = 0;
            int maxE = 0;
            for (int j = 0; j < block.length; j++) {
                if (block[i][j] > maxW) {
                    visW++;
                    maxW = block[i][j];
                }
                if (block[i][block.length - j - 1] > maxE) {
                    visE++;
                    maxE = block[i][block.length - j - 1];
                }
            }
            vis[i + 1][block.length + 1] = visE;
            vis[i + 1][0] = visW;
        }
        // calculate north - south visibilities
        for (int i = 0; i < block.length; i++) {
            int visN = 0;
            int maxN = 0;
            int visS = 0;
            int maxS = 0;
            for (int j = 0; j < block.length; j++) {
                if (block[j][i] > maxN) {
                    visN++;
                    maxN = block[j][i];
                }
                if (block[block.length - j - 1][i] > maxS) {
                    visS++;
                    maxS = block[block.length - j - 1][i];
                }
            }
            vis[0][i + 1] = visN;
            vis[block.length + 1][i + 1] = visS;
        }
        return vis;
    }
}
