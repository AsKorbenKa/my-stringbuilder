import java.math.BigDecimal;

public class BankAccount {
    public void deposit(Account account, BigDecimal amount) {
        account.setBigDecimal(account.getBigDecimal().add(amount));
    }

    public void withdraw(Account account, BigDecimal amount) {
        if (account.getBigDecimal().compareTo(amount) < 0) {
            throw new RuntimeException("Недостаточно средств для снятия со счета.");
        }
        account.setBigDecimal(account.getBigDecimal().subtract(amount));
    }

    public BigDecimal getBalance(Account account) {
        return account.getBigDecimal();
    }
}
