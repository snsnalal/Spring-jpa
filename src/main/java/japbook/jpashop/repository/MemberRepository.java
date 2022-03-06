package japbook.jpashop.repository;


import japbook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor //스프링부트를 사용하면 엔티티 매니저에도 사용가능
public class MemberRepository {

    //@PersistenceContext //스프링이 엔티티 매니저를 생성해서 주입해줌
    private final EntityManager em;

    public void save(Member member)
    {
        em.persist(member);
    }

    public Member findOne(Long id)
    {
        return em.find(Member.class, id);
    }

    public List<Member> findAll()
    {
        return em.createQuery("select m from Member m", Member.class)//from의 대상인 엔티티 객체를 대상으로 쿼리를 한다.
                .getResultList();
    }

    public List<Member> findByName(String name)
    {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
