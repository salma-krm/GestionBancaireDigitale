import Module.User;
import Module.Account;
import Repository.Implements.InMemoryAccountRepository;
import Repository.Implements.InMemoryUserRepository;
import Repository.Interfaces.AccountRepository;
import Repository.Interfaces.UserRepository;
import Services.AccountService;
import Services.AuthService;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static UserRepository userRepository = new InMemoryUserRepository();
    static AccountRepository accountRepository = new InMemoryAccountRepository();
    static AuthService authService = new AuthService(userRepository);
    static AccountService accountService = new AccountService(accountRepository);
    static User currentUser;

    public static void main(String[] args) {
        int choix;
        do {
            System.out.println("\n--- Accueil (non connecté) ---");
            System.out.println("1. Inscription");
            System.out.println("2. Connexion");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 0 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private static void registerUser() {
        System.out.print("Prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Nom : ");
        String lastName = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();

        User newUser = new User(firstName, lastName, email, password);
        authService.register(newUser);
        System.out.println("Inscription réussie !");
    }

    private static void loginUser() {
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();

        User user = authService.login(new User(null, null, email, password));
        if (user != null) {
            System.out.println("Connexion réussie !");
            currentUser = user;
            showUserMenu();
        } else {
            System.out.println("Échec de la connexion.");
        }
    }

    private static void showUserMenu() {
        int choix;
        do {
            System.out.println("\n--- Menu Utilisateur ---");
            System.out.println("1. Créer un compte");
            System.out.println("2. Voir les transactions ");
            System.out.println("3. edit profil");
            System.out.println("4. Déconnexion");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> createAccount();
                case 2 -> System.out.println("Fonction non encore implémentée.");
                case 3 -> {

                }
                case 4-> {
                    System.out.println("Déconnexion...");
                    currentUser = null;
                }
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 3);
    }

    private static void createAccount() {
        Account account = new Account(currentUser);
        accountService.create(account);
        System.out.println("Compte créé avec succès !");
        System.out.println("ID du compte : " + account.getAccountId());
    }
}
