package au.id.bjf.sudoku.dlx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Load the 'hardest' database and run it through the solver and get some
 * timings.
 */
final class SudokuHardestDatabaseTest {

	public static final String HARDEST_DB_FILENAME = "HardestDatabase110626.txt";

	public static final int HEADER_LINES_COUNT = 2;

	private String testCaseName;

	private byte[] problem;


	public void initSudokuHardestDatabaseTest(final String testCaseName, final byte[] problem) {
		this.testCaseName = testCaseName;
		this.problem = problem;
	}

	public static Collection<Object[]> data() throws IOException {

		final Collection<Object[]> results = new LinkedList<>();

		final InputStream is = ClassLoader.getSystemResourceAsStream(HARDEST_DB_FILENAME);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String buffer;

		for (int i=0; i < HEADER_LINES_COUNT; ++i)
			System.out.println(br.readLine());

		while ((buffer = br.readLine()) != null)
		{
			System.out.println(buffer);
			final String[] split = buffer.split(",");
			final String problemStr = split[0];
			final byte[] problem = formatProblem(problemStr);
			final String name = split[1];
			results.add(new Object[] { name, problem });
		}

		return results;
	}

	private static byte[] formatProblem(String problemStr) {
		final String NUM_CHARS = "0123456789";
		if (problemStr.length() != 81)
			throw new IllegalArgumentException("problemStr must be 81 characters");

		final byte[] result = new byte[81];
		for (int i=0; i < 81; ++i)
		{
			byte b = (byte) NUM_CHARS.indexOf(problemStr.substring(i, i+1));
			result[i] = (b >= 0 && b <= 9) ? b : 0;
		}

		return result;
	}

	@MethodSource("data")
	@ParameterizedTest
	void hardestDbTest(final String testCaseName, final byte[] problem) {
		initSudokuHardestDatabaseTest(testCaseName, problem);
		System.out.println("Test case: '" + testCaseName + "'");

		SudokuTester.builder()
				.problem(getProblem())
				.solution(null)  // Just print answer for time being
				.build()
			.run();

		System.out.println();
	}

	byte[] getProblem() {
		return problem;
	}

}
