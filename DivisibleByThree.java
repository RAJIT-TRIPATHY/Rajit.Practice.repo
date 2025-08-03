public class DivisibleByThree {
    public static void main(String[] args) {
        // Initialize a 3-digit number
        int number = 336;  // Example default value (you can change this number)
        
        // Check if the number is divisible by 3, and print "CUTM" until it is
        while (number % 3 != 0) {
            System.out.println("CUTM");
            number++;  // Increment the number by 1 each time
        }

        // Once the number is divisible by 3, print the final number
        System.out.println("The number " + number + " is divisible by 3.");
    }
}
