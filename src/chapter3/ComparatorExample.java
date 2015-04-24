package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.groupingBy;

public class ComparatorExample {

  public static void main(String[] args) {
    final List<Person> people = Arrays.asList(new Person("John", 20), new Person("Sara", 21), new Person("Jane", 21),
            new Person("Greg", 35));
    
    //sorting by age
    List<Person> acendingAge = 
        people.stream().sorted((person1, person2) -> person1.ageDifference(person2))
        .collect(Collectors.toList());
    
    //more concise
    List<Person> anotherAge = 
        people.stream().sorted(Person::ageDifference).collect(Collectors.toList());
    printPeople("your butt", acendingAge);
    
    //Comparators to flip the order
    Comparator<Person> compareAscending = (person1, person2) -> person1.ageDifference(person2);
    Comparator<Person> compareDescending = compareAscending.reversed();
    
    //sort ascending with comparator
    people.stream().sorted(compareAscending).collect(Collectors.toList());
    //sort descending with comparator
    people.stream().sorted(compareDescending).collect(Collectors.toList());
    
    //Sorting by name instead
    printPeople("Sorted in ascending order by name: " , people.stream().sorted((person1, person2) -> person1.getName().compareTo(person2.getName())).collect(Collectors.toList()));
    
    //youngest person
    people.stream().min(Person::ageDifference).ifPresent(System.out::println);
    //oldest person
    people.stream().max(Person::ageDifference).ifPresent(System.out::println);
    
    final Function<Person, String> byName = person -> person.getName();
    //more concise by name sorting
    people.stream().sorted(Comparator.comparing(byName));
    
    //compare by age, then by name
    final Function<Person, Integer> byAge = person -> person.getAge();
    people.stream().sorted(Comparator.comparing(byAge).thenComparing(byName)).collect(Collectors.toList());
    
    /**Collections section**/
    //semi old style
    List<Person> olderThan20 = new ArrayList<>();
    people.stream().filter(person -> person.getAge() > 20).forEach(person -> olderThan20.add(person));
    
    List<Person> olderThan20Part2 = 
        people.stream().filter(person -> person.getAge() > 20)
        .collect(Collectors.toList());
    
    //grouping example
    Map<Integer, List<Person>> peopleByAge = people.stream().collect(Collectors.groupingBy(Person::getAge));
    
    //if we only wanted people's names
    Map<Integer, List<String>> nameOfPeopleByAge = people.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toList())));
    
    //gets a bit ridiculous, but lets get oldest person of each letter
    Comparator<Person> byAgeComp = Comparator.comparing(Person::getAge);
    Map<Character, Optional<Person>> oldestPersonOfEachLetter = 
        people.stream().collect(groupingBy(person -> person.getName().charAt(0), reducing(BinaryOperator.maxBy(byAgeComp))));
  }
  
  public static void printPeople(final String message, final List<Person> people) {
    System.out.println(message);
    people.forEach(System.out::println);
  }
  
  public static class Person {
    private final String name;
    private final int age;
    
    public Person(final String theName, final int theAge) {
      name = theName;
      age = theAge;
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }
    
    public int ageDifference(final Person other) {
      return age - other.age;
    }
    
    public String toString() {
      return String.format("%s - %d", name, age);
    }
  }
}
