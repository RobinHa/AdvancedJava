package sample;

/**
 * Created by Robin on 03.11.2015.
 */
public enum Nucleotide {
    Adenin  ('A', 'a', "Purin", "BOTH"),
    Guanin  ('G', 'g', "Purin", "BOTH"),
    Cytosin ('C', 'c', "Pyrimidin", "BOTH"),
    Thymin  ('T', 't', "Pyrimidin", "DNA"),
    Uracil  ('U', 'u', "Pyrimidin", "RNA");

    private final char charUpperRepresentation;
    private final char charLowerRepresentation;
    private final String PurinPyrimidin; // Purin or Pyrimidin
    private final String dnaOrRna; // is base of DNA or RNA

    Nucleotide(char upper, char lower, String PurinOrPyrimidin, String dnaOrRna) {
        this.charUpperRepresentation = upper;
        this.charLowerRepresentation = lower;
        this.PurinPyrimidin = PurinOrPyrimidin;
        this.dnaOrRna = dnaOrRna;
    }

    public char getCharUpperRepresentation() {
        return charUpperRepresentation;
    }

    public char getCharLowerRepresentation() {
        return charLowerRepresentation;
    }

    public String getPurinPyrimidin() {
        return PurinPyrimidin;
    }

    public String getDnaOrRna() {
        return dnaOrRna;
    }

    public Nucleotide convertToRNA(){
        if(this == Thymin) return Uracil;
        return this;
    }

    public Nucleotide convertToDNA(){
        if(this == Uracil) return Thymin;
        return this;
    }



    public static Nucleotide getBaseFromCharRep(char symbol){
        switch (symbol){
            case 'A':
            case 'a':
               return Adenin;
            case 'T':
            case 't':
                return Thymin;
            case 'C':
            case 'c':
                return Cytosin;
            case 'G':
            case 'g':
                return Guanin;
            case 'U':
            case 'u':
                return Uracil;
            default:
                break;
        }
        return null;
    }
}
