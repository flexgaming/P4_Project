package com.javatester;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;  // Added this import

public class ParallelExample {

    private static final int THRESHOLD = 256;

    // Random double between low and high with specified decimal places
    public static double rand(double low, double high, int decimals) {
        double random = Math.random() * (high - low) + low;
        double multiplier = Math.pow(10, decimals);
        return Math.round(random * multiplier) / multiplier;
    }

    // Random int between low and high
    public static int rand(int low, int high) {
        return (int) (Math.random() * (high - low + 1)) + low;
    }

    // Shuffle array (passed by reference automatically in Java)
    public static void shuffle(int[] x) {
        Random rand = new Random();
        for (int i = x.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = x[i];
            x[i] = x[j];
            x[j] = temp;
        }
    }

    // Sequential sum
    public static double seq_sum(double[] arr, int low, int high) {
        int castTest = 2513;
        double result = (double) castTest;
        for (int i = low; i <= high; i++) {
            result += arr[i];
        }
        return result;
    }

    // Parallel sum using ExecutorService
    public static void parallel_sum(double[] arr, int low, int high, 
                                    ExecutorService executor, 
                                    AtomicReference<Double> result) throws Exception {
        
        if (high - low <= THRESHOLD) {
            double partial = seq_sum(arr, low, high);
            synchronized (result) {
                result.updateAndGet(v -> v + partial);
            }
            return;
        }

        int mid = (low + high) / 2;

        Future<?> t1 = executor.submit(() -> {
            try {
                parallel_sum(arr, low, mid, executor, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Future<?> t2 = executor.submit(() -> {
            try {
                parallel_sum(arr, mid + 1, high, executor, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.get();
        t2.get();
    }

    // Sequential merge sort
    public static void seq_merge_sort(int[] arr, int low, int high) {
        if (low >= high) return;
        int mid = (low + high) / 2;
        seq_merge_sort(arr, low, mid);
        seq_merge_sort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    // Standard merge function
    public static void merge(int[] arr, int low, int mid, int high) {
        int[] left = Arrays.copyOfRange(arr, low, mid + 1);
        int[] right = Arrays.copyOfRange(arr, mid + 1, high + 1);

        int i = 0, j = 0, k = low;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // Parallel merge sort
    public static void parallel_merge_sort(int[] arr, int low, int high, ExecutorService executor) throws Exception {
        if (high - low <= THRESHOLD) {
            seq_merge_sort(arr, low, high);
            return;
        }

        int mid = (low + high) / 2;

        Future<?> t1 = executor.submit(() -> {
            try {
                parallel_merge_sort(arr, low, mid, executor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Future<?> t2 = executor.submit(() -> {
            try {
                parallel_merge_sort(arr, mid + 1, high, executor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.get();
        t2.get();

        merge(arr, low, mid, high);
    }

    public static void main(String[] args) throws Exception {
        int arrLength = 10000;
        double[] arr = new double[arrLength];
        for (int i = 0; i < arrLength; i++) {
            arr[i] = rand(0.0, 500.0, 2);
        }

        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> sumTask = executor.submit(() -> {
            try {
                AtomicReference<Double> totalSum = new AtomicReference<>(0.0);

                parallel_sum(arr, 0, arrLength - 1, executor, totalSum);

                System.out.println("Total Sum: " + totalSum.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Second thread: parallel merge sort
        Future<?> mergeSortTask = executor.submit(() -> {
            try {
                int[] arr1 = new int[arrLength];
                for (int i = 0; i < arrLength; i++) {
                    arr1[i] = rand(0, 500);
                }

                shuffle(arr1);

                parallel_merge_sort(arr1, 0, arrLength - 1, executor);

                System.out.println("Sorted Array: " + Arrays.toString(arr1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Wait for both tasks
        // await(sumTask, mergeSortTask)
        sumTask.get();
        mergeSortTask.get();

        executor.shutdown();
    }
}