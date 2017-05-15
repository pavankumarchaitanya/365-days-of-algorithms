import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoinChangeProblemSolver {
	public static void main(String[] args) {
		int[] coinInCentsArray = { 1, 5, 10, 25, 50 };

		solveCoinChangeProblem(20, coinInCentsArray, new ArrayList<Integer>());
	}

	public static void solveCoinChangeProblem(int sumCents, int[] coinInCentsArray, List<Integer> currentSelection) {
		int currentSum = currentSelection.stream().mapToInt(Integer::intValue).sum();
		if (currentSum == sumCents) {
			System.out.println(currentSelection);
			return;
		}

		for (int i = 0; i < coinInCentsArray.length; i++) {
			int tempSum = currentSum + coinInCentsArray[i];
			if (!(tempSum > sumCents)) {
				List<Integer> tempList = currentSelection.stream().collect(Collectors.toList());
				tempList.add(coinInCentsArray[i]);
				solveCoinChangeProblem(sumCents, coinInCentsArray, tempList);
			}

		}

	}
}
