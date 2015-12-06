package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Test {
	public static void main(String[] args) {
		long[] l = new long[40];
		for (int i = 0; i < 40; i++) {
			l[i] = i;
		}
		long[] l2 = new long[40];
		Random r = new Random();
		long st = System.currentTimeMillis();
		Set<Integer> set = new TreeSet<>();
		for (int i = 0; i < 40; i++) {
			int j = 0;
			do {
				j = r.nextInt(40);
			} while (!set.add(j));
			l2[i] = l[j];
		}
		long en = System.currentTimeMillis();
		System.out.println(set);
		System.out.println(Arrays.toString(l2));
		System.out.println(en - st);
	}
}
