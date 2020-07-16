public class PrintFooBarAlternately {
    /**
     * Synchronized code blocks.
     */
    class Solution1 {
        private int n;
        private boolean flag;

        public Solution1(int n) {
            this.n = n;
            flag = true;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                synchronized (this) {
                    while (!flag) {
                        this.wait();
                    }
                    printFoo.run();
                    flag = false;
                    this.notify();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                synchronized (this) {
                    while (flag) {
                        this.wait();
                    }
                    printBar.run();
                    flag = true;
                    this.notify();
                }
            }
        }
    }

    /**
     * Synchronized methods.
     */
    class Solution2 {
        private int n;
        private boolean foo, bar;

        public Solution2(int n) {
            this.n = n;
            foo = true;
            bar = false;
        }

        public synchronized void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (!foo) {
                    this.wait();
                }
                printFoo.run();
                foo = false;
                bar = true;
                this.notifyAll();
            }
        }

        public synchronized void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (!bar) {
                    this.wait();
                }
                printBar.run();
                bar = false;
                foo = true;
                this.notifyAll();
            }
        }
    }
}
