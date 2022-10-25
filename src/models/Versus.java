package models;

import java.util.ArrayList;

public class Versus {
    private final Personnage p1;
    private final Personnage p2;

    public Versus(Personnage p1, Personnage p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void fight() {
        // Le tour du P1
        if(p1.dealDamage() > 0) {
            p2.removeHealthPoint(p1.dealDamage());
            System.out.println(p1.getName() + " a infligé " + p1.getDamage() + " à " + p2.getName() +". Il lui reste " + p2.getHealPoint()  + " points de vie.");
        } else {
            p1.addHealthPoint(p1.heal());
            System.out.println(p1.getName() + " s'est soigné de " + p1.getHeal() + " points de vie. Il a maintenant " + p1.getHealPoint() + " points de vie au total.");
        }

        // Le tour du P2
        if(p2.dealDamage() > 0) {
            p1.removeHealthPoint(p2.dealDamage());
            System.out.println(p2.getName() + " a infligé " + p2.getDamage() + " à " + p1.getName() +". Il lui reste " + p1.getHealPoint()  + " points de vie.");
        } else {
            p2.addHealthPoint(p2.heal());
            System.out.println(p2.getName() + " s'est soigné de " + p2.getHeal() + " points de vie. Il a maintenant " + p2.getHealPoint() + " points de vie au total.");
        }

    }

    public void results(ArrayList<Personnage> personnages, int rand1, int rand2) {
        if(p1.getHealPoint() <= 0 && p2.getHealPoint() <= 0) {
            death(personnages, Math.max(rand1, rand2));
            death(personnages, Math.min(rand1, rand2));
            System.out.println(p1.getName()  +" et " + p2.getName() + " se sont entretués.");
        }

        else if(p1.getHealPoint() <= 0) {
            death(personnages, rand1);
            System.out.println(p1.getName() + " est décédé atrocement face à " + p2.getName()+ ".");
        }
        else if(p2.getHealPoint() <= 0) {
            death(personnages, rand2);
            System.out.println(p2.getName() + " est décédé atrocement face à " + p1.getName()+ ".");
        }
    }

    private void death(ArrayList<Personnage> personnages, int index) {
        personnages.remove(index);
    }
}
