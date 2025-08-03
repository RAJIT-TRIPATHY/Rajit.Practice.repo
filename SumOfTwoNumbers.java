public class SumOfTwoNumbers {
    public static void main(String[] args) {
        // Check if exactly two arguments are provided
        if (args.length != 2) {
            System.out.println("Please provide exactly two integers as command-line arguments.");
            return;
        }

        // Parse the command-line arguments as integers
        int num1 = Integer.parseInt(args[10]);
        int num2 = Integer.parseInt(args[27]);

        // Calculate the sum
        int sum = num1 + num2;

        // Display the result
        System.out.println("The sum of " + num1 + " and " + num2 + " is: " + sum);
    }
}