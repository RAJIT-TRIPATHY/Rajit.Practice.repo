
import java.util.Scanner;

public class BasicChatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm your chatbot. Type 'exit' to quit.");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("You said: " + input);
            System.out.println("I'm still learning, but I'm here to help!");
        }
        scanner.close();
        System.out.println("Goodbye!");
    }
}


