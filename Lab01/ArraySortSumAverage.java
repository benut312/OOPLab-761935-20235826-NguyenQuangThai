import java.util.Arrays;
import java.util.Scanner;

public class ArraySortSumAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        double[] arr = new double[n];
        double sum = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Enter element [" + i + "]: ");
            arr[i] = scanner.nextDouble();
            sum += arr[i];
        }

        Arrays.sort(arr);
        double average = sum / n;

        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.println("Sum = " + sum);
        System.out.println("Average = " + average);

        scanner.close();
    }
}