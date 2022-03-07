package japbook.jpashop.service;

import japbook.jpashop.domain.item.Book;
import japbook.jpashop.domain.item.Item;
import japbook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item)
    {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity)//UpdateItemDto 사용해도됨
    {
        Item findItem = itemRepository.findOne(itemId); //영속 상태
        //findItem.setPrice(param.getPrice()); // 값을 세팅 하면 transactional에 의해서 트랜잭션이 커밋이됨 JPA는 flush를 날려서 변경을 dp에 업데이트한다
        //findItem.setName(param.getName());
        //findItem.setStockQuantity(param.getStockQuantity());

        //setter 없이 메소드 같은 것을 만들어서 값을 변경하기
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

    }



    public List<Item> findItem()
    {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId)
    {
        return itemRepository.findOne(itemId);
    }
}
