package Corner.Spring_Study.repository;

import Corner.Spring_Study.domain.Member;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //JPA 쓰기 위해서 EntityManager 주입

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    // 객체(Member Entity)를 대상으로 쿼리를 보냄
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // 객체 자체를 select
                .getResultList();
    }
}