package japbook.jpashop.service;

import japbook.jpashop.domain.Member;
import japbook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //기본적으로 트랜잭션 안에서 이루어짐, 읽기 전용 트랜잭션에 사용
//@AllArgsConstructor// 모든 필드 생성자 생성
@RequiredArgsConstructor //final있는 것만 생성자 생성
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional //기본이 먼저 적용됨 readOnly = false
    public Long join(Member member)
    {
        validateDuplicateMember(member);//중복 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //예외처리
        List<Member> findMembers = memberRepository.findByName(member.getName()); //DB에서 제약조건 거는 것이 안전
        if (!findMembers.isEmpty())
        {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId)
    {
        return memberRepository.findOne(memberId);
    }
}
