public class Komputer extends Thread{

    public int ID;
    public int maxPowtorzen;
    public int numerPowtorzenia;

    public Siec siec;

    public Komputer(int id, int liczbaPowtorzen, Siec s)
    {
        ID = id;
        maxPowtorzen = liczbaPowtorzen;
        siec = s;
    }

    public void uzywanieSkanera(){
        try{
            int t = (int)(Math.random() * 5 + 5);
            sleep(t);
        }
        catch (InterruptedException ex){
        }
    }

    public void uzywanieDrukarki(){
        try{
            int t = (int)(Math.random() * 5 + 10);
            sleep(t);
        }
        catch (InterruptedException ex){
        }
    }

    public void run()
    {
        for(numerPowtorzenia=0; numerPowtorzenia<maxPowtorzen; numerPowtorzenia++)
        {
            siec.poczatekSkanowania(ID, numerPowtorzenia);
            uzywanieSkanera();
            siec.koniecSkanowania(ID, numerPowtorzenia);

            siec.poczatekDrukowania(ID, numerPowtorzenia);
            uzywanieDrukarki();
            siec.koniecDrukowania(ID, numerPowtorzenia);
        }
    }
}
