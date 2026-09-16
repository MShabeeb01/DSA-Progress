import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ActivitySelection {
    // Operation: Activity Selection Problem (Greedy Algorithm)
    public static int maxActivities(int start[], int end[]) {
        int n = start.length;

        // 2D Array to store original index, start time, and end time: [index, start, end]
        int activities[][] = new int[n][3];
        for (int i = 0; i < n; i++) {
            activities[i][0] = i;        // Activity index (A0, A1, ...)
            activities[i][1] = start[i];  // Start time
            activities[i][2] = end[i];    // End time
        }

        // Step 1: Sort activities based on end time (ascending order)
        Arrays.sort(activities, Comparator.comparingDouble(o -> o[2]));

        // Step 2: Always pick the 1st activity (finishes earliest)
        int maxAct = 1;
        ArrayList<Integer> selectedActivities = new ArrayList<>();
        selectedActivities.add(activities[0][0]);
        int lastEnd = activities[0][2]; // Track the end time of the last chosen activity

        // Step 3: Iterate through remaining activities and pick non-overlapping ones
        for (int i = 1; i < n; i++) {
            // Disjoint condition: start time of current activity >= end time of last chosen activity
            if (activities[i][1] >= lastEnd) {
                maxAct++;
                selectedActivities.add(activities[i][0]);
                lastEnd = activities[i][2]; // Update end time to current activity's end time
            }
        }

        // Print selected activity indices
        System.out.print("Selected Activities: ");
        for (int actIdx : selectedActivities) {
            System.out.print("A" + actIdx + " ");
        }
        System.out.println();

        return maxAct;
    }

    public static void main(String args[]) {
        int start[] = {1, 3, 0, 5, 8, 5};
        int end[]   = {2, 4, 6, 7, 9, 9};

        int count = maxActivities(start, end);
        System.out.println("Maximum activities count = " + count); // Output: 4 (A0, A1, A3, A4)
    }
}

/*
==================== SUMMARY ====================

Problem: Activity Selection Problem (Disjoint Intervals)

Objective:
Given `n` activities with their start and end times, select the maximum number of 
activities that can be performed by a single person/machine, assuming that a person 
can only work on a single activity at a time (activities must be non-overlapping / disjoint).

Greedy Choice Strategy:
- **Always prioritize the activity that finishes first (earliest end time).**
- Finishing early leaves the maximum possible remaining time window to accommodate 
  subsequent activities.

Algorithmic Breakdown:
1. Sorting:
   - Sort all activities in ascending order of their **end time** (`end[]`).
2. First Selection:
   - Pick the very first activity from the sorted list (`activities[0]`).
   - Set `count = 1` and track `lastEnd = activities[0].end`.
3. Non-Overlapping Check (Disjoint Test):
   - For every subsequent activity `i`:
     - If `activities[i].start >= lastEnd`:
       - It does not overlap with the previously scheduled task.
       - Increment `count++`.
       - Update `lastEnd = activities[i].end`.
     - Otherwise, skip the activity (conflicts with ongoing task).

-------------------------------------------------

Timeline & Interval Selection Visual

Activities sorted by End Time:
   A0: [1, 2]
   A1: [3, 4]
   A2: [0, 6]
   A3: [5, 7]
   A4: [8, 9]
   A5: [5, 9]

Timeline (0 to 9):
  0   1   2   3   4   5   6   7   8   9
  |---|---|---|---|---|---|---|---|---|
      [===]                                 -> Pick A0 (end = 2)
              [===]                         -> Pick A1 (start 3 >= 2, new end = 4)
  [===================]                     -> Skip A2 (start 0 < 4)
                      [=======]             -> Pick A3 (start 5 >= 4, new end = 7)
                                  [===]     -> Pick A4 (start 8 >= 7, new end = 9)
                      [===============]     -> Skip A5 (start 5 < 7)

Chosen: A0 -> A1 -> A3 -> A4 (Total = 4)

-------------------------------------------------

Step-by-Step Trace Table

start = [1, 3, 0, 5, 8, 5], end = [2, 4, 6, 7, 9, 9]

----------------------------------------------------------------------------------------------------------------------
Sorted Activity | Start | End | `lastEnd` Before | Condition (`start >= lastEnd`) | Action Taken       | `lastEnd` After
----------------------------------------------------------------------------------------------------------------------
A0              | 1     | 2   | -                | 1st Activity (Always picked)   | Pick A0 (count = 1)| 2
A1              | 3     | 4   | 2                | 3 >= 2 (True)                  | Pick A1 (count = 2)| 4
A2              | 0     | 6   | 4                | 0 >= 4 (False)                 | Discard (Overlap)  | 4
A3              | 5     | 7   | 4                | 5 >= 4 (True)                  | Pick A3 (count = 3)| 7
A4              | 8     | 9   | 7                | 8 >= 7 (True)                  | Pick A4 (count = 4)| 9
A5              | 5     | 9   | 9                | 5 >= 9 (False)                 | Discard (Overlap)  | 9
----------------------------------------------------------------------------------------------------------------------
Final Result: 4 Activities Selected (A0, A1, A3, A4)

Complexity Analysis:
- Time Complexity:
  - When end times are already sorted: O(n) — Single linear scan.
  - When end times are unsorted: O(n log n) — Due to sorting step.
- Space Complexity: O(n) — To store the `activities` array mapping index, start, and end pairs.
=================================================
*/
