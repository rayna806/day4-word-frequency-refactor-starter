import java.util.*;
import java.util.stream.Collectors;

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
        Arrays.stream(words).forEach(word -> freqMap.put(word, freqMap.getOrDefault(word, 0) + 1));
        return freqMap.entrySet().stream().map(entry -> new Input(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }
}
