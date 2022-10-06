import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<DiaryEntry> diaryEntries = new ArrayList<>();
        int menuSelect = 0;
        Scanner scanner = new Scanner(System.in);

        while(menuSelect != 3) {
            System.out.println("MainMenu\n\n1. Läs dagbok\n2. Skriv inlägg\n3. Avsluta programmet");
            menuSelect = scanner.nextInt();

            switch (menuSelect) {
                case 1:

                    break;
                case 2:
                    System.out.println("Ange titel på ditt inlägg: ");

                    System.out.println("Skriv ditt inlägg: ");
                    break;
                case 3:
                    
                    break;
                default:
                    System.out.println("fel");
                    break;
            }




        }











    }
}
