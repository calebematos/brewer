package brewer;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution {

	// Complete the miniMaxSum function below.
	static void miniMaxSum(int[] arr) {
		List<Integer> listaInteger = Arrays.stream(arr).boxed().collect(Collectors.toList());

		List<Integer> listaOrdenadaMaior = listaInteger.stream().sorted().collect(Collectors.toList());
		List<Integer> listaOrdenadaMenor = listaInteger.stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());

		List<Integer> listaMenor = listaOrdenadaMenor.stream().skip(1).collect(Collectors.toList());
		List<Integer> listaMaior = listaOrdenadaMaior.stream().skip(1).collect(Collectors.toList());

		Long valorMenor = listaMenor.stream().mapToLong(Integer::intValue).sum();
		Long valorMaior = 0L;
		for (Integer i : listaMaior) {
			valorMaior += i;
		}

		System.out.println(valorMenor + " " + valorMaior);
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int[] arr = new int[5];

		String[] arrItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < 5; i++) {
			int arrItem = Integer.parseInt(arrItems[i]);
			arr[i] = arrItem;
		}

		miniMaxSum(arr);

		scanner.close();
	}
}
