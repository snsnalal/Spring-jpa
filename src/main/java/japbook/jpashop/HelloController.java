package japbook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model)
    {
        model.addAttribute("data", "hello!!");//data라는 키에 hello!! 라는 값을 넘긴다.
        return "hello";//화면(페이지) 이름
    }
}
