import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SubsetSumProblem {
	static final HashMap<Integer, Set<Set<Integer>>> subsetSumCache = new HashMap<>();

	public static void main(String[] args) {

		Set<Integer> integerSet = new HashSet<>();
		
		for(int i=1; i < 100;i++){
			integerSet.add(i);
		}
		

		Set<Set<Integer>> solution = subSetSum(3000, integerSet);

		System.out.println(solution);
		System.out.println("Subset Count :  " + solution.size());

	}

	public static Set<Set<Integer>> subSetSum(Integer sum, Set<Integer> inputSet) {

		Set<Set<Integer>> subsetSumCachedValue = subsetSumCache.get(sum);
		Set<Set<Integer>> outputSet = new HashSet<>();
		if (subsetSumCachedValue != null) {
			return subsetSumCachedValue;
		}

		if (inputSet.size() == 0) {
			return outputSet; // return empty set;
		}

		for (Integer i : inputSet) {
			if (sum - i == 0) {
				Set<Integer> e = new HashSet<Integer>();
				e.add(i);
				outputSet.add(e);
			}

			if ((sum - i) > 0) {
				Set<Integer> inputSubSet = new HashSet<>();
				inputSubSet.addAll(inputSet);
				inputSubSet.remove(i);
				Set<Set<Integer>> outputSubSet = subSetSum(sum - i, inputSubSet);

				for (Set<Integer> sumSet : outputSubSet) {
					
						Set<Integer> tempSumSet = new HashSet<Integer>();
						tempSumSet.addAll(sumSet);
						tempSumSet.add(i);
						int tempSum = tempSumSet.stream().mapToInt(Integer::intValue).sum();

						if ((tempSum) == sum) { // This step is to filter out tempSubSets that have i in the outputSubSet already.
						outputSet.add(tempSumSet);
					}
					

				}

				// outputSet.addAll(outputSubSet);

			}
		}
		if (outputSet.size() != 0 && subsetSumCache.get(sum) == null) {
			for (Set<Integer> set : outputSet) {
				if (sum == 2 && set.stream().mapToInt(j -> j.intValue()).sum() != sum) {
					System.out.println(sum);
				}
			}

			if (subsetSumCache.get(sum) != null) {
				outputSet.addAll(subsetSumCache.get(sum));
			}
			subsetSumCache.put(sum, outputSet);
		}
		return outputSet;

	}

}
