import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {
    private final Lock lock = new ReentrantLock();
    private final BankAccount bankAccount = new BankAccount();
    private final List<Account> accounts = new ArrayList<>();

    public Account createAccount(BigDecimal amount) {
        Account createdAccount =  new Account(amount);
        accounts.add(createdAccount);
        return createdAccount;
    }

    public void transfer(Account from, Account to, BigDecimal amount) {
        lock.lock();
        try {
            bankAccount.withdraw(from, amount);
            bankAccount.deposit(to, amount);
        } finally {
            lock.unlock();
        }
    }

    public BigDecimal getTotalBalance() {
        return accounts.stream()
                .map(Account::getBigDecimal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
