package control;

import instructions.BaseInstruction;
import java.util.ArrayList;
import java.util.List;
import exceptions.IllegalInstructionException;


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
    
    public String preParse(String s) throws IllegalInstructionException {
        String stepZero = removeResultIndicators(s);
        String stepOne = addBrackets(stepZero);
        return stepOne;
    }

    private String removeResultIndicators (String s) {
        String lines[] = s.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        for (String line :lines) {
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
        if (wordsList.size() < 3)
            return s;

        ReturnValues rv = recurse(wordsList, Integer.MAX_VALUE);
        wordsList = rv.list;
        
        return createStringFromList(wordsList, 0, wordsList.size());
    }

    private ReturnValues recurse (List<String> wordsList, int argCount)
                                                                       throws IllegalInstructionException {
        List<String> restOfList = new ArrayList<String>();
        int counter = 0;
        for (int i = 0; i < argCount; i++) {
            if (counter >= wordsList.size())
                break;
            String command = wordsList.get(counter);
            if (command.equals("[")) {
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
                wordsList.add(counter, "[");
                counter++;
                restOfList = createRestOfList(wordsList, counter);
                ReturnValues rv = recurse(restOfList, getArgumentCount(command));
                counter += rv.counterChange;
                merge(wordsList, rv.list);
                counter++;
                wordsList.add(counter, "]");
                counter++;
            }
        }
        return new ReturnValues(wordsList, counter);
    }

    private int insertList (List<String> insideList, List<String> wordsList, int counter) {
        for (String str:insideList) {
            wordsList.add(counter, str);
            counter++;
        }
        return counter;
    }

    private List<String> createListFromString (String s) {
        String[] words = s.split("\\s+");
        List<String> wordsList = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            wordsList.add(words[i]);
        }
        return wordsList;
    }

    // removes entries between counter (inclusive) and indexOfRightBracket (exclusive)
    // in wordsList
    private String createStringFromList (List<String> wordsList,
                                         int counter,
                                         int indexOfRightBracket) {
        StringBuilder sb = new StringBuilder();
        for (int i = counter; i < indexOfRightBracket; i++) {
            String str = wordsList.get(i);
            sb.append(str);
            sb.append(" ");
        }
        for (int i = counter; i < indexOfRightBracket; i++) {
            wordsList.remove(counter);
        }
        return sb.toString();
    }

    private int findRightBracket (List<String> wordsList, int counter) {
        String str;
        int counterBracket = 1;
        while (counterBracket != 0) {
            str = wordsList.get(counter);
            if (str.equals("[")) {
                counterBracket++;
            }
            if (str.equals("]")) {
                counterBracket--;
                if (counterBracket == 0) {
                    break;
                }
            }
            counter++;
        }
        return counter;
    }

    private int getArgumentCount (String s) throws IllegalInstructionException {
        if (isNumber(s) || s.charAt(0) == ':') {
            return -1;
        }
        else{
            BaseInstruction base = myEnvironment.systemInstructionSkeleton(s);
            return base.getNumberOfArguments();
        }

    }

    private boolean isNumber (String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

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

    private void merge (List<String> wordsList, List<String> restOfList) {
        for (String s : restOfList) {
            wordsList.add(s);
        }
    }
}
