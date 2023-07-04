package test.spring.repository.song;

import java.util.ArrayList;
import test.spring.component.song.sampleListDTO;

public class PermutationDAO {
	
	// ¼ø¿­
	public static ArrayList<ArrayList<sampleListDTO>> permutation(ArrayList<sampleListDTO> main) {
	    ArrayList<ArrayList<sampleListDTO>> permutations = new ArrayList<>();
	    generatePermutations(new ArrayList<>(), main, permutations);
	    return permutations;
	}

	private static void generatePermutations(ArrayList<sampleListDTO> prefix, ArrayList<sampleListDTO> remaining, ArrayList<ArrayList<sampleListDTO>> permutations) {
	    if (remaining.isEmpty()) {
	        permutations.add(new ArrayList<>(prefix));
	        return;
	    }
	    
	    for (int i = 0; i < remaining.size(); i++) {
	        sampleListDTO element = remaining.get(i);
	        ArrayList<sampleListDTO> newPrefix = new ArrayList<>(prefix);
	        newPrefix.add(element);
	        ArrayList<sampleListDTO> newRemaining = new ArrayList<>(remaining);
	        newRemaining.remove(i);
	        generatePermutations(newPrefix, newRemaining, permutations);
	    }
	}
	
}
