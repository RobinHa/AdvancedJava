package sample;

/**
 * Created by Robin on 18.10.2015.
 */
public class Nucleotides {

    private int position;
    private char nucleotide;

    /**
     * Constructer for a Nucleotide in a sequence.
     * @param position of the base
     * @param nucleotide
     */
    public Nucleotides(int position, char nucleotide){
        this.position = position;
        this.nucleotide = nucleotide;
    }

    public char getNucleotide() {
        return nucleotide;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setNucleotide(char nucleotide) {
        this.nucleotide = nucleotide;
    }
}
