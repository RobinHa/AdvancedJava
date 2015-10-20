package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * Created by Robin on 18.10.2015.
 */
public class FastaReader {

    //Attributes
    private String filePath;
    private LinkedList<Sequence> Sequences  = new LinkedList<Sequence>();

    //Constucts
    public FastaReader(String path){
        this.filePath=path;
        readFile(path);
    }

    /**
     *  Reads and converts a fastA file into a Sequence object
     * @param file the file name
     */
    public void readFile(String file){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String currLine = "";
            String currentSeqName = ">Not a sequence";
            String currentSequence = null;
            boolean firstSequence = true;
            while((currLine = br.readLine()) != null){
                if(Pattern.matches(">.*", currLine) && firstSequence){
                    currentSeqName = currLine;
                    firstSequence = false;
                }
                else if(Pattern.matches(">.*", currLine) && !firstSequence){
                    Sequences.add(new Sequence(currentSeqName, currentSequence));
                    currentSeqName = currLine;
                    currentSequence = null;
                }
                else if(currentSequence == null && Pattern.matches(".+", currLine) ){
                    currentSequence = currLine;
                }
                else if((currentSequence !=null) && Pattern.matches(".+", currLine) ){
                    currentSequence += currLine;
                }
            }
            Sequences.add(new Sequence(currentSeqName, currentSequence));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public LinkedList<Sequence> getSequences() {
        return Sequences;
    }

    public void setSequences(LinkedList<Sequence> sequences) {
        Sequences = sequences;
    }
}
