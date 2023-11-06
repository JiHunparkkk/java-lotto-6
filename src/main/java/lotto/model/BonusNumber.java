package lotto.model;

import static lotto.util.message.Digit.LOTTO_RANGE_END;
import static lotto.util.message.Digit.LOTTO_RANGE_START;
import static lotto.util.message.Error.MUST_LOTTO_RANGE;

import java.util.List;

public class BonusNumber {

    private final int bonus;

    public BonusNumber(int bonus, List<Integer> winningNumber) {
        validate(bonus, winningNumber);
        this.bonus = bonus;
    }

    private void validate(int bonus, List<Integer> winningNumber) {
        validateNumberRange(bonus);
    }

    private void validateNumberRange(int bonus) {
        if (!(LOTTO_RANGE_START.getNumber() <= bonus && bonus <= LOTTO_RANGE_END.getNumber())) {
            throw new IllegalArgumentException(
                    MUST_LOTTO_RANGE.getError(LOTTO_RANGE_START.getNumber(), LOTTO_RANGE_END.getNumber()));
        }
    }
}
