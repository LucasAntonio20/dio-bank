import br.com.bank.Bank;
import br.com.bank.Client;
import br.com.bank.Account;

import java.util.Scanner;

public class VirtualBank {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Bank bank = new Bank();
        int op = 0;

        while (op != 4){
            menu(1);
            menu(999);
            op = in.nextInt();in.nextLine();
            switch (op) {
                case 1:
                    menu(2);
                    menu(999);
                    int typeAccount = in.nextInt(); in.nextLine();
                    System.out.print("Digite seu CPF: ");
                    String CPF = in.nextLine();
                    while (!Client.isCPF(CPF)){
                        System.out.println("CPF invalido! Digite novamente: ");
                        CPF = in.nextLine();
                    }
                    System.out.print("Digite o seu nome completo: ");
                    String nome = in.nextLine();
                    bank.createAccount(new Client(CPF, nome.toUpperCase()), typeAccount);
                    break;
                case 2:
                    System.out.print("Digite seu CPF: ");
                    CPF = in.nextLine();
                    while (!Client.isCPF(CPF)){
                        System.out.print("CPF invalido! Digite novamente: ");
                        CPF = in.nextLine();
                    }
                    int op2;
                    if (bank.existsAccountByCPF(CPF)) {
                        Account actualAccount = bank.getClientAccountByCPF(CPF);
                        do {
                            bank.showData(actualAccount);
                            menu(3);
                            menu(999);
                            op2 = in.nextInt(); in.nextLine();
                            switch (op2){
                                case 1:
                                    System.out.print("Digite o valor: ");
                                    double value = in.nextDouble(); in.nextLine();
                                    bank.withdraw(actualAccount, value);
                                    break;
                                case 2:
                                    System.out.print("Digite o valor: ");
                                    value = in.nextDouble(); in.nextLine();
                                    bank.deposit(actualAccount, value);
                                    break;
                                case 3:
                                    System.out.print("Digite o numero da conta: ");
                                    String transferAccountNumber = in.nextLine();
                                    if (!bank.existsAccountByNumberAccount(transferAccountNumber)){
                                        System.out.println("Nao encontrei a conta que você estava querendo depositar");
                                        break;
                                    }
                                    System.out.print("Digite o valor: ");
                                    value = in.nextDouble(); in.nextLine();
                                    bank.transfer(actualAccount, value, transferAccountNumber);
                                    break;
                                case 4:
                                    break;
                                default:
                                    System.out.println("Opcao invalida!");
                                    break;
                            }
                        } while (op2 != 4);
                    }else{
                        System.out.println("Desculpe mas não encontrei uma conta vinculada a esse CPF");
                    }
                    break;
                case 3:
                    System.out.println("Digite o numero da conta que deseja depositar: ");
                    String accountNumber = in.nextLine();
                    if (!bank.existsAccountByNumberAccount(accountNumber)) {
                        System.out.println("Desculpe mas nao consegui achar a conta digitada!");
                        break;
                    }
                    System.out.println("Agora digite o valor que deseja depositar");
                    double value = in.nextDouble(); in.nextLine();
                    Account destinyAccount = bank.getClientAccountByNumberAccount(accountNumber);
                    bank.deposit(destinyAccount, value);
                    break;
                case 4:
                    System.out.println("Muito Obrigado!");
                    break;
                default:
                    System.out.println("Opcao invalida! Por favor digite novamente!");
                    break;
            }
        }
    }

    public static void menu(int menuNumber){
        switch (menuNumber) {
            case 1:
                System.out.println("*----------Bem-Vindo----------*");
                System.out.println("| 1 - Criar Conta             |");
                System.out.println("| 2 - Entrar Na Sua Conta     |");
                System.out.println("| 3 - Fazer Deposito rapido   |");
                System.out.println("| 4 - Sair                    |");
                System.out.println("*-----------------------------*");
                break;
            case 2:
                System.out.println("*-----------------------------*");
                System.out.println("| 1 - Conta Corrente          |");
                System.out.println("| 2 - Conta poupanca          |");
                System.out.println("*-----------------------------*");
                break;
            case 3:
                System.out.println("*-----------------------------*");
                System.out.println("| 1 - Sacar                   |");
                System.out.println("| 2 - Depositar               |");
                System.out.println("| 3 - Transferir              |");
                System.out.println("| 4 - Sair                    |");
                System.out.println("*-----------------------------*");
                break;
            case 999:
                System.out.print("Digite sua opcao: ");
                break;
        }

    }
}
