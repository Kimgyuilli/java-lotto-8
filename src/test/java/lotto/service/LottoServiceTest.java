package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    @Test
    void 구입_금액에_맞는_개수만큼_로또를_생성한다() {
        Money money = new Money(5000);

        List<Lotto> lottos = lottoService.purchase(money);

        assertThat(lottos).hasSize(5);
    }

    @Test
    void 천원으로_1개의_로또를_생성한다() {
        Money money = new Money(1000);

        List<Lotto> lottos = lottoService.purchase(money);

        assertThat(lottos).hasSize(1);
    }

    @Test
    void 생성된_로또는_정상적인_로또_번호를_가진다() {
        Money money = new Money(3000);

        List<Lotto> lottos = lottoService.purchase(money);

        assertThat(lottos).hasSize(3);
        for (Lotto lotto : lottos) {
            assertThat(lotto.getNumbers()).hasSize(6);
            assertThat(lotto.getNumbers()).allMatch(number -> number >= 1 && number <= 45);
        }
    }

    @Test
    void 생성된_로또_번호는_중복되지_않는다() {
        Money money = new Money(10000);

        List<Lotto> lottos = lottoService.purchase(money);

        for (Lotto lotto : lottos) {
            assertThat(lotto.getNumbers())
                    .hasSize(6)
                    .doesNotHaveDuplicates();
        }
    }

    @Test
    void 대량_로또_구매도_정상_동작한다() {
        Money money = new Money(100000);

        List<Lotto> lottos = lottoService.purchase(money);

        assertThat(lottos).hasSize(100);
        for (Lotto lotto : lottos) {
            assertThat(lotto.getNumbers())
                    .hasSize(6)
                    .doesNotHaveDuplicates()
                    .allMatch(number -> number >= 1 && number <= 45);
        }
    }
}
