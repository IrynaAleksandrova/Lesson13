import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DocumentWithComments {

    public void sort() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/Files.txt"))) {

            Set<String> setDoc = new HashSet<>();
            for (String element : ListDocument.getInput()) {
                if (Files.isRegularFile(Path.of(element))) {
                    List<String> listOfDocuments = Files.readAllLines(Path.of(element));
                    setDoc.addAll(listOfDocuments);
                }
            }

            HashMap<String, String> validationMap = new HashMap<>();
            for (String doc : setDoc) {
                if (doc.matches("[A-Za-z\\d]+") && doc.length() == 15 &&
                        (doc.startsWith("docnum") || doc.startsWith("contract"))) {
                    validationMap.put(doc, "Valid document");
                } else if (!(doc.startsWith("docnum") || doc.startsWith("contract"))) {
                    validationMap.put(doc, "Invalid document start name");
                } else if (!doc.matches("[A-Za-z\\d]+")) {
                    validationMap.put(doc, "Invalid document symbol containment");
                } else if (doc.length() != 15) {
                    validationMap.put(doc, "Invalid document length");
                }
            }
            for (String key : validationMap.keySet()) {
                writer.write(key + " -- " + validationMap.get(key) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}