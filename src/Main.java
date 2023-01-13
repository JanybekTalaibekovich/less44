import java.util.Arrays;
import java.util.Random;
import java.util.Timer;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 70;
    public static String bossDefence;
    public static int medicalHeal = 30;
    public static int[] heroesHealth = {180, 270, 180, 150, 300, 270};
    public static int[] heroesDamage = {10, 15, 20, 0, 7, 15};
    static String[] heroesAttackType = new String[]{"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky"};
    public static int roundNumber;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++; // roundNumber = roundNumber + 1
        chooseBossDefence();
        bossHits();
        heroesHit();
        medicHits();
        golemHits();
        luckyHits();
        printStatistics();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coefficient = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage = coefficient * heroesDamage[i];
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }
    public static void medicHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if(heroesAttackType[i].equals("Medic")){
                continue;
            } else if (heroesHealth[i] > 0 && heroesHealth[i] <= 100 && heroesHealth[3] > 0) {
                heroesHealth[i] += medicalHeal;
                System.out.println("Герой " + heroesAttackType[i] + " вылечен");
                break;
            }
        }

    }
    public static void golemHits(){
        int golemIndex = 0;
        int golemAttack = bossDamage / 5;
        for (int i = 0; i < heroesHealth[i]; i++) {
            if(heroesAttackType[i].equals("Golem")){
                golemIndex = i;
                break;
            }
            for (int j = 0; j < heroesAttackType.length; j++) {
                if(heroesHealth[golemIndex] != 0){
                    heroesHealth[golemIndex] -= golemAttack;
                    System.out.println("Голем принял на себя удар");
                    break;
                }else {
                    heroesHealth[golemIndex] = 0;
                }
            }
        }
    }


    public static void luckyHits(){
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesAttackType[i].equals("Lucky")){
                Random random = new Random();
                boolean luckyAbilityVariable = random.nextBoolean();
                if (heroesAttackType[i].equals("Lucky") == luckyAbilityVariable){
                    int luckyHealth = heroesHealth[5] = bossDamage;
                    System.out.println("Lucky уклонился");
                    break;
                }else{
                    continue;
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println(" ROUND " + roundNumber + " --------------");
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage
                + " defence: " + (bossDefence != null ? bossDefence : "No defence"));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]
                    + " damage: " + heroesDamage[i]);
        }
    }
}