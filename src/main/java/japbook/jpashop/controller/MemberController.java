package japbook.jpashop.controller;


import japbook.jpashop.domain.Address;
import japbook.jpashop.domain.Member;
import japbook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){//Model 컨트롤러에서 뷰로 넘어갈때 데이터를 실어서 보냄

        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) //NotEmpty 같은 것들을 찾아서 validation해줌
    {                                                                  //에러가 생기면 result에 담긴다. 엔티티를 최대한 순수하게 유지해야한다.
                                                                        //DTO 권장
        if (result.hasErrors())
        {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        //딱 필요한 데이터만 넘기는 것을 추천
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("members")
    public String list(Model model)//DTO로 변환하는 것을 권장, API를 만들 떄는 엔티티 외부로 반환 금지
    {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
