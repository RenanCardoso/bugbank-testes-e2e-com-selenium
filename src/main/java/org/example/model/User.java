package org.example.model;
import org.example.utils.GeradorDeDados;

public class User {

    private String email;
    private String name;
    private String password;
    private String numberAccount;
    private String digitAccount;

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public void setDigitAccount(String digitAccount) {
        this.digitAccount = digitAccount;
    }

    public String getNumberAccount() {
        return numberAccount;
    }
    public String getDigitAccount() {
        return digitAccount;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public User generateRandomUser(){
        this.email = GeradorDeDados.gerarEmail();
        this.name = GeradorDeDados.gerarPrimeiroNome();
        this.password = GeradorDeDados.gerarSenha();
        return this;
    }
}
