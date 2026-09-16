import java.util.ArrayList;
import java.util.Collections;

public class JobSequencingProblem {

    // Job representation
    static class Job {
        char id;
        int deadline;
        int profit;

        public Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    // Operation: Maximize profit by scheduling jobs before their deadlines
    public static void maxProfitJobSequence(ArrayList<Job> jobs) {
        // Step 1: Sort jobs in descending order of profit (Greedy Choice)
        Collections.sort(jobs, (a, b) -> b.profit - a.profit);

        // Step 2: Find maximum deadline to determine available time slots
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Step 3: Initialize time slot array (1-indexed based on unit time slots)
        // slot[t] stores the id of the job scheduled in slot [t-1, t]
        char[] slots = new char[maxDeadline + 1];
        boolean[] isOccupied = new boolean[maxDeadline + 1];

        int totalProfit = 0;
        int jobCount = 0;
        ArrayList<Character> scheduledJobs = new ArrayList<>();

        // Step 4: Schedule each job at the latest possible free slot <= deadline
        for (Job currJob : jobs) {
            for (int t = currJob.deadline; t > 0; t--) {
                if (!isOccupied[t]) {
                    isOccupied[t] = true;
                    slots[t] = currJob.id;
                    totalProfit += currJob.profit;
                    jobCount++;
                    scheduledJobs.add(currJob.id);
                    break; // Job scheduled; proceed to next highest profit job
                }
            }
        }

        // Print results
        System.out.println("Jobs selected: " + scheduledJobs);
        System.out.println("Total Jobs Scheduled: " + jobCount);
        System.out.println("Maximum Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(new Job('A', 4, 20));
        jobs.add(new Job('B', 1, 10));
        jobs.add(new Job('C', 1, 40));
        jobs.add(new Job('D', 1, 30));

        maxProfitJobSequence(jobs);
        // Output:
        // Jobs selected: [C, A]
        // Total Jobs Scheduled: 2
        // Maximum Profit: 60
    }
}

/*
==================== SUMMARY ====================

Problem: Job Sequencing Problem

Objective:
Given an array of jobs where every job has a deadline and an associated profit if completed 
before or on its deadline. Each job takes a single unit of time (1 slot). Maximize the total 
profit when only one job can be executed at a time.

Greedy Choice Strategy:
1. Sort by Profit:
   - Always consider highest-profit jobs first (sort in descending order of `profit`).
2. Delay Execution to the Latest Possible Slot:
   - For a selected job with deadline $d$, attempt to schedule it as late as possible 
     (at slot $d$, or $d-1, d-2, \dots, 1$).
   - Why? Scheduling as late as possible leaves earlier slots open for other jobs with tighter deadlines.

Algorithmic Breakdown:
1. Sort all jobs by `profit` in descending order.
2. Determine `maxDeadline` across all jobs to establish the slot timeline $[1 \dots \text{maxDeadline}]$.
3. Create a boolean tracking array `isOccupied[]` of size `maxDeadline + 1`.
4. For each job:
   - Scan backwards from `t = job.deadline` down to `1`.
   - If `!isOccupied[t]`:
     - Assign the job to slot `t`.
     - Mark `isOccupied[t] = true`.
     - Add `job.profit` to running total and break.

-------------------------------------------------

Timeline & Slot Allocation Visual

Input:
  Job A: deadline = 4, profit = 20
  Job B: deadline = 1, profit = 10
  Job C: deadline = 1, profit = 40
  Job D: deadline = 1, profit = 30

Step 1: Sort by Profit (Descending):
  1. Job C: deadline = 1, profit = 40
  2. Job D: deadline = 1, profit = 30
  3. Job A: deadline = 4, profit = 20
  4. Job B: deadline = 1, profit = 10

Timeline Slots (Max Deadline = 4):
  Slot 1: [0 - 1]
  Slot 2: [1 - 2]
  Slot 3: [2 - 3]
  Slot 4: [3 - 4]

Step 2: Greedily Assign Slots:
  - Job C (deadline 1): Slot 1 is free -> Place C in Slot 1.
    Timeline: [ C |   |   |   ]

  - Job D (deadline 1): Slot 1 is full, no earlier slot -> Cannot schedule (Skip).

  - Job A (deadline 4): Check slot 4 -> Slot 4 is free -> Place A in Slot 4.
    Timeline: [ C |   |   | A ]

  - Job B (deadline 1): Slot 1 is full, no earlier slot -> Cannot schedule (Skip).

Selected Jobs: C, A
Total Profit: 40 + 20 = 60

-------------------------------------------------

Step-by-Step Trace Table

----------------------------------------------------------------------------------------------------------------------
Sorted Job | Deadline | Profit | Target Slot Probe | Slot Assigned | Slots Occupied  | Running Profit
----------------------------------------------------------------------------------------------------------------------
Job C      | 1        | 40     | Slot 1            | Slot 1        | [1]             | 40
Job D      | 1        | 30     | Slot 1 (Occupied) | None (Skipped)| [1]             | 40
Job A      | 4        | 20     | Slot 4            | Slot 4        | [1, 4]          | 40 + 20 = 60
Job B      | 1        | 10     | Slot 1 (Occupied) | None (Skipped)| [1, 4]          | 60
----------------------------------------------------------------------------------------------------------------------
Final Result: 2 Jobs (C and A) Scheduled | Maximum Profit = 60

Complexity Analysis:
- Time Complexity : O(n log n + n * m)
  - Sorting takes $O(n \log n)$, where $n$ is the number of jobs.
  - Slot searching takes $O(n \cdot m)$ in the worst case, where $m$ is the maximum deadline (can be optimized to $O(n \log m)$ using Disjoint Set Union / DSU).
- Space Complexity: O(m) — For the slot occupation tracker array of size `maxDeadline`.
=================================================
*/
