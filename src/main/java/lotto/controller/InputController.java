package lotto.controller;

import static lotto.util.Conversion.stringToInt;
import static lotto.util.Conversion.stringToList;
import static lotto.util.message.Guide.BONUS_NUMBER;
import static lotto.util.message.Guide.PURCHASE_AMOUNT;
import static lotto.util.message.Guide.WINNING_NUMBER;
import static lotto.view.InputView.input;

import java.util.List;
import lotto.model.BonusNumber;
import lotto.model.Lottos;
import lotto.model.Purchase;
import lotto.model.User;
import lotto.model.WinningLotto;
import lotto.model.WinningNumber;
import lotto.view.OutputView;

public class InputController {

    public static User makeUser() {
        Purchase purchase = payMoney();
        Lottos lottos = buyLottos(purchase);

        return new User(purchase, lottos);
    }

    public static WinningLotto makeWinning() {
        WinningNumber winningNumber = getWinningLotto();
        BonusNumber bonusNumber = getBonusLotto(winningNumber);

        return new WinningLotto(winningNumber, bonusNumber);
    }


    private static Purchase payMoney() {
        CheckedSupplier<Purchase> purchaseSupplier = () -> {
            int money = stringToInt(input(PURCHASE_AMOUNT.getMessage()));
            return new Purchase(money);
        };

        return handleException(purchaseSupplier);
    }

    private static Lottos buyLottos(Purchase purchase) {
        int count = purchase.getCount();

        return new Lottos(Lottos.generator(count));
    }

    private static WinningNumber getWinningLotto() {
        CheckedSupplier<WinningNumber> WinningNumberSupplier = () -> {
            List<Integer> list = stringToList(input(WINNING_NUMBER.getMessage()));
            return new WinningNumber(list);
        };

        return handleException(WinningNumberSupplier);
    }

    private static BonusNumber getBonusLotto(WinningNumber winningNumber) {
        CheckedSupplier<BonusNumber> BonusNumberSupplier = () -> {
            String bonusNumber = input(BONUS_NUMBER.getMessage());
            return new BonusNumber(stringToInt(bonusNumber), winningNumber.getWinning());
        };

        return handleException(BonusNumberSupplier);
    }

    private static <T> T handleException(CheckedSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return handleException(supplier);
        }
    }

    @FunctionalInterface
    public interface CheckedSupplier<T> {
        T get() throws IllegalArgumentException;
    }

}
