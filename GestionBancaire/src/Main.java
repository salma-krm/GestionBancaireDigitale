import Module.Account;
import Module.User;
import Module.Account.AccountType;
import Repository.Implements.InMemoryAccountRepository;
import Repository.Implements.InMemoryTransactionRepository;
import Repository.Implements.InMemoryUserRepository;
import Repository.Interfaces.AccountRepository;
import Repository.Interfaces.TransactionRepository;
import Repository.Interfaces.UserRepository;
import Services.AccountService;
import Services.AuthService;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static UserRepository userRepository = new InMemoryUserRepository();
    static AccountRepository accountRepository = new InMemoryAccountRepository();
    static TransactionRepository transactionRepository = new InMemoryTransactionRepository();
    static AuthService authService = new AuthService(userRepository);
    static AccountService accountService = new AccountService(accountRepository, transactionRepository);
    static User currentUser;

    public static void main(String[] args) {
        int choix;
        do {
            System.out.println("\n--- Accueil ---");
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
        System.out.println(" Inscription réussie !");
    }

    private static void loginUser() {
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();

        User user = authService.login(new User(null, null, email, password));
        if (user != null) {
            System.out.println(" Connexion réussie !");
            currentUser = user;
            showUserMenu();
        } else {
            System.out.println(" Échec de la connexion.");
        }
    }

    private static void showUserMenu() {
        int choix;
        do {
            System.out.println("\n--- Menu Utilisateur ---");
            System.out.println("1. Créer un compte");
            System.out.println("2. Dépôt");
            System.out.println("3. Retrait");
            System.out.println("4. Voir les transactions");
            System.out.println("5. Modifier le profil");
            System.out.println("6. Déconnexion");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> createAccount();
                case 2 -> deposer();
                case 3 -> retirer();
                case 4 -> afficherTransactions();
                case 5 -> updateUser();
                case 6 -> {
                    System.out.println("Déconnexion...");
                    currentUser = null;
                }
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 6);
    }

    private static void createAccount() {
        System.out.println("Type de compte :");
        System.out.println("1. COURANT");
        System.out.println("2. EPARGNE");
        System.out.println("3. SALAIRE");
        System.out.print("Choix : ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        AccountType type = switch (typeChoice) {
            case 1 -> AccountType.COURANT;
            case 2 -> AccountType.EPARGNE;
            case 3 -> AccountType.SALAIRE;
            default -> null;
        };

        if (type == null) {
            System.out.println(" Type invalide.");
            return;
        }

        Account account = new Account(currentUser, type);
        accountService.create(account);
        currentUser.addAccount(account);
        System.out.println(" Compte créé avec succès !");
        System.out.println("ID du compte : " + account.getAccountId());
    }


    private static void deposer() {
        if (currentUser.getAccounts().isEmpty()) {
            System.out.println(" Aucun compte disponible.");
            return;
        }

        System.out.print("Montant à déposer : ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount <= 0) {
            System.out.println(" Le montant doit être positif.");
            return;
        }

        Account result = accountService.deposer(currentUser, amount);
        if (result != null) {
            System.out.println(" Dépôt effectué. Nouveau solde : " + result.getBalance() + " €");
        } else {
            System.out.println(" Échec du dépôt.");
        }
    }

    private static void retirer() {
        if (currentUser.getAccounts().isEmpty()) {
            System.out.println("Aucun compte disponible.");
            return;
        }

        System.out.print("Montant à retirer : ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount <= 0) {
            System.out.println(" Le montant doit être positif.");
            return;
        }

        Account result = accountService.retrait(currentUser, amount);
        if (result != null) {
            System.out.println(" Retrait effectué. Nouveau solde : " + result.getBalance() + " €");
        } else {
            System.out.println(" Échec du retrait. Solde insuffisant.");
        }
    }

    private static void afficherTransactions() {
        if (currentUser.getAccounts().isEmpty()) {
            System.out.println("Aucune transaction : aucun compte trouvé.");
            return;
        }

        var all = transactionRepository.getAll().stream()
                .filter(t -> t.account.getUser().getId().equals(currentUser.getId()))
                .toList();

        if (all.isEmpty()) {
            System.out.println("Aucune transaction enregistrée.");
        } else {
            System.out.println("Liste des transactions :");
            all.forEach(System.out::println);
        }
    }

    private static void updateUser() {
        System.out.print("Nouveau prénom : ");
        String firstName = scanner.nextLine();
        System.out.print("Nouveau nom : ");
        String lastName = scanner.nextLine();
        System.out.print("Nouvel email : ");
        String email = scanner.nextLine();
        System.out.print("Nouveau mot de passe : ");
        String password = scanner.nextLine();

        User updated = new User(firstName, lastName, email, password);
        updated.setId(currentUser.getId());

        User result = authService.update(updated);

        if (result != null) {
            currentUser = result;
            System.out.println(" Profil mis à jour.");
        } else {
            System.out.println(" Échec de la mise à jour.");
        }
    }
}
