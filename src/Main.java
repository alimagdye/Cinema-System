import java.util.Scanner;
public class Main {
    public static void main (String[] args) {
        Scanner scanObj = new Scanner(System.in);
        CLI.Manager cli = new CLI.Manager(scanObj);
        cli.start(scanObj);
    }
}
