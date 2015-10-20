package sample;


/**
 * Author: Robin Harmening
 */
public class Main{


    public static void main(String[] args) {
        System.out.print("Assignment 1 - Robin Harmening - 3844234");

        FastaReader file1 = new FastaReader(args[0]);
        int sequenceLength = file1.getSequences().get(1).getNuclSequence().length + 1;
        CommandLine.printSequenceAlignment(1, sequenceLength, file1.getSequences());

    }
}
