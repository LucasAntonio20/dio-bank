package br.com.bank;

public abstract class Account {

    private static int SEQUENCE = 1;

    protected Client client;
    protected String agencyNumber;
    protected String accountNumber;
    protected double balance;

    protected void withdraw(double value){
        if (balance < value) {
            System.out.println("Falha ao tentar sacar - nao possui saldo suficiente!");
            return;
        }
        this.balance -= value;
    }

    protected void deposit(double value){
        this.balance += value;
    }

    protected void transfer(double value, Account account){
        this.withdraw(value);
        account.deposit(value);
    }

    public Account(Client client) {
        this.agencyNumber = "001";
        this.accountNumber = generateAccountNumber();
        SEQUENCE++;
        this.client = client;
    }

    protected static String generateAccountNumber(){
        if (SEQUENCE < 10){
            return "000" + SEQUENCE;
        } else if (SEQUENCE < 100) {
            return "00" + SEQUENCE;
        } else if (SEQUENCE < 1000) {
            return "0" + SEQUENCE;
        } else if (SEQUENCE < 10000) {
            return Integer.toString(SEQUENCE);
        }else{
            throw new RuntimeException();
        }
    }

}
