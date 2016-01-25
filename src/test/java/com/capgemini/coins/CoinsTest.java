/**
 * 
 */
package com.capgemini.coins;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author ALUKASZC
 *
 */
public class CoinsTest {
	private List<Integer> lista;
	int result;

	@Before 
	public void doBefore(){
		this.lista = new ArrayList<Integer>();
	}
	
	@After
	public void doCleanupAfterTest() {
		this.lista.clear();
	}
	
	@Test
	public void shouldReturn3ForTheSameElements() {
		lista.add(1);
		lista.add(1);
		lista.add(1);
		lista.add(1);
		lista.add(1);
		result = Coins.solution(lista);
		assertEquals(3, result);		
	}
	
	@Test
	public void shouldReturn3ForMixedCoins() {
		lista.add(1);
		lista.add(1);
		lista.add(0);
		lista.add(0);
		lista.add(1);
		lista.add(1);
		result = Coins.solution(lista);
		assertEquals(3, result);
	}

	@Test
	public void shouldReturn4ForMixedCoins() {
		lista.add(1);
		lista.add(1);
		lista.add(0);
		lista.add(1);
		lista.add(1);
		lista.add(0);
		result = Coins.solution(lista);
		assertEquals(4, result);
	}

	@Test
	public void shouldReturn4ForOnesWithFirstZero() {
		lista.add(0);
		lista.add(1);
		lista.add(1);
		lista.add(1);
		lista.add(1);
		result = Coins.solution(lista);
		assertEquals(4, result);
	}

	@Test
	public void shouldReturn4ForOnesWithLastZero() {
		lista.add(1);
		lista.add(1);
		lista.add(1);
		lista.add(1);
		lista.add(0);
		result = Coins.solution(lista);
		assertEquals(4, result);
	}

	@Test
	public void shouldReturnErrorNoCoins() {
		result = Coins.solution(lista);
		assertEquals(0, result);		
	}

	@Test
	public void shouldReturnErrorOneCoin() {
		lista.add(1);
		result = Coins.solution(lista);
		assertEquals(0, result);		
	}

}
