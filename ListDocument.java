import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListDocument {

    public static List<String> input() {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter path of files, enter 0 to stop");

            //   resources/Files1
            //   resources/Files2
            //   resources/Files3

            List<String> listDoc = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("0")) {
                    break;
                }
                listDoc.add(line);
            }
            return listDoc;
        }
    }
}

