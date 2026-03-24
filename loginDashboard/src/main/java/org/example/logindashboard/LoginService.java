package org.example.logindashboard;

public class LoginService {
    public String validarEmail(String email, String password){
        if (email == null || email.isEmpty()){
            return "Email cannot be empty";
        }
        if (!email.contains("@") || !email.contains(".")){
            return "Email not recognized";
        }
        if (email.length() < 4){
            return "Has to be at least 4 characters long";
        }
        if (password == null || password.isEmpty()){
            return "Password cannot be empty";
        }
        if (password.length() < 6){
            return "Pasword has to be at least 6 characters long.";
        }

        return null;
    }
}
