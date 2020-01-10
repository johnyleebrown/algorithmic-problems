import java.util.concurrent.Semaphore;

public class PrintInOrder
{
	class Solution
	{
		private final Semaphore semaphoreSecond = new Semaphore(1);
		private final Semaphore semaphoreThird = new Semaphore(1);

		public Solution()
		{
			try
			{
				semaphoreSecond.acquire();
				semaphoreThird.acquire();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		public void first(Runnable printFirst) throws InterruptedException
		{
			printFirst.run();
			semaphoreSecond.release();
		}

		public void second(Runnable printSecond) throws InterruptedException
		{
			semaphoreSecond.acquire();
			printSecond.run();
			semaphoreThird.release();
		}

		public void third(Runnable printThird) throws InterruptedException
		{
			semaphoreThird.acquire();
			printThird.run();
		}
	}
}
