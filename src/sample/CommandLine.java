package sample;

import javafx.scene.text.Text;

import java.util.LinkedList;

/**
 * Created by Robin on 18.10.2015.
 */
public class CommandLine {

    //the length of the displayed sequence per line
    private static int sequenceDisplayFrame = 50;
    //length of the sequence name per line
    private static int nameDisplayLenght = 23;


    /**
     * formats and prints the MSA
     * TODO outsource some loops into new methods
     * @param start
     * @param end
     * @param seq
     */
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
     * Formats and returns an Text object for an MSAlignment
     * @param numbering
     * @param alignment
     * @param headers
     * @param seq
     * @return
     */
    public static Text getSequenceAlignment(boolean headers, boolean alignment,
                                              boolean numbering,LinkedList<Sequence> seq) {
        int maxNameLength = headers ? getSequenceNameMaxLength(seq) + 1 : 0;
        int maxLineLength = 100;
        int lastPositionOfSequence = seq.get(0).getNuclSequence().length % (maxLineLength - maxNameLength);
        int nucleotidesPerLine = maxLineLength - maxNameLength;

        Text alignmentText = new Text();
        int currentPosition = 0;
        int maxPosition = seq.get(0).getNuclSequence().length-1;
        while (currentPosition < maxPosition) {
            if (numbering) {
                String appand = getSequenceNumbering(currentPosition,
                        (maxPosition > (currentPosition + nucleotidesPerLine)
                                ? nucleotidesPerLine : lastPositionOfSequence),
                        maxNameLength);
                alignmentText.setText(alignmentText.getText() + "\n"
                        + appand);
            }
            for (Sequence s : seq) {
                alignmentText.setText(alignmentText.getText() + "\n");
                if (headers) {
                    alignmentText.setText(alignmentText.getText() + s.getSequenceName());
                }
                if (alignment) {
                    alignmentText.setText((alignmentText.getText()
                            + s.getNucleotideFromTo(currentPosition,
                            currentPosition + ((currentPosition + nucleotidesPerLine) > maxPosition
                                    ? lastPositionOfSequence : nucleotidesPerLine))));
                }
            }
            currentPosition += nucleotidesPerLine;
        }
        return alignmentText;
    }


    /**
     * computes the positioning of the Numbers above the MSA
     * @param currPos
     * @param nuclAmountLine
     * @param offSet
     * @return
     */
    private static String getSequenceNumbering(int currPos, int nuclAmountLine, int offSet) {

        int end = currPos + nuclAmountLine;
        String s = "";
        for(int i=0; i<offSet; i++) s+= " ";
        s += currPos;
        int numberLength = ("" + currPos).length() + ("" + end).length() ;
        for(int i=0; i<nuclAmountLine-numberLength; i++){
            s += " ";
        }
        s += (end-("" + end).length());
        
        return s;
    }

    public static int getSequenceNameMaxLength(LinkedList<Sequence> seq){
        int maxLength = 1;
        for(Sequence s: seq){
            int i= s.getSequenceName().length();
            if(i > maxLength){
                maxLength = i;
            }
        }
        return maxLength;
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
