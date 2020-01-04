import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class PrintZeroEvenOdd
{
	/**
	 * Semaphores.
	 */
	class Solution1
	{
		private int n;
		private Semaphore canPrintZero = new Semaphore(1);
		private Semaphore canPrintEven = new Semaphore(1);
		private Semaphore canPrintOdd = new Semaphore(1);

		public Solution1(int n)
		{
			this.n = n;
			try
			{
				canPrintEven.acquire();
				canPrintOdd.acquire();
			}
			catch (Exception e)
			{
			}
		}

		public void zero(IntConsumer printNumber) throws InterruptedException
		{
			for (int i = 1; i <= n; i++)
			{
				canPrintZero.acquire();
				printNumber.accept(0);
				if (i % 2 != 0)
				{
					canPrintOdd.release();
				}
				else
				{
					canPrintEven.release();
				}
			}
		}

		public void even(IntConsumer printNumber) throws InterruptedException
		{
			for (int i = 2; i <= n; i += 2)
			{
				canPrintEven.acquire();
				printNumber.accept(i);
				// after each digit get back to zero
				canPrintZero.release();
			}
		}

		public void odd(IntConsumer printNumber) throws InterruptedException
		{
			for (int i = 1; i <= n; i += 2)
			{
				canPrintOdd.acquire();
				printNumber.accept(i);
				// after each digit get back to zero
				canPrintZero.release();
			}
		}
	}

	/**
	 * Synchronized methods.
	 */
	class Solution2
	{
		private int n;
		private int i = 1;
		private boolean timeToPrintZero = true;

		public Solution2(int n)
		{
			this.n = n;
		}

		public synchronized void zero(IntConsumer printNumber) throws InterruptedException
		{
			while (i <= n)
			{
				while (!timeToPrintZero)
				{
					this.wait();
				}

				if (i <= n)
				{
					printNumber.accept(0);
				}

				timeToPrintZero = false;
				this.notifyAll();
			}
		}

		public synchronized void even(IntConsumer printNumber) throws InterruptedException
		{
			while (i <= n)
			{
				while (timeToPrintZero || i % 2 != 0)
				{
					this.wait();
				}

				if (i > n)
				{
					return;
				}

				printNumber.accept(i);
				timeToPrintZero = true;
				i++;
				this.notifyAll();
			}
		}

		public synchronized void odd(IntConsumer printNumber) throws InterruptedException
		{
			while (i <= n)
			{
				while (timeToPrintZero || i % 2 == 0)
				{
					this.wait();
				}

				if (i > n)
				{
					return;
				}

				printNumber.accept(i);

				// can print zero
				timeToPrintZero = true;

				// increase not in zero print so we could exit loop after last print
				i++;

				this.notifyAll();
			}
		}
	}
}
