import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Fonctions

    // Ajoute le nombre d'ingrédients précisés en paramètres
    public static void ajout_ingredient(Stock stock, int cafe, int lait, int chocolat, int sucre) {
        // On ajoute chaque ingrédient dans la machine, et gère l'erreur d'ajout d'un nombre négatif d'ingrédients
        if (cafe<0 || lait<0 || chocolat<0 || sucre<0){
            System.out.println("Veuillez renseigner des valeurs positives correctes pour les ingrédients.");
        } else {
            stock.addCafe(cafe);
            stock.addLait(lait);
            stock.addChocolat(chocolat);
            stock.addSucre(sucre);
            // On affiche le nombre d'ingrédients ajoutés en guise de retour
            System.out.println("Ingrédients ajoutés dans la machine :\n\t- "+
                    cafe+" café(s)\n\t- "+
                    lait+" lait(s)\n\t- "+
                    chocolat+" chocolat(s)\n\t- "+
                    sucre+" sucre(s)\n");
        }
    }

    // Affiche le stock
    public static void afficher_stock(Stock stock) {
        // On affiche le stock grâce à la méthode de classe
        System.out.println(stock.toString());
    }

    // Ajouter une boisson
    public static void ajouter_boisson(ArrayList<Boisson> liste, String nom,int prix,int cafe,int lait,int chocolat,int sucre) {
        if (prix<=0 || cafe<0 || lait<0 || chocolat<0 || sucre<0){
            System.out.printf("Veuillez renseigner des valeurs positives correctes pour le prix et les ingrédients.");
        } else if (cafe==0 & lait==0 & chocolat==0 & sucre==0) {
            System.out.printf("Une boisson doit contenir au moins un ingrédient !");
        } else {
            // Vérifier si le nom de la boisson est unique
            for (Boisson b : liste) {
               if (nom.equals(b.getNom())) {
                   System.out.printf("Une boisson comporte déjà ce nom là.");
                   return;
               }
            }
            Boisson boisson = new Boisson(nom,prix,cafe,lait,chocolat,sucre);
            liste.add(boisson);
            System.out.printf("La boisson \""+boisson.toString()+"\" a bien été ajoutée à la machine.");
        }
    }

    // Modifier une boisson
    public static void modifier_boisson(Boisson boisson,int prix,int cafe,int lait,int chocolat,int sucre) {
        if (prix<=0 || cafe<0 || lait<0 || chocolat<0 || sucre<0){
            System.out.printf("Veuillez renseigner des valeurs positives correctes pour le prix et les ingrédients.");
        } else if (cafe==0 & lait==0 & chocolat==0 & sucre==0) {
            System.out.printf("Une boisson doit contenir au moins un ingrédient !");
        } else {
            boisson.setPrix(prix);
            boisson.setCafe(cafe);
            boisson.setLait(lait);
            boisson.setChocolat(chocolat);
            boisson.setSucre(sucre);
            System.out.println("La boisson a bien été modifiée. Sa nouvelle configuration est :");
            System.out.println("\""+boisson.toString()+"\"");
        }
    }

    // Supprimer une boisson
    public static void supprimer_boisson(ArrayList<Boisson> liste, int num) {
        Boisson boisson = liste.get(num);
        liste.remove(num);
        Boisson.decreaseCount();
        System.out.printf("La boisson \""+boisson.toString()+"\" a bien été supprimée de la machine.");
    }

    // Plus ou moins de sucre
    public static int ajuster_sucre(int nb_sucres) {
        System.out.println("Veuillez sélectionner le nombre de sucres à ajouter/supprimer [Actuellement "+nb_sucres+" sucres]\n(+ pour ajouter, - pour supprimer)");
        Scanner sc_sucre = new Scanner(System.in);
        // L'utilisateur doit entrer un nombre positif ou négatif pour ajuster son nombre de sucres
        int add_sucre=0;
        try {
            add_sucre = sc_sucre.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Votre choix est invalide. Le nombre de sucres ne sera pas modifié.");
        }
        nb_sucres+=add_sucre;
        if (nb_sucres<0) {
            nb_sucres=0;
        }
        System.out.println("Votre boisson contiendra "+nb_sucres+" sucres.");
        return nb_sucres;
    }

    // Programme principal

    public static void main(String[] args) {
        // Booléen vrai tant qu'on est dans le système
        boolean machine = true;
        // On initialise le stock de la machine à café
        Stock stock=new Stock(0,0,0,0);
        // On initialise la liste des boissons (vide)
        ArrayList<Boisson> liste_boissons = new ArrayList();

        /* Données de test */
        // On utilise les méthodes de classe pour ne pas avoir de printf
        stock.addCafe(2);
        stock.addLait(2);
        stock.addSucre(2);
        stock.addChocolat(2);

        liste_boissons.add(new Boisson("Café simple",1,2,0,0,0));
        liste_boissons.add(new Boisson("Cappucino",2,2,1,0,1));
        liste_boissons.add(new Boisson("Chocolat chaud",2,0,1,2,1));
        /*              */

        // Affichage des options de la machine
        System.out.printf("Bienvenue à CaféJava ! Nous proposons les services suivants :\n\n");
        do {
            System.out.printf("[1] Acheter une boisson\n");
            System.out.printf("[2] Ajouter une boisson\n");
            System.out.printf("[3] Modifier une boisson\n");
            System.out.printf("[4] Supprimer une boisson\n");
            System.out.printf("[5] Ajouter des ingrédients\n");
            System.out.printf("[6] Consulter le stock de la machine\n");
            System.out.printf("[7] Eteindre la machine\n\n");

            // Gestion des choix de l'utilisateur
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez votre choix : ");
            int choix;
            try {
                choix = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.printf("Votre choix est invalide. Veuillez choisir parmis les options suivantes :\n");
                continue;
            }

            // Action effectuée en fonction du choix
            switch (choix) {
                case 1: //Acheter une boisson
                    System.out.printf("Voici les boissons disponibles :\n");

                    // On boucle sur la liste des boissons et les affiche une à une
                    for(int i = 0; i < liste_boissons.size(); i++) {
                        System.out.println("["+(i+1)+"] "+liste_boissons.get(i).toString());
                    }
                    System.out.println("["+(liste_boissons.size()+1)+"] Annuler");

                    // On demande à l'utilisateur de choisir une boisson
                    System.out.printf("Votre choix : ");
                    int choix_boisson;
                    try {
                        choix_boisson = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.printf("Votre choix est invalide. Annulation. \n");
                        continue;
                    }

                    // Gestion de l'annulation
                    if (choix_boisson>liste_boissons.size() || choix_boisson<=0) {
                        System.out.println("Annulation.\n");
                        continue;
                    }

                    // On récupère la boisson choisie
                    Boisson boisson = liste_boissons.get(choix_boisson-1);

                    // On demande à l'utilisateur si il veut ajuster le nombre de sucres dans sa boisson
                    int nbsucres = ajuster_sucre(boisson.getSucre());

                    // On lui demande de payer : il peut ajouter plusieurs pièces
                    int prix = boisson.getPrix();
                    int monnaie=0;
                    System.out.printf("Veuillez payer "+prix+"€ pour obtenir votre boisson (Cette machine rend la monnaie) : \n");
                    try {
                        int add;
                        // Boucle tant que l'utilisateur n'a pas payé le montant correspondant
                        do {
                            add = sc.nextInt();
                            if (add<=0) {
                                System.out.println("Veuillez insérer une monnaie correcte !");
                            } else {
                                monnaie += add;
                                System.out.println("Inséré : " + monnaie + "€\n");
                                if (prix-monnaie>0) {
                                    System.out.println("Vous devez encore payer " + (prix - monnaie)+"€");
                                }
                            }
                        } while (monnaie < prix);

                        // On prépare la boisson
                        System.out.printf("Préparation de la boisson en cours...\n");
                        // On vérifie que le stock d'ingrédients est suffisant
                        if (stock.getCafe() >= boisson.getCafe() && stock.getLait() >= boisson.getLait() && stock.getChocolat() >= boisson.getChocolat() && stock.getSucre() >= nbsucres) {
                            // Préparer la boisson : enlever les ingrédients du stock
                            stock.addCafe(-boisson.getCafe());
                            stock.addLait(-boisson.getLait());
                            stock.addChocolat(-boisson.getChocolat());
                            stock.addSucre(-nbsucres);
                            System.out.printf("Voici votre boisson \"" + boisson.getNom() + "\".\n");
                            // Rendre le solde
                            if (monnaie - prix > 0) {
                                System.out.printf("Veuillez récupérer votre monnaie : " + (monnaie - prix) + "€.\n");
                            }
                        } else {
                            System.out.printf("Nous sommes désolés, mais le stock d'ingrédients est insuffisant pour préparer votre boisson.\n");
                            System.out.printf("Veuillez récupérer votre monnaie : " + monnaie + "€.\n");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Annulation. Veuillez récupérer votre monnaie : "+monnaie+"€.\n");
                    }

                    // En attente d'une nouvelle commande
                    System.out.printf("\n\nIci CaféJava. Veuillez choisir une option parmis celles proposées :\n");
                    break;

                case 2: // Ajouter une boisson
                    // On vérifie que l'utilisateur peut ajouter une boisson
                    if (Boisson.getCount()>=5) {
                        System.out.printf("La machine contient déjà 3 choix de boisson. Veuillez en supprimer une avant d'en ajouter une nouvelle.");
                    } else {
                        // On demande les infos nécéssaires
                        System.out.printf("Veuillez renseigner les différentes informations sur la boisson à ajouter :");
                        try {
                        System.out.printf("\n\tNom : ");
                        String nom = sc.next();
                        System.out.printf("\tPrix : ");
                        int prixboisson = sc.nextInt();
                        System.out.printf("\tNombre de cafés : ");
                        int cafe = sc.nextInt();
                        System.out.printf("\tNombre de laits : ");
                        int lait = sc.nextInt();
                        System.out.printf("\tNombre de chocolats : ");
                        int chocolat = sc.nextInt();
                        System.out.printf("\tNombre de sucres : ");
                        int sucre = sc.nextInt();

                        // On ajoute la boisson dans la machine
                        ajouter_boisson(liste_boissons,nom,prixboisson,cafe,lait,chocolat,sucre);
                        } catch (InputMismatchException e) {
                            System.out.printf("Vos informations ne sont pas valides. Annulation.");
                        }
                    }

                    // En attente d'une nouvelle commande
                    System.out.printf("\n\nIci CaféJava. Veuillez choisir une option parmis celles proposées :\n");
                    break;

                case 3: // Modifier une boisson
                    // On demande à l'utilisateur quelle boisson il souhaite modifier
                    System.out.printf("Quelle boisson souhaitez-vous modifier ? :\n");

                    // On boucle sur la liste des boissons et les affiche une à une
                    for(int i = 0; i < liste_boissons.size(); i++) {
                        System.out.println("["+(i+1)+"] "+liste_boissons.get(i).toString());
                    }
                    System.out.println("["+(liste_boissons.size()+1)+"] Annuler");

                    // On demande à l'utilisateur de choisir une boisson
                    System.out.printf("Votre choix : ");
                    int choix_boisson_modif;
                    try {
                        choix_boisson_modif = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.printf("Votre choix est invalide. Annulation. \n");
                        continue;
                    }

                    // Gestion de l'annulation
                    if (choix_boisson_modif>liste_boissons.size() || choix_boisson_modif<=0) {
                        System.out.println("Annulation.\n");
                        continue;
                    }

                    // On récupère la boisson choisie
                    Boisson boisson_modif = liste_boissons.get(choix_boisson_modif-1);

                    // On lui demande de rentrer les nouvelles informations
                    System.out.printf("Veuillez renseigner les différentes informations sur la boisson à modifier :\n");
                    try {
                        System.out.printf("\tPrix : ");
                        int prixboisson = sc.nextInt();
                        System.out.printf("\tNombre de cafés : ");
                        int cafe = sc.nextInt();
                        System.out.printf("\tNombre de laits : ");
                        int lait = sc.nextInt();
                        System.out.printf("\tNombre de chocolats : ");
                        int chocolat = sc.nextInt();
                        System.out.printf("\tNombre de sucres : ");
                        int sucre = sc.nextInt();

                        // On modifie la boisson choisie
                        modifier_boisson(boisson_modif,prixboisson,cafe,lait,chocolat,sucre);
                    } catch (InputMismatchException e) {
                        System.out.printf("Vos informations sont invalides. Annulation.");
                    }

                    // En attente d'une nouvelle commande
                    System.out.printf("\n\nIci CaféJava. Veuillez choisir une option parmis celles proposées :\n");
                    break;

                case 4: // Supprimer une boisson
                    // On demande à l'utilisateur quelle boisson il souhaite supprimer
                    System.out.printf("Quelle boisson souhaitez-vous modifier ? :\n");

                    // On boucle sur la liste des boissons et les affiche une à une
                    for(int i = 0; i < liste_boissons.size(); i++) {
                        System.out.println("["+(i+1)+"] "+liste_boissons.get(i).toString());
                    }
                    System.out.println("["+(liste_boissons.size()+1)+"] Annuler");

                    // On demande à l'utilisateur de choisir une boisson
                    System.out.printf("Votre choix : ");
                    int choix_boisson_suppr;
                    try {
                        choix_boisson_suppr = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.printf("Votre choix est invalide. Annulation.\n");
                        continue;
                    }

                    // Gestion de l'annulation
                    if (choix_boisson_suppr>liste_boissons.size() || choix_boisson_suppr<=0) {
                        System.out.println("Annulation.\n");
                        continue;
                    }

                    // On supprime la boisson
                    supprimer_boisson(liste_boissons,choix_boisson_suppr-1);

                    // En attente d'une nouvelle commande
                    System.out.printf("\n\nIci CaféJava. Veuillez choisir une option parmis celles proposées :\n");
                    break;

                case 5: // Ajouter un ingrédient
                    // On demande à l'utilisateur le nombre d'ingrédients qu'il souhaite ajouter
                    System.out.printf("Veuillez renseigner le nombre d'ingrédients à ajouter :\n");
                    try {
                        System.out.printf("\tNombre de cafés : ");
                        int nbcafe = sc.nextInt();
                        System.out.printf("\tNombre de laits : ");
                        int nblait = sc.nextInt();
                        System.out.printf("\tNombre de chocolats : ");
                        int nbchocolat = sc.nextInt();
                        System.out.printf("\tNombre de sucres : ");
                        int nbsucre = sc.nextInt();

                        // On ajoute les ingrédients
                        ajout_ingredient(stock,nbcafe,nblait,nbchocolat,nbsucre);
                    } catch (InputMismatchException e) {
                        System.out.printf("Vos informations sont invalides. Annulation.");
                    }

                    // En attente d'une nouvelle commande
                    System.out.printf("\n\nIci CaféJava. Veuillez choisir une option parmis celles proposées :\n");
                    break;

                case 6: // Afficher le stock
                    System.out.printf("Voici le stock actuel de la machine :\n");
                    // On affiche le stock de la machine
                    System.out.printf(stock.toString());

                    // En attente d'une nouvelle commande
                    System.out.printf("\n\nIci CaféJava. Veuillez choisir une option parmis celles proposées :\n");
                    break;

                case 7: // Partir du système
                    System.out.printf("\nÀ bientôt chez CaféJava !\n");
                    // On quitte la boucle
                    machine=false;
                    break;

                default:
                    System.out.println("Votre choix n'est pas valide. Veuillez choisir une option parmis celles proposées :\n");
            }
        }while(machine);
    }
}
