package org.sssihl.solution;
import java.util.HashMap;
/** Write a Java program to find the shortest path for a bot in a 2D grid.

  * 0 - Open path
  * 1 - Blockage (cannot pass)
  * 2 - Goal (destination)

  * The bot starts at a given coordinate and must reach the goal using only up, down, left, right moves. The program should print the sequence of coordinates that form the shortest path. If no path exists, display "No path found".*/

/**Example Input 1:
  * Size : 5x5
  * Start: 0,0
  * Goal : 1x4
  * The program autogenerates this 2D array based on the specifications given

  *  0 0 1 0 0
  *  1 0 1 0 2
  *  0 0 0 0 1
  *  0 1 1 0 0
  *  0 0 0 0 0

  *  output: [(0,0), (0,1), (1,1), (2,1), (2,2), (2,3), (1,3), (1,4)]
  */

/** Example Input 2:
 *  Size : 4x4
 *  Start: 2x0
 *  Goal : 0,3
 * 
 * The program autogenerates this 2D array based on the specifications given
 * 
 * 0 1 0 2
 * 0 1 0 1
 * 0 0 0 1
 * 1 1 0 0
 * 
 * output: [(2,0), (2,1), (2,2), (1,2), (0,2), (0,3)]
 * */

/**
 * Compile : javac org/sssihl/Main.java org/sssihl/solution/Solution.java org/sssihl/generator/AutoGenerator*.java
 * Run     : java org.sssihl.Main 
 */

public class Solution {
    public static int REGNUMBER = 124407;
    
    public int[][] run (int[] start, int[] end, int[][] grid)
    {
        int len = grid.length;
        int[][] res = new int[len * len][2];
        
        // dll acts as queue node
        class dll 
        {
            public dll prev;
            public dll next;
            public int index1;
            public int index2;
        }
        
        // ll acts as BFS level tracker (each ll node holds one dll list of nodes at that depth)
        class ll 
        {
            public ll l_prev;
            public int l_index1;
            public int l_index2;
            public dll nxt;
            public ll l_next;
        }

        // Movement directions
        int[] a_array = {-1, 1, 0, 0};
        int[] b_array = {0, 0, 1, -1};

        HashMap<String, Boolean> visited = new HashMap<>();
        HashMap<String, String> parent = new HashMap<>();

        // Initialize level 0
        ll head_ll = new ll();
        ll tail_ll = head_ll;
        dll start_dll = new dll();
        start_dll.index1 = start[0];
        start_dll.index2 = start[1];
        head_ll.nxt = start_dll;

        visited.put(start[0] + "," + start[1], true);
        boolean found = false;
        int goal_a = -1, goal_b = -1;

        // BFS using ll levels
        while (head_ll != null) 
        {
            dll cur = head_ll.nxt;
            ll next_level = new ll();
            dll next_dll_head = null, next_dll_tail = null;

            while (cur != null) 
            {
                int a = cur.index1;
                int b = cur.index2;

                if (grid[a][b] == 2) 
                {
                    found = true;
                    goal_a = a;
                    goal_b = b;
                    break;
                }

                for (int i = 0; i < 4; i++) 
                {
                    int na = a + a_array[i];
                    int nb = b + b_array[i];
                    String key = na + "," + nb;

                    if (na >= 0 && nb >= 0 && na < len && nb < len && !visited.containsKey(key)) 
                    {
                        if (grid[na][nb] == 0 || grid[na][nb] == 2) 
                        {
                            visited.put(key, true);
                            dll newNode = new dll();
                            newNode.index1 = na;
                            newNode.index2 = nb;
                            if (next_dll_head == null) 
                            {
                                next_dll_head = newNode;
                                next_dll_tail = newNode;
                            } 
                            else 
                            {
                                next_dll_tail.next = newNode;
                                newNode.prev = next_dll_tail;
                                next_dll_tail = newNode;
                            }
                            parent.put(key, a + "," + b);
                        }
                    }
                }

                cur = cur.next;
            }

            if (found) 
            {
              break;
            }
            
            if (next_dll_head != null) 
            {
                next_level.nxt = next_dll_head;
                tail_ll.l_next = next_level;
                next_level.l_prev = tail_ll;
                tail_ll = next_level;
            }
            head_ll = head_ll.l_next;
        }

        if (!found) 
        {
            System.out.println("No path found");
            return new int[0][0];
        }

        // reconstruct shortest path
        int x = 0;
        int a = goal_a, b = goal_b;

        while (true) {
            res[x][0] = a;
            res[x][1] = b;
            x++;
            String key = a + "," + b;
            if (!parent.containsKey(key)) 
            {
               break;
            }
            String[] p = parent.get(key).split(",");
            a = Integer.parseInt(p[0]);
            b = Integer.parseInt(p[1]);
        }

        // reverse the result (start → goal)
        int[][] finalRes = new int[x][2];
        for (int i = 0; i < x; i++) {
            finalRes[i][0] = res[x - i - 1][0];
            finalRes[i][1] = res[x - i - 1][1];
        }

        return finalRes;
    }
}

