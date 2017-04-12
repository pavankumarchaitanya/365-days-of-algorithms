import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
// https://en.wikipedia.org/wiki/Closest_pair_of_points_problem
public class DistanceBetweenPoints {
	public static void main(String[] args) throws IOException {

		class XComparator implements Comparator<Point> {

			@Override
			public int compare(Point p1, Point p2) {
				Integer p1X = p1.x;
				Integer p2X = p2.x;
				return p1X.compareTo(p2X);
			}

		}

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		ArrayList<String> inputArray = new ArrayList<String>();

//		inputArray.add("5");
//		inputArray.add("0 2");
//		inputArray.add("6 67");
//		inputArray.add("43 71");
//		inputArray.add("39 107");
//		inputArray.add("189 140");
		
		inputArray.add("20");

		for(int i=0;i<20;i++){
			inputArray.add((int) (Math.random()*10000) +" "+(int)Math.random()*10000);

			
		}
		inputArray.add("0");
		List<Point> pointsList = new ArrayList<Point>();
		int N = new Integer(inputArray.get(0));
		DistanceBetweenPoints classInstance = (new DistanceBetweenPoints());
		for (int i = 1; i < N + 1; i++) {
			if (inputArray.get(i).equals("0"))
				break;

			String tempX = inputArray.get(i).split(" ")[0];
			String tempY = inputArray.get(i).split(" ")[1];
			DistanceBetweenPoints.Point tempPoint = classInstance.new Point(new Integer(tempX), new Integer(tempY));
			pointsList.add(tempPoint);
		}

		Collections.sort(pointsList, new XComparator());
		double minDistance = smallestDistanceBetweenTwoPoints(pointsList);
//System.out.println("smallestDistance = " +  smallestDistance);
//		double minDistance = 10000;
//		for (Point p1 : pointsList) {
//			for (Point p2 : pointsList) {
//				if (!p1.equals(p2)) {
//					double distance = p1.distanceTo(p2);
//					if (distance < minDistance) {
//						minDistance = distance;
//					}
//				}
//			}
//		}
		if (minDistance < 10000) {
			DecimalFormat dff = new DecimalFormat(".####");
			System.out.println(dff.format(minDistance));
		} else {
			System.out.println("INFINITY");

		}

	}

	private static double smallestDistanceBetweenTwoPoints(List<Point> pointsList) {
		if(pointsList.size()<2)
			return Double.MAX_VALUE;
		if(pointsList.size()==2){
			return pointsList.get(0).distanceTo(pointsList.get(1));
		}
		
		
		List<Point> leftHalfPoints = pointsList.subList(0, pointsList.size() / 2);
		List<Point> rightHalfPoints = pointsList.subList(pointsList.size() / 2, pointsList.size());
		double leftMin = smallestDistanceBetweenTwoPoints(leftHalfPoints);
		double rightMin = smallestDistanceBetweenTwoPoints(rightHalfPoints);
		double minOfLeftAndRight = 0;
		if (leftMin < rightMin) {
			minOfLeftAndRight = leftMin;
		} else {
			minOfLeftAndRight = rightMin;
		}

		double stripMinimum = getMinimumFromStripOfWidthMinOfLeftAndRightAtMidPoint(minOfLeftAndRight, pointsList,
				leftHalfPoints, rightHalfPoints);

		if (stripMinimum < minOfLeftAndRight)
			return stripMinimum;
		else
			return minOfLeftAndRight;
	}

	private static double getMinimumFromStripOfWidthMinOfLeftAndRightAtMidPoint(double minOfLeftAndRight,
			List<Point> pointsList, List<Point> leftHalfPoints, List<Point> rightHalfPoints) {
		double leftx = leftHalfPoints.get(leftHalfPoints.size()-1).x;
		double rightx = rightHalfPoints.get(0).x;

		double midPoint = (leftx + rightx) / 2;
		int leftIndex = 0;
		int rightIndex = pointsList.size() - 1;
		double stripXStartCoordinate = midPoint - minOfLeftAndRight;
		double stripXEndCoordinate = midPoint + minOfLeftAndRight;
		for (int i = leftHalfPoints.size() - 1; i > 0; i--) {
			if (leftHalfPoints.get(i).x > stripXStartCoordinate)
				continue;
			leftIndex = i;
		}
		for (int i = 0; i < rightHalfPoints.size(); i++) {
			if (rightHalfPoints.get(i).x < stripXEndCoordinate)
				continue;
			rightIndex = i;
		}

		List<Point> stripList = pointsList.subList(leftIndex, rightIndex);
		double minDistance = 10000;
		for (Point p1 : stripList) {
			for (Point p2 : stripList) {
				if (!p1.equals(p2)) {
					double distance = p1.distanceTo(p2);
					if (distance < minDistance) {
						minDistance = distance;
					}
				}
			}
		}

		return minDistance;

	}

	class Point {

		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		double distanceTo(Point p) {
			return Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y));
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Point)) {
				return false;
			}
			Point other = (Point) obj;

			if (x != other.x) {
				return false;
			}
			if (y != other.y) {
				return false;
			}
			return true;
		}

	}
}
