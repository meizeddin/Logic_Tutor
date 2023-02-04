package expressionTree;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {

    private static final Pattern TOKEN_PATTERN = Pattern.compile(
        // Variables (single letters)
        "([A-Za-z])" +
        // Operators (includes ! for negation, & for conjunction, | for disjunction, and => for implication)
        "|(!|&|\\||=>|<=>)" +
        // Parentheses
        "|(\\(|\\))"
    );
    
    /**
     * Tokenizes a given input string
     * @param input the string to be tokenized
     * @return a list of tokens
    */
    public static List<String> tokenize(String input) {
    	// initiates the list that will hold the tokens
        List<String> tokens = new ArrayList<>();
        // Matches the input string against the predefined token pattern
        Matcher matcher = TOKEN_PATTERN.matcher(input);
        // Iterates through all the matches and adds them to the list of tokens
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        //returns the list of tokens.
        return tokens;
    }
    
    public static void main(String[] args) {
    	List<String> tokens = Tokenizer.tokenize("(A => B) & !C <=> v");
    	System.out.println(tokens);
	}
}
