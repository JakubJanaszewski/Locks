import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Siec {
    private final int urzadzenia[] = new int[4];
    private final Lock zamek = new ReentrantLock();
    private final Condition grupa[] = new Condition[2];

    public Siec() {
        for (int i = 0; i < 4; i++) {
            urzadzenia[i] = 0;
        }

        grupa[0] = zamek.newCondition();
        grupa[1] = zamek.newCondition();
    }

    public void poczatekSkanowania(int id, int t) {
        zamek.lock();
        try {
            if (id % 2 == 0) {
                if (urzadzenia[0] == id - 1 | urzadzenia[1] == id - 1) {
                    grupa[(int) ((id - 1) / 2)].await();
                }
            } else {
                if (urzadzenia[0] == id + 1 | urzadzenia[1] == id + 1) {
                    grupa[(int) ((id - 1) / 2)].await();
                }
            }

            for (int i = 0; i < 2; i++) {
                if (urzadzenia[i] == 0) {
                    urzadzenia[i] = id;
                    break;
                }
            }
        } catch (InterruptedException ie) {
        } finally {
            System.out.println(">S> [W-" + id + ", " + t + "]::S[" + urzadzenia[0] + ", " + urzadzenia[1] + "] D[" + urzadzenia[2] + ", " + urzadzenia[3] + "]");
            zamek.unlock();
        }
    }

    public void koniecSkanowania(int id, int t) {
        zamek.lock();
        for (int i = 0; i < 2; i++) {
            if (urzadzenia[i] == id) {
                urzadzenia[i] = 0;
                grupa[(int) ((id - 1) / 2)].signal();
                break;
            }
        }
        System.out.println("<S< [W-" + id + ", " + t + "]::S[" + urzadzenia[0] + ", " + urzadzenia[1] + "] D[" + urzadzenia[2] + ", " + urzadzenia[3] + "]");
        zamek.unlock();
    }

    public void poczatekDrukowania(int id, int t) {
        zamek.lock();
        try {
            if (id % 2 == 0) {
                if (urzadzenia[2] == id - 1 | urzadzenia[3] == id - 1) {
                    grupa[(int) ((id - 1) / 2)].await();
                }
            } else {
                if (urzadzenia[2] == id + 1 | urzadzenia[3] == id + 1) {
                    grupa[(int) ((id - 1) / 2)].await();
                }
            }

            for (int i = 2; i < 4; i++) {
                if (urzadzenia[i] == 0) {
                    urzadzenia[i] = id;
                    break;
                }
            }
        } catch (InterruptedException ie) {
        } finally {
            System.out.println(">D> [W-" + id + ", " + t + "]::S[" + urzadzenia[0] + ", " + urzadzenia[1] + "] D[" + urzadzenia[2] + ", " + urzadzenia[3] + "]");
            zamek.unlock();
        }
    }

    public void koniecDrukowania(int id, int t) {
        zamek.lock();
        for (int i = 2; i < 4; i++) {
            if (urzadzenia[i] == id) {
                urzadzenia[i] = 0;
                grupa[(int) ((id - 1) / 2)].signal();
                break;
            }
        }
        System.out.println("<D< [W-" + id + ", " + t + "]::S[" + urzadzenia[0] + ", " + urzadzenia[1] + "] D[" + urzadzenia[2] + ", " + urzadzenia[3] + "]");
        zamek.unlock();
    }
}
