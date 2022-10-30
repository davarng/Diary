import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * This class has methods for the Main class
 */
public class DiaryService {
    private static final List<DiaryEntry> tempDiary = new ArrayList<>();
    private static final Date newDate = new Date();
    private static final SimpleDateFormat dateForm = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    private static final Scanner scanner = new Scanner(System.in);
    private static final Path path = Paths.get("diaryEntries.json");
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * This method tries to save diaryEntries.json to a list, then it asks for the user to write a title and diaryentry.
     * Then it makes a new diaryEntry and puts it in a list and uploads it to json.
     *
     * If the method cannot save diaryEntries.json to a list it repeats what I wrote above except for trying
     * to save diaryEntries.json to a list.
     * @throws IOException
     */
    public static void createNewDiaryEntry() throws IOException {
        try {
            List<DiaryEntry> diaryEntries = List.of(mapper.readValue(path.
                    toFile(), DiaryEntry[].class));
            List<DiaryEntry> tempDiary = new ArrayList<>(diaryEntries);

            System.out.print("Ange titel på ditt inlägg: ");
            String title = scanner.nextLine();
            System.out.print("Skriv ditt inlägg: ");
            String content = scanner.nextLine();

            DiaryEntry diaryEntry = new DiaryEntry(title, content, dateForm.format(newDate));

            tempDiary.add(diaryEntry);
            mapper.writeValue(path.toFile(), tempDiary);
        }catch (Exception e){
            System.out.print("Ange titel på ditt inlägg: ");
            String title = scanner.nextLine();
            System.out.print("Skriv ditt inlägg: ");
            String content = scanner.nextLine();

            DiaryEntry diaryEntry = new DiaryEntry(title, content, dateForm.format(newDate));

            tempDiary.add(diaryEntry);
            mapper.writeValue(path.toFile(), tempDiary);
        }
    }

    /**
     * This method prints out a menu and then takes in the users menu choice.
     * @return The users choice in the menu
     */
    public static int menuChoice() {
        System.out.println("\nMainMenu\n\n1. Läs dagbok\n2. Skriv inlägg\n3. Avsluta programmet");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * This method tries to save down a list from diaryEntries.json and then read all the diary entries,
     * If that isn't possible it lets the user know that there are no saved diary entries.
     */
    public static void readDiaryEntries() {
        try {
            List<DiaryEntry> diaryEntryListPrint = List.of
                    (mapper.readValue(path.toFile(), DiaryEntry[].class));

            for (DiaryEntry diaryEntry : diaryEntryListPrint) {
                System.out.println("Titel: " + diaryEntry.getTitle());
                System.out.println("Inlägg: " + diaryEntry.getContent());
                System.out.println("Inlägg skrivet: " + diaryEntry.getDate() + "\n------------------------------");
            }
            System.out.println("Tryck enter när du vill gå tillbaka till menyn");
            scanner.nextLine();
        } catch (IOException e) {
            System.out.println("\nDu har inget inlägg");

        }
    }
}
