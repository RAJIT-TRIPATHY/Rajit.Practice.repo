public class AlphabetCheck {
    public static void main(String[] args) {
        // Assign an English alphabet character (you can change this value to test with others)
        char alphabet = 'E';  // Example default value
        
        // Check if the character is an alphabet
        if (Character.isLetter(alphabet)) {
            // Convert the character to lowercase to simplify checking
            alphabet = Character.toLowerCase(alphabet);

            // Check if the character is a vowel
            if (alphabet == 'a' || alphabet == 'e' || alphabet == 'i' || alphabet == 'o' || alphabet == 'u') {
                System.out.println(alphabet + " is a vowel.");
            } else {
                // If not a vowel, then it's a consonant
                System.out.println(alphabet + " is a consonant.");
            }
        } else {
            // If the character is not a letter, print a default message
            System.out.println("The character is not a valid English alphabet.");
        }
    }
}
