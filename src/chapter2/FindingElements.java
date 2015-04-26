package chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FindingElements {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Brian", "Dude", "Dudet", "Lebowski");
		
		/**old way**/
		final List<String> startsWithN = new
				ArrayList<>();
		for(String name : names) {
			if(name.startsWith("N")) {
				startsWithN.add(name);
			}
		}
		
		/** functional **/
		final List<String> startsWithN2 =
				names.stream()
				.filter(name -> name.startsWith("D"))
				.collect(Collectors.toList());
		
		/**store the lambda expression for repeat use**/
		final Predicate<String> startsWthPredicate = 
				name -> name.startsWith("N");
				
		/**Using Fuction instance instead of
		 * the static Predicate below
		 */
		final Function<String, Predicate<String>> 
		startsWithLetter = 
		letter -> name -> name.startsWith(letter);
	}
	/**Predicate that allows different letters **/
	public static Predicate<String> checkIfStartsWith(final String letter) {
		return name -> name.startsWith(letter);
	}
}
