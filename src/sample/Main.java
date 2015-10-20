package sample;

public class Main{


    public static void main(String[] args) {

        System.out.print("");


        FastaReader file1 = new FastaReader("exemplary_RNA_aln.fa");
        CommandLine.printSequenceAlignment(1, 119, file1.getSequences());

    }
}
