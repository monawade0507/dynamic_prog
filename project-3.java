
class Project3
{
  static public int event = 9;
  static public int coordinate = 9;
  static public int [][] DP_table = new int[(2 * coordinate) + 1][event];

  // static public int event = 3;
  // static public int coordinate = 3;
  // static public int [][] DP_table = new int[(2*coordinate) + 1][event];

  /*
  astronomicalEvent = {1,2,3,4,5,6,7,8,9};
  aka for DP_table: astronomicalEvent = {0,1,2,3,4,5,6,7,8};
  astronomicalCoordinate = {1,-4,-1,5,-4,6,7,-2};
  aka for DP_table: astronomicalCoordinate = {8, 13, 10, 5,  4, 13, 3, 2, 11};
  */
  static public int [] astronomicalEvent =      {0, 1,  2,  3,  4,  5, 6, 7,  8};
  static public int [] astronomicalCoordinate = {8, 13, 10, 5,  4, 13, 3, 2, 11};

  // static public int [] astronomicalEvent      = {0, 1, 2};
  // static public int [] astronomicalCoordinate = {2, 7, 4};

  // static public int score = 0;
  // optimal solution = {1,3,6,9}

  public static void recur(int pos, int time, int score) {
    // build 2D array for DP

    // // base case:
    if (time > 9) { DP_table[pos][time] = 1; return; }
    else {
      int min, max, same = 0;
      // check min boundary
      if ( (pos - 1 >= 0) && (time - 1 >= 0) ) {
        min = DP_table[pos - 1][time - 1];
        if (min > score) score = min;
      }
      // check max boundary
      if ( (pos + 1 < ((2 * coordinate) + 1)) && (time - 1 >= 0) ) {
        max = DP_table[pos + 1][time - 1];
        if (max > score) score = max;
      }
      // check equal boundary
      if (time - 1 >= 0) {
        same = DP_table[pos][time - 1];
        if (same > score) score = same;
      }
      // by now, should have determined the highest score

      //test if the position is predicted event/coordinate
      for (int i = 0; i < event; i++) {
        if (time == astronomicalEvent[i] && pos == astronomicalCoordinate[i]) {
          score ++;
          System.out.println("astronomicalEvent found");
          System.out.println("  Event = " + time);
          System.out.println("  Coordinate = " + pos);
        }
      }

      // update visited locations
      DP_table[pos][time] = score;

      //move through the table and update scores
      if ( (pos + 1) < ((2 * coordinate) + 1) && (time + 1) < event ) { recur((pos + 1), (time + 1), score); }       // +1
      if ( (time + 1) < event ) {   recur(pos, (time + 1), score); }                                                 // 0
      if ( (pos - 1) >= 0 && (time + 1) < event ) { recur((pos - 1), (time + 1), score); }                           // -1



    }
  // end of recur
  }


 public static void main(String args[])
 {
     Project3 proj3 = new Project3();
     // starting point: time = 1 (aka according to DP_table = 0), coordinate = 1 (aka according to DP_table = 8)
     int start_time = 0;
     int start_pos = 8;
     proj3.recur(start_pos, start_time, 0);

     // print DP_table to make sure everything is working as designed
     for (int[] x : proj3.DP_table) {
       for (int y : x) {
         System.out.print( y + " ");
       }
       System.out.println();
     }

 // end of main
 }

// end of Project3 class
}
