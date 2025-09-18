package DTOs;


public class RegisterDTO {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    public RegisterDTO(String firstName, String lastName, String email, String password) {
        if (firstName == null || firstName.length() < 2)
            throw new IllegalArgumentException("Prénom invalide.");
        if (lastName == null || lastName.length() < 2)
            throw new IllegalArgumentException("Nom invalide.");
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("Email invalide.");
        if (password == null || password.length() < 6)
            throw new IllegalArgumentException("Mot de passe trop court (min 6 caractères).");

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
