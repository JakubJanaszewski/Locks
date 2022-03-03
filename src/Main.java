import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Siec siec = new Siec();
        Scanner scan = new Scanner(System.in);
        int liczbaKompoterow=4;
        int liczbaPowtorzen=0;

        System.out.println("Program pokazuje działanie 4 komputerów, które korzystają z 2 skanerów i 2 drukarek.");
        System.out.println("Wpisz 1 jeżeli chcesz załadować podstawowe parametry, 2 gdy chcesz załadować dane z pliku lub 3 jeżeli chcesz podać parametry.");
        String wariant = scan.nextLine();
        if(Integer.parseInt(wariant)==1){
            liczbaPowtorzen=1000;
        }else if(Integer.parseInt(wariant)==2){
            System.out.println("Podaj nazwe ścieżki do pliku.");
            wariant = scan.nextLine();
            Scanner scan2 = new Scanner(new File(wariant));
            liczbaPowtorzen=Integer.parseInt(scan2.nextLine());
        }else if(Integer.parseInt(wariant)==3){
            System.out.println("Podaj liczbę powtórzeń procesów sekwencyjnych.");
            liczbaPowtorzen=Integer.parseInt(scan.nextLine());
        }

        Komputer[] K = new Komputer[liczbaKompoterow];
        for(int i=0; i<liczbaKompoterow; i++)
        {
            K[i]= new Komputer(i+1, liczbaPowtorzen, siec);
        }

        for(int i=0; i<liczbaKompoterow; i++)
        {
            K[i].start();
        }

        try {
            for(int i=0; i<liczbaKompoterow; i++)
            {
                K[i].join();
            }
        }
        catch (InterruptedException e) {}
    }
}

