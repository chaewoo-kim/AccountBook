package com.chaewookim.AccoutBook;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeRepository incomeRepository;
    private final UserinfoRepository userinfoRepository;
    public final String user_id = "ck12369"; //나중에 로그인해서 user_id의 변수화 가능해지면 수정할 부분


    @GetMapping("/income")
    String income(Model model) {

        List<Income> incomes = incomeRepository.findAllByUserId_UserId(user_id);
        model.addAttribute("incomes", incomes);

        return "income.html";
    }

    @PostMapping("/addIncome")
    String addPost(@RequestParam Map<String, String> formData, Model model) {

        //나중에 로그인 기능이 생기면 아이디 값을 가져올 것이기 때문에 없앨 부분임
        Optional<Userinfo> userinfo = userinfoRepository.findByUserId(user_id);
        if (!userinfo.isPresent()){

            return "redirect:/main";

        }

        //새롤운 데이터 저장하는 부분
        Userinfo add_userinfo =  userinfo.get();

        Income addIncome = new Income();
        addIncome.setUserId(add_userinfo);
        addIncome.setName(formData.get("name"));
        addIncome.setBank(formData.get("bank"));
        addIncome.setAccount_number(Long.parseLong(formData.get("account_number")));
        addIncome.setAmount(Integer.parseInt(formData.get("amount")));
        addIncome.setMonthly_spending_upper_limit(Integer.parseInt(formData.get("monthly_spending_upper_limit")));
        System.out.println(formData.get("is_fixed"));
        addIncome.setIs_fixed(Boolean.parseBoolean(formData.get("is_fixed")));


        incomeRepository.save(addIncome);


        return "redirect:/income";
    }

    @PostMapping("/deleteIncome")
    String deletePost(@RequestParam(value = "selected_unfixed[]", required = false) List<Long> unfixed, @RequestParam(value = "selected_fixed[]", required = false) List<Long> fixed) {

        if (unfixed != null) {
            for (Long id : unfixed) {
                System.out.println(id);
                incomeRepository.deleteById(id);
            }
        }

        if (fixed != null) {
            for (Long id : fixed) {
                System.out.println(id);
                incomeRepository.deleteById(id);
            }
        }

        return "redirect:income";
    }
}
