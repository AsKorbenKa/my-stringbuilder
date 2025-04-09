import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConcurrentBank {
    private final List<BankAccount> accounts = new ArrayList<>();

    public BankAccount createAccount(BigDecimal amount) {
        BankAccount createdAccount =  new BankAccount(amount);
        accounts.add(createdAccount);
        return createdAccount;
    }

    public void transfer(BankAccount from, BankAccount to, BigDecimal amount) {
        from.withdraw(amount);
        to.deposit(amount);
    }

    public BigDecimal getTotalBalance() {
        return accounts.stream()
                .map(BankAccount::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
