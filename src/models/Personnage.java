package models;

public class Personnage implements Attaquant, Soigneur{
        private int healPoint = 30;
        private int damage = 0;
        private int heal = 0;

        protected String name;


        public final static int DEFAULT_MAX_HP;
        public final static int DEFAULT_MIN_HP;
        public final static int MAX_DAMAGES;
        public final static int MIN_DAMAGES;
        public final static int MAX_HEAL;
        public final static int MIN_HEAL;

        static {
            DEFAULT_MIN_HP = 10;
            DEFAULT_MAX_HP = 200;
            MAX_DAMAGES = 60;
            MIN_DAMAGES = 20;
            MAX_HEAL = 40;
            MIN_HEAL = 15;
        }

        @Override
        public int dealDamage() {
            return damage;
        }

        @Override
        public int heal() {
            return heal;
        }


        public Personnage(int healPoint, String name) {
            this.healPoint = healPoint;
            this.name = name;
        }

        public Personnage(int healPoint, int damage, int heal, String name) {
            this.healPoint = healPoint;
            this.damage = damage;
            this.heal = heal;
            this.name = name;
        }


        public void setDamage(int damage) {
            this.damage = damage;
        }

        public int getDamage() {
            return damage;
        }

        public void setHeal(int heal) {
            this.heal = heal;
        }

        public int getHeal() {
            return heal;
        }


        public int getHealPoint() {
            return healPoint;
        }

        public void setHealPoint(int healPoint) {
            this.healPoint = healPoint;
        }

        public void removeHealthPoint(int damageDealt) {
            this.healPoint -= damageDealt ;
        }

        public void addHealthPoint(int healing) {
            this.healPoint += healing;
        }
        public String getName() {
            return name;
        }

}
