package sample;

import java.util.LinkedList;

/**
 * Created by Robin on 18.10.2015.
 */
public class CommandLine {

    //the length of the displayed sequence per line
    private static int sequenceDisplayFrame = 50;
    private static int nameDisplayLenght = 23;2

    public static void printSequenceAlignment(int start, int end, LinkedList<Sequence> seq){
        int currentPosition = start-1;
        String output = "";

        while(currentPosition < end-1){
            printSequenceNumbering(currentPosition,currentPosition+sequenceDisplayFrame);
            for(Sequence s: seq){
                output = "";
                for(int i = currentPosition; i<end-1 && i<(currentPosition + sequenceDisplayFrame); i++){
                    output += s.getNucleotideAt(i).getNucleotide();
                }
                System.out.format("\n%-" + nameDisplayLenght
                        + "s \t%-" + sequenceDisplayFrame
                        + "s", s.getSequenceName(), output);
            }
            currentPosition += sequenceDisplayFrame;
        }
    }


    /**
     * prints the base position above the alignment
     * @param start
     * @param end
     */
    private static void printSequenceNumbering(int start, int end){
        System.out.printf("\n%" + nameDisplayLenght
                + "s \t%-"+ (sequenceDisplayFrame-1)
                + "d%d", "", start+1, end);
    }
}
