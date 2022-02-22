package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void isVip() {
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        int discountPrice = discountPolicy.discount(member, 10000);

        Assertions.assertThat(discountPrice).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void isNotVip() {
        Member member = new Member(1L, "memberVIP", Grade.BASIC);

        int discountPrice = discountPolicy.discount(member, 10000);

        Assertions.assertThat(discountPrice).isEqualTo(0);
    }
}