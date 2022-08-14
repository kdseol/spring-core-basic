package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    /*@Autowired
    private MemberRepository memberRepository;
    * 필드주입(이름 그대로 필드에 바로 주입하는 방법)
    - 코드가 간결해서 많은 개발자들을 유혹하지만, 외부에서 변경이 불가능해서 테스트하기 힘들다는 치명적인 단점이 있다.
    - DI프레임워크(=spring)가 없으면 아무것도 할 수 없다.(ex. 태스트코드)
    -> 사용하지 말자! (but, 부득이한 Configuration.class)
    */

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    //@Autowired /생성자 주입, 생성자가 딱 하나일때는 Autowired를 생략할 수 있다(자동으로 의존관계 주입이 일어난다).
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /*
    일반 메서드 주입
    - 한번에 여러 필드를 주입받을 수 있다.
    - 일반적으로 사용하지 않는다.
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    */

    /*
    //@Autowired //수정자 주입
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //@Autowired //수정자 주입
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    */

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
