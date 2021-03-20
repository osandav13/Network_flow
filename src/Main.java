import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    private static void menu(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-----------------------------------------------");
            System.out.println("Please Enter Your Choice:");
            System.out.println("1. Calculate Maximum Flow");
            System.out.println("2. Display Flow Graph");
            System.out.println("0. To Exit The Menu");
            System.out.print("Enter your selection: ");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    maxFlow();
                    break;
                case "2":
                    displayFlowGraph();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Your input is not correct");
                    break;
            }
        }
    }

    private static void maxFlow(){

    }

    private static void displayFlowGraph(){

    }
}
