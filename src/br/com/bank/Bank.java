package br.com.bank;

import java.util.*;

public class Bank {

    public String name;
    private final List<Account> accounts;

    public Bank() {
        this.name = "Bank";
        this.accounts = new ArrayList<>();
    }

    public void createAccount(Client client, int typeAccount){
        if (existsAccountByCPF(client.cpf)) {
            System.out.println("Ja existe uma conta atrelada a esse cpf");
            return;
        }

        if (typeAccount == 1){
            accounts.add(new CurrentAccount(client));
        }else{
            accounts.add(new SavingsAccount(client));
        }
    }

    public void withdraw(Account account, double value){
        if (value < 0) {
            System.out.println("Erro durante o saque");
            return;
        }
        if (account != null) account.withdraw(value);
    }

    public void deposit(Account account, double value){
        if (value < 0) {
            System.out.println("Erro durante o deposito");
            return;
        }
        if (account != null) account.deposit(value);
    }

    public void transfer(Account account, double value, String destinyAccountNumber){
        Account destinyAccount = getClientAccountByNumberAccount(destinyAccountNumber);
        if (destinyAccount != null) account.transfer(value, destinyAccount);
    }

    public Account getClientAccountByNumberAccount(String numberAccount){
        for (Account account : accounts) {
            if (account.accountNumber.compareTo(numberAccount) == 0) return account;
        }
        return null;
    }

    public Account getClientAccountByCPF(String CPF){
        for (Account account : accounts) {
            if (account.client.cpf.compareTo(CPF) == 0) return account;
        }
        return null;
    }

    public boolean existsAccountByCPF(String cpf){
        for (Account account : accounts) {
            if (account.client.cpf.compareTo(cpf) == 0) return true;
        }
        return false;
    }

    public boolean existsAccountByNumberAccount(String numberAccount){
        for (Account account : accounts) {
            if (account.accountNumber.compareTo(numberAccount) == 0) return true;
        }
        return false;
    }

    public void showData(Account account){
        System.out.println(account.client.nome);
        System.out.println("R$ " + account.balance);
        System.out.println("Agencia "+ account.agencyNumber + "    Numero " + account.accountNumber);
    }

}
