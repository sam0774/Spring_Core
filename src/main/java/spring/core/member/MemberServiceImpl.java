package spring.core.member;

public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository; //클래스 내에서 객체를 직접 주입시켰던 코드를 지우고,

    //생성자를 통해서 객체를 주입받는다.
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
}
