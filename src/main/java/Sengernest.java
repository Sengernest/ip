import java.util.Scanner;

public class Sengernest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int count = 0;

        System.out.println("Hello! I'm Sengernest");
        System.out.print("What can I do for you?: ");

        while(true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println();
                System.out.println("Goodbye, hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                System.out.println();
                if (list[0] == null) {
                    System.out.println("Nothing added yet!");
                } else {
                    for (int i = 0; i < count; i++) {
                        System.out.println(i + 1 + ". "+ list[i]);
                    }
                }
                System.out.println();
                System.out.print("Enter next command: ");
            } else {
                list[count] = command;
                count += 1;
                System.out.println();
                System.out.println("added to list: " + command);
                System.out.println();
                System.out.print("Enter next command: ");
            }
        }

        scanner.close();
    }

}
