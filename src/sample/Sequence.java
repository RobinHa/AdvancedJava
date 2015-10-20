package sample;

/**
 * Created by Robin on 25.5.2015.
 * reused class from 'Grundlagen der Bioinformatik'
 */
public class Sequence {

    private int sequenceNumber;
    private String sequenceName;
    private String sequence;
    private Nucleotides[] nuclSequence;

    public Sequence(int sequenceNumber, String sequenceName, String sequence){
        this.setSequenceNumber(sequenceNumber);
        this.setSequenceName(sequenceName);
        Nucleotides[] nuclSequence = new Nucleotides[sequence.length()];
        for(int i=0; i<sequence.length(); i++){
           nuclSequence[i] = new Nucleotides(i + 1, sequence.charAt(i));
        }
        this.nuclSequence = nuclSequence;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
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
            nuclSequence[i] = new Nucleotides(i + 1, sequence.charAt(i));
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
}
