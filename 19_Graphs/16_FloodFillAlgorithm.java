public class FloodFillAlgorithm {

    // Helper method: Recursive DFS traversal to fill connected pixels[cite: 17]
    public static void dfs(int[][] image, int r, int c, int originalColor, int newColor) {
        // Base Condition: Check boundaries and valid color match[cite: 17]
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != originalColor) {
            return;
        }

        // Color current cell with the target new color[cite: 17]
        image[r][c] = newColor;

        // Recurse in 4 directions: Up, Down, Left, Right[cite: 17]
        dfs(image, r - 1, c, originalColor, newColor); // Up
        dfs(image, r + 1, c, originalColor, newColor); // Down
        dfs(image, r, c - 1, originalColor, newColor); // Left
        dfs(image, r, c + 1, originalColor, newColor); // Right
    }

    // ==========================================================
    // LeetCode 733: Flood Fill Algorithm -> Time Complexity: O(m * n)[cite: 17]
    // ==========================================================
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];

        // Guard: If original color is already equal to target color, return immediately[cite: 17]
        if (originalColor != color) {
            dfs(image, sr, sc, originalColor, color);
        }

        return image;
    }

    public static void main(String[] args) {
        // Input example matching slide[cite: 17]
        int[][] image = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };

        int sr = 1;
        int sc = 1;
        int color = 2;

        System.out.println("Original Image Grid:");
        printGrid(image);

        int[][] ans = floodFill(image, sr, sc, color);

        System.out.println("\nTransformed Image Grid after Flood Fill:");
        printGrid(ans);
        /*
          Expected ans grid[cite: 17]:
          [2, 2, 2]
          [2, 2, 0]
          [2, 0, 1]
        */
    }

    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            System.out.print("[ ");
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println("]");
        }
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 43 — Flood Fill Algorithm (LeetCode 733 - Easy)[cite: 17]

Problem Description[cite: 17]:
- Given an $m \times n$ integer grid where `image[i][j]` represents the pixel value[cite: 17].
- Given starting coordinates `(sr, sc)` and a replacement `color`[cite: 17].
- Goal: Perform a flood fill starting at `image[sr][sc]`, changing its color and all connected pixels (sharing the original pixel value) across 4 cardinal directions (Up, Down, Left, Right) to `color`[cite: 17].
- Pixels with differing values or disconnected identical values (like the isolated `1` at `image[2][2]`) remain untouched[cite: 17].

Traversal Strategy (Implicit Graph via DFS):
1. Identify the starting pixel's color: `originalColor = image[sr][sc]`[cite: 17].
2. Critical Infinite Loop Guard:
   - If `originalColor == color`, immediately return the grid[cite: 17].
   - If this check is omitted, recursion re-colors cells to their same color infinitely, resulting in a StackOverflowError[cite: 17].
3. For the current coordinate `(r, c)`:
   - Verify boundary conditions: `0 <= r < m` and `0 <= c < n`.
   - Verify color match: `image[r][c] == originalColor`.
   - Update `image[r][c] = color`[cite: 17].
   - Spread recursively to four neighboring cells: `(r-1, c)`, `(r+1, c)`, `(r, c-1)`, `(r, c+1)`[cite: 17].

-------------------------------------------------

Step-by-Step Grid Transformation[cite: 17]

Initial State (sr = 1, sc = 1, target color = 2)[cite: 17]:
   [ 1,  1,  1 ]
   [ 1, (1), 0 ]   <-- start at center (1, 1)[cite: 17]
   [ 1,  0,  1 ]

Connected Component Walk:
- Pixel (1, 1) changes: 1 -> 2
- Up neighbor (0, 1) changes: 1 -> 2
  - (0, 1) reaches (0, 0) and (0, 2): both change to 2
- Down neighbor (2, 1) has value 0 -> stops branch
- Left neighbor (1, 0) changes: 1 -> 2
  - (1, 0) reaches (2, 0): changes to 2
- Pixel at bottom right (2, 2) has value 1, but is blocked by 0s -> remains unchanged[cite: 17]

Final Output State[cite: 17]:
   [ 2,  2,  2 ]
   [ 2,  2,  0 ]
   [ 2,  0,  1 ][cite: 17]

-------------------------------------------------

Complexity Analysis:
- Time Complexity : O(m * n) — In the worst-case scenario (e.g., all grid cells share the same color), every cell is visited and modified exactly once[cite: 17].
- Space Complexity: O(m * n) — Auxiliary call stack space in the worst case (a continuous snake-like path spanning across the grid)[cite: 17].
=================================================
*/
