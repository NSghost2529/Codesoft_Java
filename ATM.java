public class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    // User interface and methods for each option
    public void displayOptions() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void withdraw(double amount) {
        if (bankAccount.withdraw(amount)) {
            System.out.println("Withdrawal of " + amount + " successful.");
        } else {
            System.out.println("Insufficient balance for withdrawal.");
        }
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
        System.out.println("Deposit of " + amount + " successful.");
    }

    public void checkBalance() {
        double balance = bankAccount.checkBalance();
        System.out.println("Your current balance is " + balance + ".");
    }

    public void userInterface() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayOptions();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 2:
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using our ATM.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}