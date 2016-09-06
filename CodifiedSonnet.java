import java.util.HashMap;
import java.util.Random;

/**
 * Created by Maya Kumits (www.mayakumits.com) on 8/10/16.
 */
public class CodifiedSonnet {
    static HashMap<DatingStatus, Integer> stats = new HashMap<>();

    public static void main(String[] args) {
        stats.put(DatingStatus.DatingSeriously, 0);
        stats.put(DatingStatus.DatingCasually, 0);
        stats.put(DatingStatus.BrokeUp, 0);
        stats.put(DatingStatus.Married, 0);
        int tries = 1000;

        CodifiedSonnet mySonnet = new CodifiedSonnet();
        for (int i = 0; i < tries; i++) {
            mySonnet.loveDecoded();
        }

        System.out.println("\n--------- FINAL STATS (" + tries + " tries) -----------");
        System.out.println("Dating seriously: " + stats.get(DatingStatus.DatingSeriously));
        System.out.println("Dating casually: " + stats.get(DatingStatus.DatingCasually));
        System.out.println("Broke up: " + stats.get(DatingStatus.BrokeUp));
        System.out.println("Married: " + stats.get(DatingStatus.Married));
        System.out.println("\n\n\n");
    }

    private void loveDecoded() {
        char you='u'; char me='i';
        Couple we = new Couple(you,me);
        try {
            do {
                if (we.doCheat() || we.doLie())
                    we.breakUpNow();
                else if (we.areHappy()
                         && we.haveEngagingConversation()
                         && we.haveGreatSex()) {
                    we.stayCommitted();
                    if (we.bothDesireProcreation()
                        && we.haveTempersThatAreWellFitted()) {
                        // TODO: Share this great news with the mothers
                        throw new CoupleMatchedAsWellAsCanBe();
                    }
                } else
                    we.goCasualAndDateOthers();
            } while (we.areDating());
        } catch (Exception e) {
            // happily-ever-afters for these two
            MustMarryImmediatly(me,you);
        }
    }

    private void MustMarryImmediatly(char me, char you) {
        System.out.println("Just Married!");
        stats.put(DatingStatus.Married, stats.get(DatingStatus.Married) + 1);
    }

    private class Couple {
        boolean dating = true;
        char you, me;
        private Random random;


        public Couple(char you, char me) {
            System.out.println("----- New Couple -----");
            this.you = you;
            this.me = me;
            random = new Random();
        }

        public boolean areDating() {
            return dating;
        }

        public void stayCommitted() {
            System.out.println("Stay committed");
            stats.put(DatingStatus.DatingSeriously, stats.get(DatingStatus.DatingSeriously) + 1);
            dating = true;
        }

        public void goCasualAndDateOthers() {
            System.out.println("Date others");
            stats.put(DatingStatus.DatingCasually, stats.get(DatingStatus.DatingCasually) + 1);
            dating = true; // kind-of
        }

        public void breakUpNow() {
            System.out.println("Breakup");
            stats.put(DatingStatus.BrokeUp, stats.get(DatingStatus.BrokeUp) + 1);
            dating = false;
        }

        public boolean doCheat() {
            return getResult("Do Cheat?");
        }

        public boolean doLie() {
            return getResult("Do lie?");
        }

        public boolean areHappy() {
            return getResult("Are happy?");
        }

        public boolean haveEngagingConversation() {
            return getResult("Have engaging conversation?");
        }

        public boolean haveGreatSex() {
            return getResult("Have great sex?");
        }

        public boolean bothDesireProcreation() {
            return getResult("Both desire procreation?");
        }

        public boolean haveTempersThatAreWellFitted() {
            return getResult("Have well fitted tempers?");
        }

        private boolean getResult(String s) {
            boolean b = random.nextBoolean();
            System.out.println(s + " " + b);
            return b;
        }

    }

    private class CoupleMatchedAsWellAsCanBe extends Exception {
    }

    private enum DatingStatus {
        DatingSeriously, DatingCasually, BrokeUp, Married
    }
}
