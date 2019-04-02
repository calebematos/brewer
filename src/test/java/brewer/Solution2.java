package brewer;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

class Result {

	/*
	 * Complete the 'reformatDate' function below.
	 *
	 * The function is expected to return a STRING_ARRAY. The function accepts
	 * STRING_ARRAY dates as parameter.
	 */

	public static List<String> reformatDate(List<String> dates) {
		List<String> returnList = new ArrayList<>();
		for (String date : dates) {
			String[] split = date.split(" ");
			String day = split[0].replaceAll("[a-zA-Z]", "");
			String month = split[1];
			String year = split[2];

			LocalDate d = LocalDate.of(Integer.parseInt(year), converterMonth(month), Integer.parseInt(day));
			returnList.add(d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		return returnList;
	}

	private static int converterMonth(String month) {
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM").withLocale(Locale.ENGLISH);
		TemporalAccessor accessor = parser.parse(month);
		return accessor.get(ChronoField.MONTH_OF_YEAR);
	}

}

public class Solution2 {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/home/calebe/Downloads/input000.txt"));

		int datesCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<String> dates = IntStream.range(0, datesCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).collect(toList());

		List<String> result = Result.reformatDate(dates);

		bufferedWriter.write(result.stream().collect(joining("\n")) + "\n");

		bufferedReader.close();
		bufferedWriter.close();
	}
}