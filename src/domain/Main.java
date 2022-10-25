package domain;

import models.Elf;
import models.Human;
import models.Orc;
import models.Personnage;
import models.Versus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*
    Développer un jeu
    Avec des Orc, Humain, Elf

    Attaquant, Soigneur

    Stocker dans une Array, et de manière aléatoire, on les fait se battre par deux ( 1 v 1 Gare du Nord)
    Si soigneur, il se donne des pv. Si c'est un attaquant, enlève pv à son adversaire

    Affiche dans la console

    Il y aura 10 échanges dans votre programme.
     */

    public static int round = 0;

    public static ArrayList<Personnage> personnages = new ArrayList<>();
    public static ArrayList<String> names = new ArrayList<>();

    public static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Initialisation des personnages...");
        initPersonnages();

        System.out.println("Début du tournois...");
        tournament();
    }

    /**
     * Initialise un nombre n de personnages qui vont démarrer les combats !
     */
    public static void initPersonnages() {
        names.addAll(List.of("Freddy", "Victor", "Ségolène", "Lisa", "Maud", "Kesavan", "Benoit", "Joris", "Jean-Philippe", "Hugo", "Baptiste", "Fabrice"));

        System.out.println("Combien de personnages voulez-vous créer ?");
        int nbCharacters = sc.nextInt();
        nbCharacters = Math.min(nbCharacters, names.size());

        System.out.println("Création aléatoire des personnages...");
        createRandomCharacters(nbCharacters);

    }

    /**
     * Créer un nombre aléatoire de Personnages qui auront différentes classes (Orc, Elf, Humain) et
     * de leur spécialisation (Guerrier, Soigneur)
     * @param nbCharacters Nombre de personnages à créer
     */
    public static void createRandomCharacters(int nbCharacters) {
        for(int i = 0; i < nbCharacters; i++) {
            Personnage pers;
            int randClass = randomStats(0,100);
            int randNameIndex = randomIndex(0,names.size());
            String randomName = names.get(randNameIndex);
            int randomHP = randomIndex(Personnage.DEFAULT_MIN_HP,Personnage.DEFAULT_MAX_HP);
            int randSpe = randomStats(0,1);



            if(randClass < 33) {
                System.out.println("Un nouvel Humain a été créé...");
                pers = new Human(randomHP, randomName);
            } else if (randClass < 66) {
                System.out.println("Un nouvel Orc a été créé...");
                pers = new Orc(randomHP, randomName);
            } else {
                System.out.println("Un nouvel Elf a été créé...");
                pers = new Elf(randomHP, randomName);
            }

            if(randSpe == 0) {
                int randDamage = randomIndex(Personnage.MIN_DAMAGES, Personnage.MAX_DAMAGES);
                pers.setDamage(randDamage);
                System.out.println("... Il s'appelle " + pers.getName() + ". C'est un GUERRIER dont les dégats s'élève à " + pers.getDamage() + " et ses PV sont à " + pers.getHealPoint() + ".");
            } else {
                int randHeal = randomIndex(Personnage.MIN_HEAL, Personnage.MAX_HEAL);
                pers.setHeal(randHeal);
                System.out.println("... Il s'appelle " + pers.getName() + ". C'est un SOIGNEUR dont les soins s'élève à " + pers.getHeal() + " et ses PV sont à " + pers.getHealPoint() + ".");

            }

            names.remove(randNameIndex);
            personnages.add(pers);



        }
    }



    public static int randomIndex (int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static int randomStats (int min, int max) {
        return (int) Math.round((Math.random() * (max - min)) + min);
    }


    public static Personnage getPersonnage(ArrayList<Personnage> list, int index) {
        return list.get(index);
    }

    public static void tournament() {
        System.out.println("En combien de rounds doit se jouer ce tournoi ?");
        round = sc.nextInt();
        int count = 0;

        do {
            int rand1 = randomIndex(0,personnages.size());
            int rand2 = randomIndex(0,personnages.size());
            if(rand2 == rand1) {
                continue;
            }


            Personnage p1 = getPersonnage(personnages, rand1);
            Personnage p2 = getPersonnage(personnages, rand2);


            ////////////////////////////////////////////////////

            Versus versus = new Versus(p1,p2);
            versus.fight();
            versus.results(personnages, rand1, rand2);

            ///////////////////////////////////////////////////

            count++;

        } while (personnages.size() > 1 && count < round);

        if(personnages.size() > 1) {
            System.out.println("Ce sont tous les gagnants");
            for (Personnage perso: personnages
            ) {
                System.out.println(perso.getName());

            }
        } else if(personnages.size() == 1) {System.out.println("Le gagnant est : " + personnages.get(0).getName());}
        else {
            System.out.println("Ils se sont tous entretués !");
        }
}
}