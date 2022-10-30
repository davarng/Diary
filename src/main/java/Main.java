import java.io.IOException;

/**
 * Mainmetod för Diary
 */
public class Main {
    public static void main(String[] args) throws IOException {
        int menuSelect = 0;

        while(menuSelect != 3) {
           menuSelect = DiaryService.menuChoice();

            switch (menuSelect) {
                case 1 -> DiaryService.readDiaryEntries();
                case 2 -> DiaryService.createNewDiaryEntry();
                case 3 -> System.out.println("Programmet avslutas");
            }
        }
    }
}