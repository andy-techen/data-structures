import java.util.*;
import java.io.*;

public class tree_height {
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

	public class TreeHeight {
		int n;
		int parent[];
		int heights[];  // add heights array to store heights and avoid stack overflow
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			heights = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

//        //  Replace this code with a faster implementation
//		int computeHeight() {
//			int maxHeight = 0;
//			for (int vertex = 0; vertex < n; vertex++) {
//				int height = 0;
//				for (int i = vertex; i != -1; i = parent[i]) {
//					height++;
//				}
//				maxHeight = Math.max(maxHeight, height);
//			}
//			return maxHeight;
//		}

		int computeHeight() {
			int maxHeight = 0;
			for (int vertex = 0; vertex < n; vertex++) {
				// if vertex not yet measured, getHeight(vertex)
				if (heights[vertex] == 0) {
					heights[vertex] = getHeight(vertex);
				}
				int height = heights[vertex];
				maxHeight = Math.max(maxHeight, height);
			}

			return maxHeight;
		}

		int getHeight(int i) {
			// if subtree not yet measured, calculate and save its height to the heights array
			if (heights[i] == 0) {
				int parentNode = parent[i];  // save to parentNode
				if (parentNode == -1) {  // if parentNode is the root, save height as 1
					heights[i] = 1;
					return 1;
				}
				// basically, if parentNode isn't the root, add height by 1
				heights[i] = getHeight(parentNode) + 1;
			}
			return heights[i];
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
