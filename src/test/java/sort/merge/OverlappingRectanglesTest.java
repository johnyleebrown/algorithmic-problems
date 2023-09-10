package sort.merge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static reader.IOUtils.getFolderFilesReaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sort.merge.OverlappingRectangles.Solution1;
import sort.merge.OverlappingRectangles.Solution2;

class OverlappingRectanglesTest {

	public static Stream<Arguments> providerData() throws Exception {
		Set<String> exclude = new HashSet<>();
		Set<String> include = new HashSet<>(Arrays.asList("test8.txt"));
		return getFolderFilesReaders(OverlappingRectanglesTest.class).stream()
				.filter(reader -> reader.fileName.endsWith(".txt"))
				.filter(reader -> include.isEmpty() || include.contains(reader.fileName))
				.map(r -> {
					boolean ans = r.nextInt() == 1;
					int n = r.nextInt();
					List<List<Integer>> input = new ArrayList<>();
					for (int i = 0; i < n; i++) {
						int x1 = r.nextInt(), y1 = r.nextInt();
						int x2 = r.nextInt(), y2 = r.nextInt();
						input.add(Arrays.asList(x1, y1, x2, y2));
					}
					return arguments(ans, input);
				});
	}

//	@ParameterizedTest
//	@MethodSource("sort.merge.OverlappingRectanglesTest#providerData")
//	void test(boolean ans, List<List<Integer>> input) {
//		assertEquals(ans, Solution2.overlap(input));
//		assertEquals(ans, Solution1.overlap(input));
//	}
}