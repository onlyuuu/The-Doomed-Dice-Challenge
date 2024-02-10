import java.util.*;

public class The_Doomed_Dice_Challenge {
    public static void main(String[] args) 
    {
        int[] die_a = {1, 2, 3, 4, 5, 6};
        int[] die_b = {1, 2, 3, 4, 5, 6};

        System.out.println("total combinations: " + (die_a.length * die_b.length));

        System.out.println("possible combinations");
        for (int i = 0; i < die_a.length; i++) 
        {
            for (int j = 0; j < die_b.length; j++) 
            {
                System.out.print("(" + die_a[i] + "," + die_b[j] + ") ");
            }
            System.out.println();
        }

        System.out.println("Sum of possible combinations");
        int[][] matrix = new int[6][6];
        for (int i = 0; i < die_a.length; i++) 
        {
            for (int j = 0; j < die_b.length; j++) 
            {
                matrix[i][j] = die_a[i] + die_b[j];
            }
        }
        for (int[] row : matrix) 
        {
            System.out.println(Arrays.toString(row));
        }

        int[] no = new int[11];
        for (int[] row : matrix) 
        {
            for (int each : row) 
            {
                no[each - 2]++;
            }
        }
        System.out.println("occurence of each possible sum");
        for (int i = 0; i < no.length; i++) 
        {
            System.out.print(no[i] + " ");
        }
        System.out.println("\nprobability of each sum");
/*part B */
        Map<Integer, Double> probabilityMap = new HashMap<>();
        for (int i = 0; i < no.length; i++) 
        {
            double probability = (double) no[i] / (die_a.length * die_b.length);
            probabilityMap.put(i + 2, probability);
        }

        for (Map.Entry<Integer, Double> entry : probabilityMap.entrySet()) 
        {
            System.out.println("P(Sum = " + entry.getKey() + ") = " + entry.getValue());
        }

        int[] new_die_a = {1, 2, 2, 3, 3, 4};
        Map<Integer, Integer> desiredOutcome = new HashMap<>();
        for (int i = 0; i < no.length; i++) 
        {
            desiredOutcome.put(i + 2, no[i]);
        }
        List<Integer> newDieBList = new ArrayList<>();

        for (int pos = 2; pos <= 12; pos++) 
        {
            while (desiredOutcome.get(pos) != null && desiredOutcome.get(pos) > 0) 
            {
                newDieBList.add(pos - 1);
                for (int i = 0; i < new_die_a.length; i++) 
                {
                    int outcome = new_die_a[i] + (pos - 1);
                    if (desiredOutcome.containsKey(outcome) && desiredOutcome.get(outcome) > 0) 
                    {
                        desiredOutcome.put(outcome, desiredOutcome.get(outcome) - 1);
                    }
                }
            }
        }
       
        int[] new_die_b = new int[newDieBList.size()];
        for (int i = 0; i < newDieBList.size(); i++) 
        {
            new_die_b[i] = newDieBList.get(i);
        }

        System.out.println("Adjusted Die A: " + Arrays.toString(new_die_a));
        System.out.println("Adjusted Die B: " + Arrays.toString(new_die_b));
    }
}
