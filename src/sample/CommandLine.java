package sample;

import java.util.LinkedList;

/**
 * Created by Robin on 18.10.2015.
 */
public class CommandLine {

    //the length of the displayed sequence per line
    private static int displayFrame = 60;

    public static void printSequenceAlignment(int start, int end, LinkedList<Sequence> seq){
        int currentPosition = start;
        String output = "";
        while(currentPosition < end){
            output += currentPosition + "\t" + currentPosition+displayFrame ;
            for(Sequence s: seq){
                output += "\n" + s.getSequenceName();
                for(int i = currentPosition; i<end && i<(currentPosition + displayFrame); i++){
                    output += s.getNucleotideAt(i).getNucleotide();
                }
            }
            currentPosition += displayFrame;
        }
        System.out.print(output);
    }

}
