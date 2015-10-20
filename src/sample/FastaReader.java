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
     *
     * @param file the file name
     */
    public void readFile(String file){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int lineNum = 1;
            String currLine = "";
            Sequence e= new Sequence(0, "", "");
            while((currLine = br.readLine()) != null){
                if(Pattern.matches(">.*", currLine)){
                    e = new Sequence(lineNum, currLine, "");
                    Sequences.add(e);
                    lineNum++;
                }
                else if((e !=null) && Pattern.matches(".+", currLine) ){
                    e.setSequence(e.getSequence()+currLine);
                    e.setNuclSequence(Sequence.
                            convertStringToNuclSequence(e.getSequence()));
                }
            }
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
