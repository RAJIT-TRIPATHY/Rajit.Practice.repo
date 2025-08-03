public class SmallestNumber {
    public static void main(String[] args) {
        // Initialize three integer values
        int num1 = 47;
        int num2 = 54;
        int num3 = 35;

        // Find the smallest number
        int smallest;

        if (num1 <= num2 && num1 <= num3) {
            smallest = num1;
        } else if (num2 <= num1 && num2 <= num3) {
            smallest = num2;
        } else {
            smallest = num3;
        }

        // Output the smallest number
        System.out.println("The smallest number among " + num1 + ", " + num2 + ", and " + num3 + " is: " + smallest);
    }
}
