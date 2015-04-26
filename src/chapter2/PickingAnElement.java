package chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PickingAnElement {

	List<String> names = Arrays.asList("Brian", "Dude", "Dudet", "Lebowski");
	
	/** old way **/
	public static void pickName(final List<String> names, final String startingLetter) {
		String foundName = null;
		for(String name : names) {
			if(name.startsWith(startingLetter)) {
				foundName = name;
				break;
			}
		}
		
		
	}
	
	/** functional **/
	public static void pickName2(final List<String> names, final String startingLetter) {
		final Optional<String> foundName =
				names.stream().filter(name -> name.startsWith(startingLetter)).findFirst();
		System.out.println(String.format("A name starting with %s: %s", startingLetter, foundName.orElse("No name found")));
	}
}
