package org.chris.study.concurrency.jcip.examples.chap03;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Listing 3.9. Thread confinement of local primitive and reference variables.
 */
public class StackConfinement {
	
	private Ark ark = new Ark();

	public int loadTheArk(Collection<Animal> candidates) {
		SortedSet<Animal> animals;
		int numPairs = 0;
		Animal candidate = null;
		
		// animals confined to method, don’t let them escape!
		animals = new TreeSet<Animal>(new SpeciesGenderComparator());
		animals.addAll(candidates);
		for (Animal a : animals) {
			if (candidate == null || !candidate.isPotentialMate(a))
				candidate = a;
			else {
				ark.load(new AnimalPair(candidate, a));
				++numPairs;
				candidate = null;
			}
		}
		return numPairs;
	}
}

class Animal {

	public boolean isPotentialMate(Animal a) {
		return false;
	}
}

class SpeciesGenderComparator implements Comparator<Animal> {

	@Override
	public int compare(Animal o1, Animal o2) {
		return 0;
	}
}

class AnimalPair {
	
	public AnimalPair(Animal candidate, Animal animal) {
		
	}
}

class Ark {
	
	public void load(AnimalPair animalPair) {
		
	}
}
