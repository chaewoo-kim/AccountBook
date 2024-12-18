package com.chaewookim.AccoutBook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseRepository expenseRepository;
    private final UserinfoRepository userinfoRepository;
    private final String user_id = "ck12369";

    @GetMapping("/expense")
    String expense(Model model) {

        List<Expense> expenses = expenseRepository.findAllByUserId_UserIdAndDate(user_id, LocalDate.now());
        model.addAttribute("todayExpenses", expenses);
        System.out.println(expenses);
        System.out.println(LocalDate.now());

        return "expense.html";
    }

    @PostMapping("/addExpense")
    String addPost(@RequestParam Map<String, String> formData) {

        //나중에 로그인 기능이 생기면 아이디 값을 가져올 것이기 때문에 없앨 부분임
        Optional<Userinfo> userinfo = userinfoRepository.findByUserId(user_id);
        if (!userinfo.isPresent()){

            return "redirect:/main";

        }

        Userinfo add_userinfo = userinfo.get();

        Expense addExpense = new Expense();

        LocalDate date = LocalDate.parse(formData.get("date"));

        addExpense.setName(formData.get("name"));
        addExpense.setCategory(formData.get("category"));
        addExpense.setAmount(Integer.parseInt(formData.get("amount")));
        addExpense.setDate(date);
        addExpense.setMemo(formData.get("memo"));
        addExpense.setUserId(add_userinfo);

        expenseRepository.save(addExpense);


        return "redirect:/expense";
    }

    @GetMapping("/expenseStatus")
    String expenseStatus(Model model) {
        List<Expense> expenses = expenseRepository.findAllByUserId_UserId(user_id);
        model.addAttribute("expenseList", expenses);

        return "expenseStatus";
    }

    @PostMapping("/deleteExpenseStatus")
    String deleteExpenseStatus(@RequestParam(value = "selected_unfixed[]", required = false) List<Long> unfixed, @RequestParam(value = "selected_fixed[]", required = false) List<Long> fixed) {

        if (unfixed != null) {
            for (Long id : unfixed) {
                expenseRepository.deleteById(id);
            }
        }

        if (fixed != null) {
            for (Long id : fixed) {
                expenseRepository.deleteById(id);
            }
        }

        return "redirect:/expenseStatus";
    }

    @PostMapping("/addExpenseStatus")
    String addExpenseStatus(@RequestParam Map<String, String> formData) {

        //나중에 로그인 기능이 생기면 아이디 값을 가져올 것이기 때문에 없앨 부분임
        Optional<Userinfo> userinfo = userinfoRepository.findByUserId(user_id);
        if (!userinfo.isPresent()){

            return "redirect:/main";

        }

        Userinfo add_userinfo = userinfo.get();

        Expense addExpense = new Expense();

        LocalDate date = LocalDate.parse(formData.get("date"));

        addExpense.setName(formData.get("name"));
        addExpense.setCategory(formData.get("category"));
        addExpense.setAmount(Integer.parseInt(formData.get("amount")));
        addExpense.setDate(date);
        addExpense.setMemo(formData.get("memo"));
        addExpense.setUserId(add_userinfo);

        expenseRepository.save(addExpense);


        return "redirect:/expenseStatus";
    }


}
