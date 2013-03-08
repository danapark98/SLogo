package control;

import instructions.BaseInstruction;
import java.util.ArrayList;
import java.util.List;
import exceptions.IllegalInstructionException;


/**
 * Handles preparsing of the user input. This creates a form that the Parser
 * class can read in correctly.
 * 
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class Preparser {
    private Environment myEnvironment;

    public Preparser (Environment environment) {
        myEnvironment = environment;
    }

    private class ReturnValues {
        public List<String> list;
        public int counterChange;

        public ReturnValues (List<String> rest, int counter) {
            list = rest;
            counterChange = counter;
        }
    }

    /**
     * Takes user input, converts to lower case so that case does not matter,
     * removes lines starting with a print indicator message, and add brackets
     * so that the parser knows where arguments start and end.
     * 
     * @param s original user input
     * @return a string that is easier for the Parser to read in
     * @throws IllegalInstructionException if instruction not recognized
     */
    public String preParse (String s) throws IllegalInstructionException {
        String stepZero = s.toLowerCase();
        String stepOne = removeResultIndicators(stepZero);
        String stepTwo = addBrackets(stepOne);
        return stepTwo;
    }

    /**
     * Remove the print indicator string and the rest of the line that it is on
     * from the provided user input.
     * 
     * This allows the user to copy and paste from the command history and
     * messages that are printed back to the user will not be parsed.
     * 
     * @param s user input
     * @return string without printed messages
     */
    private String removeResultIndicators (String s) {
        String[] lines = s.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            if (!line.startsWith(Controller.PRINT_INDICATOR)) {
                sb.append(line);
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * Takes original string of commands and adds list notations to make it
     * easier to parse later.
     * 
     * @param s original commands
     * @return commands with brackets added
     */
    private String addBrackets (String s) throws IllegalInstructionException {
        List<String> wordsList = createListFromString(s);
        if (wordsList.size() < 3) {
            return s;
        }

        // the second argument should just be arbitrarily large, so that it 
        // can read every instruction provided.  the argument for the recursive
        // method only has a context when called by itself
        ReturnValues rv = recurse(wordsList, Integer.MAX_VALUE);
        wordsList = rv.list;

        return createStringFromList(wordsList, 0, wordsList.size());
    }

    private ReturnValues recurse (List<String> wordsList, int argCount)
                                                                       throws IllegalInstructionException {
        int counter = 0;
        for (int i = 0; i < argCount; i++) {
            if (counter >= wordsList.size()) {
                break;
            }
            String command = wordsList.get(counter);
            if (command.equals(Parser.BEGINNING_OF_LIST)) {
                counter++;
                int indexOfRightBracket = findRightBracket(wordsList, counter);
                String s = createStringFromList(wordsList, counter, indexOfRightBracket);
                String sWithBrackets = addBrackets(s);
                List<String> insideList = createListFromString(sWithBrackets);
                counter = insertList(insideList, wordsList, counter);
                counter++;
            }
            else if (getArgumentCount(command) == -1) {
                counter++;
            }
            else {
                wordsList.add(counter, Parser.BEGINNING_OF_LIST);
                counter++;
                List<String> restOfList = createRestOfList(wordsList, counter);
                ReturnValues rv = recurse(restOfList, getArgumentCount(command));
                counter += rv.counterChange;
                wordsList.addAll(rv.list);
                counter++;
                wordsList.add(counter, Parser.END_OF_LIST);
                counter++;
            }
        }
        return new ReturnValues(wordsList, counter);
    }

    /**
     * Inserts insideList into wordsList at the index counter.
     * 
     * Returns the new counter after insertion.
     */
    private int insertList (List<String> insideList, List<String> wordsList, int counter) {
        wordsList.addAll(counter, insideList);
        return counter + insideList.size();
    }

    private List<String> createListFromString (String s) {
        String[] words = s.split("\\s+");
        List<String> wordsList = new ArrayList<String>();
        for (String word : words) {
            wordsList.add(word);
        }
        return wordsList;
    }

    /**
     * Creates a string from the entries in wordsList starting at startIndex 
     * (inclusive) and ending at endIndex (exclusive) and removes those entries
     * from the wordsList
     */
    private String createStringFromList (List<String> wordsList,
                                         int startIndex,
                                         int endIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            String str = wordsList.get(i);
            sb.append(str);
            sb.append(" ");
        }
        for (int i = startIndex; i < endIndex; i++) {
            wordsList.remove(startIndex);
        }
        return sb.toString();
    }

    //TODO: refactor repetitive code here and the Parser's parseList()
    private int findRightBracket (List<String> wordsList, int counter) {
        String str;
        int counterBracket = 1;
        while (counterBracket != 0) {
            str = wordsList.get(counter);
            if (str.equals(Parser.BEGINNING_OF_LIST)) {
                counterBracket++;
            }
            if (str.equals(Parser.END_OF_LIST)) {
                counterBracket--;
                if (counterBracket == 0) {
                    break;
                }
            }
            counter++;
        }
        return counter;
    }

    /**
     * Does not throw an exception because could be a user defined thing,
     * like after a TO command. if there is an error, it will get caught
     * at a later point in regular parsing.
     */
    private int getArgumentCount (String s) {
        if (s.startsWith(Parser.START_OF_VARIABLE))
            return -1;
        else {
            try {
                BaseInstruction base = myEnvironment.systemInstructionSkeleton(s);
                return base.getNumberOfArguments();
            }
            catch (IllegalInstructionException e) {
                return -1;
            }
        }

    }

    /**
     * adds everything from wordsList after the provided index into a new
     * list and removes those entries from wordsList
     */
    private List<String> createRestOfList (List<String> wordsList, int index) {
        List<String> result = new ArrayList<String>();
        for (int i = index + 1; i < wordsList.size(); i++) {
            result.add(wordsList.get(i));
        }
        int counter = wordsList.size() - index - 1;
        for (int i = 0; i < counter; i++) {
            wordsList.remove(index + 1);
        }
        return result;
    }
}
