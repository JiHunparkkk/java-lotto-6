package lotto.model;

import java.util.List;

public class WinningLotto {

    private final WinningNumber winningNumber;
    private final BonusNumber bonusNumber;

    public WinningLotto(WinningNumber winningNumber, BonusNumber bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getWinningNumber() {
        return winningNumber.getWinning();
    }

    public int getBonusNumber() {
        return bonusNumber.getBonus();
    }
}
