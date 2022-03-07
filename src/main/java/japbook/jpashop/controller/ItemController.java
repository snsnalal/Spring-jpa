package japbook.jpashop.controller;


import japbook.jpashop.domain.item.Book;
import japbook.jpashop.domain.item.Item;
import japbook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model)
    {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form)
    {
        Book book = new Book(); //setter 없애고 생성자로 만드는게 나은 예제
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);

        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model)
    {
        List<Item> items = itemService.findItem();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model)
    {
        Book item = (Book) itemService.findOne(itemId);

        BookForm form  = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") BookForm form, @PathVariable Long itemId) //ID가 조작돼서 넘어올 수도 있기 때문에 권한이 있는지 없는지 체크 해야함
    {
        /*
        Book book = new Book();

        book.setId(form.getId());
        book.setName(form.getName());
        book.setAuthor(form.getAuthor());
        book.setStockQuantity(form.getStockQuantity());
        book.setPrice(form.getPrice());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);

         */

        //훨씬 깔끔한 방식
        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());

        return "redirect:/items";
    }

}
