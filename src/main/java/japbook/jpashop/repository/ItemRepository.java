package japbook.jpashop.repository;

import japbook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item)
    {
        if (item.getId() == null)
        {
            em.persist(item);
        }
        else
        {
            em.merge(item);//업데이트 비슷한 것, 실무에서 쓸 일 거의 없따.
        }
    }

    public Item findOne(Long id)
    {
        return em.find(Item.class, id);
    }

    public List<Item> findAll()
    {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
