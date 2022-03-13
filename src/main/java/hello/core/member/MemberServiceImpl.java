package hello.core.member;

import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    //@Autowired // 생성자에 붙여주면 스프링이 자동으로 의존관계 주입을 해 준다. (== ac.getBean(MemberRepository.class))
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

/*
 * 생성자 주입(Field Injection)
    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

 * 필드 주입 (Construction Injection)
    @Autowired
    private MemberRepository memberRepository;

 * 수정자 주입(Setter Injection)
   private MemberRepository memberRepository;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

  * As of Spring 4.3, classes with a single constructor can omit the @Autowired annotation

  * 생성자 주입을 권장하는 이유
   1. 순환참조 방지 / 2. 불변성 / 3. 테스트코드 작성

*/