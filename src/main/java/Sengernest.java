import java.util.Scanner;

public class Sengernest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Sengernest");
        System.out.print("What can I do for you?: ");

        while(true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println();
                System.out.println("Goodbye, hope to see you again soon!");
                break;
            } else {
                System.out.println();
                System.out.println(command);
                System.out.println();
                System.out.print("Enter next command: ");
            }
        }

        scanner.close();
    }
}
