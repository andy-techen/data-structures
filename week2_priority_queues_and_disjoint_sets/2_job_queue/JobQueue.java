import java.io.*;
import java.util.*;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
//        long[] nextFreeTime = new long[numWorkers];
//        for (int i = 0; i < jobs.length; i++) {
//            int duration = jobs[i];
//            int bestWorker = 0;
//            for (int j = 0; j < numWorkers; ++j) {
//                if (nextFreeTime[j] < nextFreeTime[bestWorker])
//                    bestWorker = j;
//            }
//            assignedWorker[i] = bestWorker;
//            startTime[i] = nextFreeTime[bestWorker];
//            nextFreeTime[bestWorker] += duration;
//        }
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        PriorityQueue<Worker> queue = new PriorityQueue<Worker>(numWorkers, new WorkerComparator());

        // add workers into queue
        for (int i = 0; i < numWorkers; i++) {
            queue.add(new Worker(i));
        }
        for (int i = 0; i < jobs.length; i++) {
            Worker freeThd = queue.poll();
            assignedWorker[i] = freeThd.id;
            startTime[i] = freeThd.nextFreeTime;
            freeThd.nextFreeTime += jobs[i];
            queue.add(freeThd);
        }
    }

    public class Worker {
        int id;
        long nextFreeTime;
        public Worker(int id) {
            this.id = id;
            this.nextFreeTime = 0;
        }
    }

    // specify ordering behavior
    class WorkerComparator implements Comparator<Worker> {
        @Override
        public int compare(Worker w1, Worker w2) {
            if (w1.nextFreeTime == w2.nextFreeTime) {
                return w1.id > w2.id ? 1 : -1;
            }
            else if (w1.nextFreeTime != w2.nextFreeTime) {
                return w1.nextFreeTime > w2.nextFreeTime ? 1 : -1;
            }
            else {
                return 0;
            }
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
