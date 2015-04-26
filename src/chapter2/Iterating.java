package chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Iterating {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Brian", "Dude", "Dudet", "Lebowski");
		
		/**
		 * old iteritave way
		 */
		for(String name : names) {
			System.out.println(name);
		}
		
		/**
		 * functional
		 */
		names.forEach(new Consumer<String>() {
			public void accept(String arg0) {
				System.out.println(arg0);
			}
		});
		
		/**
		 * functional, less verbose
		 */
		names.forEach((final String name) ->
		System.out.println(name));
		
		/**
		 * functional, even less verbose, with
		 * type inference
		 */
		names.forEach((name) -> System.out.println(name));
		
		/**
		 * functional, EVEN LESS VERBOSE
		 */
		names.forEach(System.out::println);
	}

}
