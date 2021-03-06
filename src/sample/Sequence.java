package sample;

/**
 * Created by Robin on 25.5.2015.
 * reused class from 'Grundlagen der Bioinformatik'
 */
public class Sequence {

    private String sequenceName;
    private Nucleotides[] nuclSequence;

    public Sequence(String sequenceName, String sequence){
        this.setSequenceName(sequenceName);
        this.nuclSequence = convertStringToNuclSequence(sequence);
    }

    public Sequence(String sequenceName){
        this.setSequenceName(sequenceName);
    }


    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public void setNuclSequence(Nucleotides[] nuclSequence) {
        this.nuclSequence = nuclSequence;
    }

    /**
     * Converts a (string)sequence to the nucleotide Object (Nucleotides)
     * @param sequence
     */
    public static Nucleotides[] convertStringToNuclSequence(String sequence) {
        Nucleotides[] nuclSequence = new Nucleotides[sequence.length()];
        for(int i=0; i<sequence.length(); i++){
            nuclSequence[i] = new Nucleotides(i, sequence.charAt(i));
        }
        return nuclSequence;
    }

    /**
     *
     * @return Nucleotides[] containing the nucleotides of the sequence
     */
    public Nucleotides[] getNuclSequence() {
        return nuclSequence;
    }

    public Nucleotides getNucleotideAt(int i){
        return nuclSequence[i];
    }

    /**
     * Gives a subsequence in String represantation
     * @param start
     * @param end
     * @return
     */
    public String getNucleotideFromTo(int start, int end){
        String sequencePart = this.getNucleotideAt(start).getNucleotide() + "";
        for(int i= start+1; i<end; i++){
            sequencePart += this.getNucleotideAt(i).getNucleotide();
        }
        return sequencePart;
    }
}
