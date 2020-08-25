package string._ds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RabinKarpTest {
	@Test
	void test1() {
		String text = "abacadabrabracabracadabrabrabracad";
		String pattern = "abracadabra";
		RabinKarp rk = new RabinKarp(pattern);
		assertEquals(text.indexOf(pattern), rk.search(text));
	}

	@Test
	void test2() {
		String text = "abacadabrabracabracadabrabrabracad";
		String pattern = "rab";
		RabinKarp rk = new RabinKarp(pattern);
		assertEquals(text.indexOf(pattern), rk.search(text));
	}

	@Test
	void test3() {
		String text = "abacadabrabracabracadabrabrabracad";
		String pattern = "bcara";
		RabinKarp rk = new RabinKarp(pattern);
		assertEquals(text.indexOf(pattern), rk.search(text));
	}

	@Test
	void test4() {
		String text = "abacadabrabracabracadabrabrabracad";
		String pattern = "rabrabracad";
		RabinKarp rk = new RabinKarp(pattern);
		assertEquals(text.indexOf(pattern), rk.search(text));
	}

	@Test
	void test5() {
		String text = "abacadabrabracabracadabrabrabracad";
		String pattern = "abacad";
		RabinKarp rk = new RabinKarp(pattern);
		assertEquals(text.indexOf(pattern), rk.search(text));
	}
}