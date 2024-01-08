package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor //final 붙은 값들의 생성자들을 자동생성 해주는 애노테이션
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository; // DIP 위반 방지를 위해 인터페이스에만 의존하도록 선언
    private final DiscountPolicy discountPolicy; // DIP 위반 방지를 위해 인터페이스에만 의존하도록 선언

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
