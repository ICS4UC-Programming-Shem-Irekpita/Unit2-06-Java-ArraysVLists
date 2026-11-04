import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Reads integer lists from text files and calculates mean, median, and mode.
 *
 * @author  Shem Irekpita
 * @version 1.0
 * @since   2026-11-03
 */
public final class ArraysVLists {

    /** File names to process. */
    private static final String[] FILE_NAMES = {
        "Unit2-06-Set1.txt",
        "Unit2-06-Set2.txt",
        "Unit2-06-Set3.txt"
    };

    /** Private constructor to prevent instantiation. */
    private ArraysVLists() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
     * Calculates the mean of an array.
     *
     * @param numbers input array
     * @return mean value
     */
    public static double calcMean(final int[] numbers) {
        double sum = 0;
        for (int value : numbers) {
            sum += value;
        }
        return sum / numbers.length;
    }

    /**
     * Calculates the median of a sorted array.
     *
     * @param numbers sorted input array
     * @return median value
     */
    public static double calcMedian(final int[] numbers) {
        final int middle = numbers.length / 2;
        if (numbers.length % 2 == 1) {
            return numbers[middle];
        }
        return (numbers[middle - 1] + numbers[middle]) / 2.0;
    }

    /**
     * Calculates mode(s) of a sorted array.
     *
     * @param numbers sorted input array
     * @return list of modes
     */
    public static ArrayList<Integer> calcMode(final int[] numbers) {
        final ArrayList<Integer> modes = new ArrayList<>();
        int maxCount = 0;
        int index = 0;

        while (index < numbers.length) {
            int count = 1;
            while (index + count < numbers.length
                    && numbers[index + count] == numbers[index]) {
                count++;
            }

            if (count > maxCount) {
                maxCount = count;
                modes.clear();
                modes.add(numbers[index]);
            } else if (count == maxCount) {
                modes.add(numbers[index]);
            }
            index += count;
        }

        if (maxCount == 1) {
            modes.clear();
        }
        return modes;
    }

    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        for (String fileName : FILE_NAMES) {
            System.out.println("\nReading " + fileName);
            final ArrayList<Integer> list = new ArrayList<>();

            try (Scanner scanner = new Scanner(new File(fileName))) {
                while (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {
                        list.add(scanner.nextInt());
                    } else {
                        scanner.next();
                    }
                }
            } catch (FileNotFoundException exception) {
                System.out.println("Error: could not find " + fileName);
                continue;
            }

            if (list.isEmpty()) {
                System.out.println("Error: file is empty");
                continue;
            }

            final int[] numbers = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                numbers[i] = list.get(i);
            }

            System.out.print("Numbers:");
            for (int number : numbers) {
                System.out.print(" " + number);
            }
            System.out.println();

            Arrays.sort(numbers);

            System.out.println("Mean: " + calcMean(numbers.clone()));
            System.out.println("Median: " + calcMedian(numbers.clone()));
            System.out.println("Mode: " + calcMode(numbers.clone()));
        }
    }
}
