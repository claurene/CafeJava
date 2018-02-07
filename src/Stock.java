public class Stock {
    // Représente l'inventaire de la machine à café : contient le nombre de cafés, laits, chocolats et sucres stockés.
    private int cafe;
    private int lait;
    private int chocolat;
    private int sucre;

    // Constructeur : initialiser le stock
    public Stock(int cafe, int lait, int chocolat, int sucre) {
        this.cafe = cafe;
        this.lait = lait;
        this.chocolat = chocolat;
        this.sucre = sucre;
    }

    // Affiche le stock de la machine
    @Override
    public String toString() {
        return "La machine contient :\n\t- "+
                cafe+" café(s)\n\t- "+
                lait+" lait(s)\n\t- "+
                chocolat+" chocolat(s)\n\t- "+
                sucre+" sucre(s)\n";
    }

    // Getters et Setters

    public int getCafe() {
        return cafe;
    }

    public void addCafe(int cafe) {
        this.cafe += cafe;
    }

    public int getLait() {
        return lait;
    }

    public void addLait(int lait) {
        this.lait += lait;
    }

    public int getChocolat() {
        return chocolat;
    }

    public void addChocolat(int chocolat) {
        this.chocolat += chocolat;
    }

    public int getSucre() {
        return sucre;
    }

    public void addSucre(int sucre) {
        this.sucre += sucre;
    }
}
