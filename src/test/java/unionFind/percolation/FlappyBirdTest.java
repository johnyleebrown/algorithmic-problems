package unionFind.percolation;

import org.junit.jupiter.api.Test;
import reader.InputReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static reader.IOUtils.getFolderFilesReaders;

class FlappyBirdTest {
	@Test
	void allTests() throws Exception {
		List<InputReader> inputReaders = getFolderFilesReaders(getClass());
		for (InputReader r : inputReaders) {
			boolean ans = r.nextInt() == 1;
			int n = r.nextInt();
			int m = r.nextInt();
			int[][] input = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					input[i][j] = r.nextInt();
				}
			}

			FlappyBird.Solution s = new FlappyBird.Solution();
			assertEquals(ans, s.canReachEnd(input));
		}
	}
}