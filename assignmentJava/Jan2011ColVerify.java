package assignmentJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jan2011ColVerify {
	public void VerifyListInDescendingOrder(List descendingColData) {
		List descList = new ArrayList(descendingColData);
		Collections.sort(descList);
		Collections.reverse(descList);
		boolean reverseSorted = descList.equals(descendingColData);

		if (reverseSorted) {
			System.out.println("[logger]:Numeric values in Jan 2011 column are in descending order");
		} else {
			System.out.println("[logger]:Numeric values in Jan 2011 column are not in descending order");
		}
	}

	public void VerifyListInAscendingOrder(List ascendingColData) {
		List ascenList = new ArrayList(ascendingColData);
		Collections.sort(ascenList);
		boolean ascenSorted = ascenList.equals(ascendingColData);

		if (ascenSorted) {
			System.out.println("[logger]:Numeric values in Jan 2011 column are in ascending order");
		} else {
			System.out.println("[logger]:Numeric values in Jan 2011 column are not in ascending order");
		}

	}

}
