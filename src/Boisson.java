import java.io.Serializable;

public class Boisson implements Serializable{
    // Représente une boisson de la machine à café, composée d'un nom, un prix et le nombre d'ingrédients correspondant.
    private String nom;
    private int prix;
    private int cafe;
    private int lait;
    private int chocolat;
    private int sucre;

    // Nombre de boissons présentes dans la machine à café.
    private static int count=0;

    // Constructeur : créer une nouvelle boisson
    public Boisson(String nom, int prix, int cafe, int lait, int chocolat, int sucre) {
        this.nom = nom;
        this.prix = prix;
        this.cafe = cafe;
        this.lait = lait;
        this.chocolat = chocolat;
        this.sucre = sucre;

        // On incrémente le nombre de boissons de la machine
        this.count++;
    }

    // Afficher la boisson
    @Override
    public String toString() {
        return  nom+ " ("+
                prix+"€) : "+
                cafe+" café(s), "+
                lait+" lait(s), "+
                chocolat+" chocolat(s), "+
                sucre+" sucre(s)";
    }

    // Décrémenter le nombre de boisson (lors d'une suppression)
    public static void decreaseCount() {
        Boisson.count--;
    }

    // Incrémenter le nombre de boisson (lors du chargement des boissons)
    public static void increaseCount() {
        Boisson.count++;
    }

    // Getters et Setters

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getCafe() {
        return cafe;
    }

    public void setCafe(int cafe) {
        this.cafe = cafe;
    }

    public int getLait() {
        return lait;
    }

    public void setLait(int lait) {
        this.lait = lait;
    }

    public int getChocolat() {
        return chocolat;
    }

    public void setChocolat(int chocolat) {
        this.chocolat = chocolat;
    }

    public int getSucre() {
        return sucre;
    }

    public void setSucre(int sucre) {
        this.sucre = sucre;
    }

    public static int getCount() {
        return count;
    }
}
