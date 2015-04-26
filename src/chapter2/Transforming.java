package chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transforming {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Brian", "Dude", "Dudet", "Lebowski");
		
		
		/**
		 * imperative approach
		 */
		final List<String> uppercaseNames = 
				new ArrayList<>();
		for(String name : names) {
			uppercaseNames.add(name.toUpperCase());
		}
		
		/**
		 * naive functional approach
		 */
		final List<String> uppercaseNames2 = 
				new ArrayList<>();
		names.forEach(name ->
		uppercaseNames2.add(name.toUpperCase()));
		
		/**
		 * better functional, no new list
		 * explicitly created
		 */
		names.stream().map(name -> name.toUpperCase())
		.forEach(name -> System.out.print(name + " "));
		
		/**
		 * ex to show mapping transformation.
		 * This gets a list of integers, the 
		 * number of letters in each name
		 */
		names.stream()
		.map(name -> name.length())
		.forEach(count -> System.out.print(count + " "));
	
		/**
		 * more concise with method references
		 */
		names.stream()
		.map(String::toUpperCase)
		.forEach(System.out::println);
	}
}
