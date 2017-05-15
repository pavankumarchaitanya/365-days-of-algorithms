
public class StringCombinationPrinter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] arr = { 'a', 'b', 'c', 'd', 'e', 'f' };

		// printStringCombinations(arr, "", arr.length);
		printAllLengthStringCombinations(arr);
	}

	public static void printAllLengthStringCombinations(char[] arr) {

		for (int i = 1; i < arr.length; i++) {
			printStringCombinations(arr, "", i);
		}
	}

	public static void printStringCombinations(char[] arr, String prefix, int length) {
		if (length == 0) {
			System.out.println(prefix);
			return;
		}
		String tempPrefix = "";
		for (int i = 0; i < arr.length; i++) {
			if (!prefix.contains(arr[i] + "")) {
				tempPrefix = prefix + arr[i];
				printStringCombinations(arr, tempPrefix, length - 1);
			}
		}
	}

}
