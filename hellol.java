import java.util.Scanner;

class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the first number");
        int firstNumber = input.nextInt();
        System.out.println("Enter the second  number");
        int secondNumber = input.nextInt();
        System.out.println("The Sum Of Entered Number is:");
        System.out.println(firstNumber + secondNumber);

    }
}
