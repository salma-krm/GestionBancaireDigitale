package DTOs;

public class LoginDTO {
    private final String email;
    private final String password;

    public LoginDTO(String email, String password) {
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("Email invalide.");
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Mot de passe requis.");
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
