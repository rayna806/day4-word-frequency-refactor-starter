import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
        if (words.length == 1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                List<Input> inputList = countFrequencies(words);
                inputList.sort((w1, w2) -> w2.wordCount() - w1.wordCount());
                return composeOutput(inputList);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private String composeOutput(List<Input> inputList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Input word : inputList) {
            String wordgroup = word.value() + " " + word.wordCount();//rename
            joiner.add(wordgroup);
        }
        return joiner.toString();
    }


    private List<Input> countFrequencies(String[] words) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        List<Input> frequencies = new ArrayList<>();
        //refactor for loop
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            frequencies.add(new Input(entry.getKey(), entry.getValue()));
        }
        return frequencies;
    }
}
