package com.capgemini.coins;

import java.util.List;
import java.lang.Math;

/**
 * Created by ldrygala on 2015-02-06.
 * <p/>
 * Consider N coins aligned in a row. Each coin is showing either heads or tails. The adjacency of these coins is the number of adjacent
 * pairs of coins with the same side facing up.
 * <p/>
 * It must return the maximum possible adjacency that can be obtained by reversing exactly one coin (that is, one of the coins must be reversed).
 * Consecutive elements of array A represent consecutive coins in the row. Array A contains only 0s and/or 1s: 
 * 0 represents a coin with heads facing up; 1 represents a coin with tails facing up. 
 * For example, given array A consisting of six numbers, such that:
 * <p/>
 * A[0] = 1
 * A[1] = 1
 * A[2] = 0
 * A[3] = 1
 * A[4] = 0
 * A[5] = 0
 * <p/>
 * the function should return 4. The initial adjacency is 2, as there are two pairs of adjacent coins with the same side facing up, 
 * namely (0, 1) and (4, 5). After reversing the coin represented by A[2], the adjacency equals 4, as there are four pairs of adjacent coins 
 * with the same side facing up, namely: (0, 1), (1, 2), (2, 3) and (4, 5), and it is not possible to obtain a higher adjacency. 
 * The same adjacency can be obtained by reversing the coin represented by A[3].
 */
public class Coins {
	public static int solution(List<Integer> coins) {
		int wynik;
		
		System.out.print("Przed: ");
		for (int i = 0; i< coins.size(); i++) {
			System.out.print(coins.get(i));
		}
		System.out.println();
		
		coins = Coins.changeCoin(coins);

		wynik = Coins.countPairs(coins);	
		System.out.print("Po: ");
		for (int i = 0; i< coins.size(); i++) {
			System.out.print(coins.get(i));
		}
		System.out.println(".  Wynik = " + wynik);
		return wynik;
	}

	private static List<Integer> changeCoin(List<Integer> coins)
	{
		int i;
		int firstFalsePosition;
		boolean lastHit;

		if (coins.isEmpty() || coins.size() < 2) {
			return coins;
		}
		lastHit = false;
		firstFalsePosition = -1;
/*1. 010
2. 01 krańce 10
3. Zmieniam dowolne 01
4. all są identyczne, zmieniam pierwszą*/
		for (i = 1; i < coins.size(); i++) {
			if (coins.get(i) == coins.get(i-1)) {
				lastHit = true;
			} else {
				if (!lastHit && i > 1) {	//najlepsze miejsce do zmiany wartosci monety
					coins.set(i-1, Math.abs(coins.get(i-1)-1));
					return coins;
				}
				if (firstFalsePosition == -1) {
					firstFalsePosition = i - 1;
				}
				lastHit = false;
			}
		}
		if (firstFalsePosition > -1) {
			if (firstFalsePosition == coins.size() - 2) {
				coins.set(coins.size()-1, Math.abs(coins.get(coins.size()-1)-1));
			} else {
				coins.set(firstFalsePosition, Math.abs(coins.get(firstFalsePosition)-1));
			}
		} else {
			coins.set(0, Math.abs(coins.get(0)-1));			
		}
		return coins;
	}

	private static int countPairs(List<Integer> coins)
	{
		int sum = 0;

		if (coins.isEmpty() || coins.size() < 2) 
			return 0;

		for(int i = 1; i < coins.size(); i++) {
			if (coins.get(i) == coins.get(i-1)) {
				sum++;
			}
		}
		return sum;
	}
}
