package ClassesAED1;

import InterfacesAED1.iBSTree;
import InterfacesAED1.iOptimalBST;

public class OptimalBST<T> extends BSTree<T> implements iOptimalBST<T> {

    @Override
    public boolean initialize(Comparable[] Keys, int[] pi, int[] qi, int n) {
        if (!isEmpty()) {
            return false;
        }
        int[][] roots = getRoots(pi, qi, n);
        iBSTree<T> tree = new BSTree();
        asembleTree(roots, Keys, 0, n, tree);
        this.root = tree.getRoot();
        return true;
    }

    private int[][] getRoots(int[] pi, int[] qi, int n) {
        int[][] e = new int[n + 2][n + 1];
        int[][] w = new int[n + 2][n + 1];
        int[][] roots = new int[n][n + 1];

        for (int i = 1; i <= n + 1; i++) {
            e[i][i - 1] = qi[i - 1];
            w[i][i - 1] = qi[i - 1];
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n - k + 1; i++) {
                int j = i + k - 1;
                e[i][j] = Integer.MAX_VALUE;
                w[i][j] = w[i][j - 1] + pi[j] + qi[j];
                for (int r = i; r <= j; r++) {
                    int t = e[i][r - 1] + e[r + 1][j] + w[i][j];
                    if (t < e[i][j]) {
                        e[i][j] = t;
                        roots[i - 1][j] = r;
                    }
                }
            }
        }
        /* 
        printMatrix(e);
        System.out.println("   +   ");
        printMatrix(w);
        System.out.println("   +   ");
        printMatrix(roots);
         */
        return roots;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println("");
        }
    }

    private void asembleTree(int[][] roots, Comparable[] Keys, int i, int j, iBSTree tree) {
        if (i >= j) {
            return;
        }
        int Root = roots[i][j];
        TreeNode<Comparable> node = new TreeNode<>(Keys[Root], Keys[Root]);
        tree.insert(node);
        asembleTree(roots, Keys, i, Root - 1, tree);
        asembleTree(roots, Keys, Root, j, tree);
    }

    @Override
    public int getCost(int[] pi, int[] qi) {
        if (isEmpty()) {
            return 0;
        }
        int[] indices = new int[2];
        return this.root.getCost(pi, qi, indices, 1);
    }
}
