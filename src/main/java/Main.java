import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Path pathDiaryEntries = Paths.get("diaryEntries.json");
        int menuSelect = 0;
        String title;
        String content;
        Scanner scanner = new Scanner(System.in);
        List<DiaryEntry> diaryEntries;
        List<DiaryEntry> tempDiary = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Date newDate = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        while(menuSelect != 3) {
            System.out.println("\nMainMenu\n\n1. Läs dagbok\n2. Skriv inlägg\n3. Avsluta programmet");
            menuSelect = Integer.parseInt(scanner.nextLine());

            switch (menuSelect) {
                case 1:
                    try {
                        List<DiaryEntry> diaryEntryListPrint = List.of
                                (mapper.readValue(pathDiaryEntries.toFile(), DiaryEntry[].class));

                        for (DiaryEntry diaryEntry : diaryEntryListPrint) {
                            System.out.println("\n" + diaryEntry.getTitle());
                            System.out.println(diaryEntry.getContent());
                            System.out.println(diaryEntry.getDate());
                        }
                    } catch (IOException e) {
                        System.out.println("\nDu har inget inlägg");
                    }

                    break;
                case 2:
                    try {
                        diaryEntries = List.of(mapper.readValue(pathDiaryEntries.
                                toFile(), DiaryEntry[].class));
                        tempDiary.clear();
                        tempDiary.addAll(diaryEntries);

                        System.out.print("Ange titel på ditt inlägg: ");
                        title = scanner.nextLine();
                        System.out.print("Skriv ditt inlägg: ");
                        content = scanner.nextLine();

                        DiaryEntry diaryEntry = new DiaryEntry(title, content, dateForm.format(newDate));

                        tempDiary.add(diaryEntry);
                        mapper.writeValue(pathDiaryEntries.toFile(), tempDiary);
                    }catch (Exception e){
                        System.out.print("Ange titel på ditt inlägg: ");
                        title = scanner.nextLine();
                        System.out.print("Skriv ditt inlägg: ");
                        content = scanner.nextLine();

                        DiaryEntry diaryEntry = new DiaryEntry(title, content, dateForm.format(newDate));

                        tempDiary.add(diaryEntry);
                        mapper.writeValue(pathDiaryEntries.toFile(), tempDiary);
                    }
                    break;
                case 3:
                    System.out.println("Programmet avslutas");
                    break;
            }
        }
    }
}
