package test.spring.repository.song;

import java.util.ArrayList;
import test.spring.component.song.SampleListDTO;

public class PermutationDAO {
	
	// 동선최적화(순열)
	public static ArrayList<ArrayList<SampleListDTO>> permutation(ArrayList<SampleListDTO> main) {
	    ArrayList<ArrayList<SampleListDTO>> permutations = new ArrayList<>();
	    generatePermutations(new ArrayList<>(), main, permutations);
	    return permutations;
	}

	private static void generatePermutations(ArrayList<SampleListDTO> prefix, ArrayList<SampleListDTO> remaining, ArrayList<ArrayList<SampleListDTO>> permutations) {
	    if (remaining.isEmpty()) {
	        permutations.add(new ArrayList<>(prefix));
	        return;
	    }
	    
	    for (int i = 0; i < remaining.size(); i++) {
	        SampleListDTO element = remaining.get(i);
	        ArrayList<SampleListDTO> newPrefix = new ArrayList<>(prefix);
	        newPrefix.add(element);
	        ArrayList<SampleListDTO> newRemaining = new ArrayList<>(remaining);
	        newRemaining.remove(i);
	        generatePermutations(newPrefix, newRemaining, permutations);
	    }
	}
	
}
