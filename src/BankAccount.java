import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final Lock lock = new ReentrantLock();
    private BigDecimal bigDecimal;

    public BankAccount(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public void deposit(BigDecimal amount) {
        lock.lock();
        try {
            if (amount.compareTo(BigDecimal.valueOf(0)) > 0) {
                bigDecimal.add(amount);
            }
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(BigDecimal amount) {
        lock.lock();
        try {
            if (bigDecimal.compareTo(amount) < 0) {
                throw new RuntimeException("Недостаточно средств для снятия со счета.");
            }
            bigDecimal.subtract(amount);
        } finally {
            lock.unlock();
        }
    }

    public BigDecimal getBalance() {
        return bigDecimal;
    }
}
