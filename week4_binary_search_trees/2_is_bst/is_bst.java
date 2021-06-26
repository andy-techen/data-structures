import java.util.*;
import java.io.*;

public class is_bst {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean solve() {
            if (nodes == 0) {
                return true;
            }
            return isBST(tree[0], Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private boolean isBST(Node root, int min, int max) {
            // Implement correct algorithm here
            if (root.key < min || root.key > max) {
                return false;
            }
            if (root.left == -1 && root.right == -1) {
                return true;
            }
            if (root.left == -1) {
                return isBST(tree[root.right], root.key + 1, max);
            }
            if (root.right == -1) {
                return isBST(tree[root.left], min, root.key - 1);
            }
            return (isBST(tree[root.left], min, root.key - 1) &&
                    isBST(tree[root.right], root.key + 1, max));
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.solve()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
