package com.king.re0;

import org.junit.Test;
import reactor.core.publisher.Flux;


public class Re0ApplicationTests {

	@Test
	public void contextLoads() {
		Flux.just("123").subscribe(System.out::println);
	}

}
