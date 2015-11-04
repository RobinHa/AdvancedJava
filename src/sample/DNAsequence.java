package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * Created by Robin on 28.10.2015.
 */
public class DNAsequence {

    private String stringSequence;
    private ArrayList<Nucleotide> sequence;
    private boolean rnaBoolean;

    /**
     * Constuctor, which checks if the input is RNA or DNA, if not it says so on the console
     * @param seq
     */
    public DNAsequence(String seq){
        if(Pattern.matches("(A|G|C|T|a|g|c|t)*", seq)){
            this.DNAsequenceHelper(seq, false);
        }else if(Pattern.matches("(A|G|C|U|u|g|c|a)*", seq)){
            this.DNAsequenceHelper(seq, true);
        }else if(Pattern.matches("(A|G|C|T|a|g|c|t|U|u)*", seq)){
            System.err.print("Your input sequence is not a DNA sequence nor a RNA sequence, " +
                    "there is Uracil and Thymin in your sequence");
        }else{
            System.err.print("Your input sequence is not a DNA Sequence. Allowed chars are 'a,c,g,t,A,C,G,T,U,u'");
        }
    }

    /**
     * a helper for the Constuctor
     * @param seq
     * @param isDNA
     */
    private void DNAsequenceHelper(String seq, boolean isDNA){
            this.stringSequence = seq;
            this.makeBaseSequence(seq);
            this.rnaBoolean = isDNA;
    }


    public String getStringSequence() {
        return stringSequence;
    }

    public void setStringSequence(String stringSequence) {
        this.stringSequence = stringSequence;
    }

    public ArrayList<Nucleotide> getSequence() {
        return sequence;
    }

    public void setSequence(ArrayList<Nucleotide> sequence) {
        this.sequence = sequence;
    }

    public boolean isRnaBoolean() {
        return rnaBoolean;
    }

    public void setRnaBoolean(boolean rnaBoolean) {
        this.rnaBoolean = rnaBoolean;
    }

    /**
     * creates a Nucleotide array from a given sequence in String format
     * @param seq
     */
    private void makeBaseSequence(String seq) {
        ArrayList<Nucleotide> sequenceList= new ArrayList<Nucleotide>();
        char[] seqArray = seq.toCharArray();
        for(int i=0; i< seqArray.length; i++){
            sequenceList.add(sample.Nucleotide.getBaseFromCharRep(seqArray[i]));
        }
        sequence = sequenceList;
    }

    public void makeRNAfromDNA() {
        String seq = "";
        for (Nucleotide b : sequence) {
            if (b == sample.Nucleotide.Thymin) {
                b = sample.Nucleotide.Uracil;
            }
            seq += b.getCharUpperRepresentation();
        }
        stringSequence = seq;
        setRnaBoolean(true);
    }

    public void makeComplementary(boolean toRNA){
        setRnaBoolean(toRNA);
        String seq = "";
        for(Nucleotide b : sequence){
            switch (b){
                case Thymin:
                case Uracil: b = sample.Nucleotide.Adenin; break;
                case Adenin: b = (toRNA ? sample.Nucleotide.Uracil : sample.Nucleotide.Thymin); break;
                case Guanin: b = sample.Nucleotide.Cytosin; break;
                case Cytosin: b = sample.Nucleotide.Guanin; break;
                default: break;
            }
            seq += b.getCharUpperRepresentation();
        }
        setStringSequence(seq);
    }

    public void reverseSequence(){
        StringBuilder builder = new StringBuilder(stringSequence);
        stringSequence = builder.reverse().toString();
        Collections.reverse(sequence);
    }

    /**
     * Computes the Percentages of GC in a sequence
     * @return double
     */
    public double getGCContent(){
        int temp = 1000;
        int length = sequence.size();
        int GCcount = 0;
        for(Nucleotide b : sequence){
            if(b == sample.Nucleotide.Guanin || b == sample.Nucleotide.Cytosin)
                GCcount++;
        }
        return (length==0?0.0:((temp*GCcount)/length)/10.);
    }

    /**
     * removes all non-nucleotide symbols in a String
     * @param input
     * @return
     */
    public static String makeLegitSequence(String input){
        return input.replaceAll("[^AGTCagtc]", "");
    }

}
