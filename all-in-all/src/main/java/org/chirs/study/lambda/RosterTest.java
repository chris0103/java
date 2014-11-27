package org.chirs.study.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class RosterTest {

	// approach #1
	public static void printPersonsOlderThan(List<Person> roster, int age) {
	    for (Person p : roster) {
	        if (p.getAge() >= age) {
	            p.printPerson();
	        }
	    }
	}
	
	// approach #2
	public static void printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
	    for (Person p : roster) {
	        if (low >= p.getAge() && p.getAge() < high) {
	            p.printPerson();
	        }
	    }
	}
	
	// approach #3
	public static void printPersons(List<Person> roster, CheckPerson tester) {
	    for (Person p : roster) {
	        if (tester.test(p)) {
	            p.printPerson();
	        }
	    }
	}
	
	// approach #6
	public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {	// Predicate: Person -> boolean
	    for (Person p : roster) {
	        if (tester.test(p)) {
	            p.printPerson();
	        }
	    }
	}
	
	// approach #7
	public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {	// Consumer: Person -> void
        for (Person p : roster) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
	}
	
	public static void processPersonsWithFunction(List<Person> roster, Predicate<Person> tester, Function<Person, String> mapper, Consumer<String> block) {	// Function: Person -> String
	    for (Person p : roster) {
	        if (tester.test(p)) {
	            String data = mapper.apply(p);
	            block.accept(data);
	        }
	    }
	}
	
	// approach #8
	public static <X, Y> void processElements(Iterable<X> source, Predicate<X> tester, Function <X, Y> mapper, Consumer<Y> block) {
	    for (X p : source) {
	        if (tester.test(p)) {
	            Y data = mapper.apply(p);
	            block.accept(data);
	        }
	    }
	}
	
	public static void main(String[] args) {
		List<Person> roster = new ArrayList<>();
		
		// invoke of approach #1
		printPersonsOlderThan(roster, 18);
		
		// invoke of approach #2
		printPersonsWithinAgeRange(roster, 18, 25);
		
		// invoke of approach #3
		printPersons(roster, new CheckPersonEligibleForSelectiveService());
		
		// invoke of approach #4
		printPersons(roster, new CheckPerson() {
			
	        public boolean test(Person p) {
	            return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
	        }
		});
		
		// invoke of approach #5
		printPersons(roster, (Person p) -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);
		
		// invoke of approach #6
		printPersonsWithPredicate(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);

		// invoke of approach #7
		processPersons(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25, p -> p.printPerson());
		processPersonsWithFunction(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25, p -> p.getEmailAddress(), email -> System.out.println(email));
	
		// invoke of approach #8
		processElements(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25, p -> p.getEmailAddress(), email -> System.out.println(email));
		
		// invoke of approach #9
		roster.stream().filter(p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25).map(p -> p.getEmailAddress()).forEach(email -> System.out.println(email));
	}
}
