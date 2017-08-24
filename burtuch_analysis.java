import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Kiwi
 * Date: 5/29/14
 * Time: 2:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class burtuch_analysis {
    public static void main (String [] args) throws IOException{
        String dataloc = args[0];
      //  String dataloc = "C:\\Documents and Settings\\HGSC\\IdeaProjects\\Burtuch_analysis\\data\\first_1k.pslx";
        File fileloc = new File(dataloc);
        Scanner pslxin = new Scanner(fileloc);
        pslxin.nextLine();
        pslxin.nextLine();
        pslxin.nextLine();
        pslxin.nextLine();
        pslxin.nextLine();
        int[][] results = new int[6][7];
        int i = 0;
        //while (i<10){
        while (pslxin.hasNext())  {
            String l = pslxin.nextLine();
//            System.out.println(l);
            String[] lsplit=l.split("\t");
         //   System.out.println(lsplit[18] + " " + lsplit[20]);
            String[] blocksize = lsplit[18].split(",");
            String[] blockstartstart = lsplit[20].split(",");
        //    System.out.println("block length = "+blocksize.length);
            int j=0;
            int previous_end=0;
            int first=0;
            while (j<blocksize.length){
              //  Boolean bothsides=false; //1
           //     Boolean upstream=false; //2
          //      Boolean downstream=false; //3
           //     Boolean internal=false; //4
           //     Boolean insertions=false; //5
                int this_end = Integer.parseInt(blocksize[j]) + Integer.parseInt(blockstartstart[j]);
                int this_start = Integer.parseInt(blockstartstart[j]);

                if(first==0){
                    previous_end=this_end;
                    first++;
                    j++;
                    continue;
                }

                int start = previous_end;
                int end= this_start;
//                System.out.println("end " + end +"  Start" + start);

                if (start==end){
  //                  System.out.println("insertion");
                    //insertions=true;
                    int insert_size = Integer.parseInt(lsplit[5]);
                    if (insert_size>=6){
                        insert_size=6;
                    }
                  //  System.out.println(l);
        //            System.out.println(insert_size);
                    results[5][insert_size]=results[5][insert_size]+1;

                    //Needs more stuff here to get insertion size

                    j++;
                    continue;
                }

                int size = end-start;
                if (size >= 6){
                    size=6;
                }
    //            System.out.println(size);
                if(start<=49 && end>=56){
         //           bothsides =true;
    //                System.out.println("both");
                    results[1][size]=results[1][size]+1;
                }
                else if (start<50 && end >=50 && end <= 55){
         //           upstream =true;
      //              System.out.println("upstream");
                    results[2][size]=results[2][size]+1;
                }
                else if (start>=50 && start <= 55 && end > 55) {
        //            downstream =true;
        //            System.out.println("downstream");
                    results[3][size]=results[3][size]+1;
                }
                else  if (start>=50 && end <=55){
          //          internal=true;
          //          System.out.println("internal");
                    results[4][size]=results[4][size]+1;
                }



       //         System.out.println("The end is " + end);
                j++;
            }

            i++;
        }
        System.out.println("Final results");
        System.out.println("Total_tested = " + i);
        System.out.println("Total Bothsides = " + (results[1][1]+results[1][2]+results[1][3]+results[1][4]+results[1][5]+results[1][6]));
        System.out.println("1BP del bothsides = " + results[1][1]);
        System.out.println("2BP del bothsides = " + results[1][2]);
        System.out.println("3BP del bothsides = " + results[1][3]);
        System.out.println("4BP del bothsides = " + results[1][4]);
        System.out.println("5BP del bothsides = " + results[1][5]);
        System.out.println("6 or more BP del bothsides = " + results[1][6]);
        System.out.println();

        System.out.println("Total upstream = " + (results[2][1]+results[2][2]+results[2][3]+results[2][4]+results[2][5]+results[2][6]));
        System.out.println("1BP del upstream = " + results[2][1]);
        System.out.println("2BP del upstream = " + results[2][2]);
        System.out.println("3BP del upstream = " + results[2][3]);
        System.out.println("4BP del upstream = " + results[2][4]);
        System.out.println("5BP del upstream = " + results[2][5]);
        System.out.println("6 or more BP del upstream = " + results[2][6]);
        System.out.println();

        System.out.println("Total downstream = " + (results[3][1]+results[3][2]+results[3][3]+results[3][4]+results[3][5]+results[3][6]));
        System.out.println("1BP del downstream = " + results[3][1]);
        System.out.println("2BP del downstream = " + results[3][2]);
        System.out.println("3BP del downstream = " + results[3][3]);
        System.out.println("4BP del downstream = " + results[3][4]);
        System.out.println("5BP del downstream = " + results[3][5]);
        System.out.println("6 or more BP del downstream = " + results[3][6]);
        System.out.println();

        System.out.println("Total internal = " + (results[4][1]+results[4][2]+results[4][3]+results[4][4]+results[4][5]+results[4][6]));
        System.out.println("1BP del internal = " + results[4][1]);
        System.out.println("2BP del internal = " + results[4][2]);
        System.out.println("3BP del internal = " + results[4][3]);
        System.out.println("4BP del internal = " + results[4][4]);
        System.out.println("5BP del internal = " + results[4][5]);
        System.out.println("6 or more BP del internal = " + results[4][6]);
        System.out.println();

        System.out.println("Total insertions = " + (results[5][1]+results[5][2]+results[5][3]+results[5][4]+results[5][5]+results[5][6]));
        System.out.println("1BP insertions = " + results[5][1]);
        System.out.println("2BP insertions = " + results[5][2]);
        System.out.println("3BP insertions = " + results[5][3]);
        System.out.println("4BP insertions = " + results[5][4]);
        System.out.println("5BP insertions = " + results[5][5]);
        System.out.println("6 or more BP del internal = " + results[5][6]);
        System.out.println();

    }
}
