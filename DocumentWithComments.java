import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DocumentWithComments {

    public void sort() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Files.txt"))) {

            Set<String> setDoc = new HashSet<>();
            for (String element : ListDocument.input()) {
                if (Files.isRegularFile(Path.of(element))) {
                    List<String> listOfDocuments = Files.readAllLines(Path.of(element));
                    setDoc.addAll(listOfDocuments);
                }
            }

            Map<String, String> mapDoc = new HashMap<>();
            for (String doc : setDoc) {
                if (doc.matches("[A-Za-z\\d]+") && doc.length() == 15 &&
                        (doc.startsWith("docnum") || doc.startsWith("contract"))) {
                    mapDoc.put(doc, "Valid document");
                } else if (!(doc.startsWith("docnum") || doc.startsWith("contract"))) {
                    mapDoc.put(doc, "Invalid document start name");
                } else if (!doc.matches("[A-Za-z\\d]+")) {
                    mapDoc.put(doc, "Invalid document symbol containment");
                } else if (doc.length() != 15) {
                    mapDoc.put(doc, "Invalid document length");
                }
            }

            for (Map.Entry<String, String> validation : mapDoc.entrySet()) {
                writer.write(validation.getKey() + " -- " + validation.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}