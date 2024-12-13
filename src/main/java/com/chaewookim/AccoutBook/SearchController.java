package com.chaewookim.AccoutBook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final IncomeStatusRepository incomeStatusRepository;
    private final ExpenseRepository expenseRepository;
    private final UserinfoRepository userinfoRepository;

    private final String user_id = "ck12369";

    @GetMapping("/search")
    String search(@RequestParam String input, Model model) {
        LocalDate date = null;
        String type = null;
        Integer amount = null;
        String symbol = "";
        String name = "";

        Optional<Userinfo> userinfo = userinfoRepository.findByUserId(user_id);
        if (!userinfo.isPresent()){
            return "redirect:/main";
        }


        List<IncomeStatus> incomeStatuses = null;
        List<Expense> expenses = null;


        String [] words = input.split(", ");

        for (String word : words) {
            if ("날짜".equals(word.substring(0,2))) {
                date = LocalDate.parse(word.substring(4, word.length()-1));
                continue;
            } else if ("타입".equals(word.substring(0,2))) {
                type = word.substring(4, word.length()-1);
                continue;
            } else if ("금액".equals(word.substring(0,2))) {
                symbol = word.substring(2, 3);
                amount = Integer.parseInt(word.substring(4, word.length()-1));
                continue;
            } else if ("이름".equals(word.substring(0,2))) {
                name = word.substring(4, word.length()-1);
                continue;
            }
        }


        if ("수입".equals(type)) {
            if (symbol.equals(">")) {
                if (date == null) {
                    if (name.isEmpty()) {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndAmountGreaterThanEqual(userinfo.get(), amount);
                    } else {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndNameAndAmountGreaterThanEqual(userinfo.get(), name, amount);
                    }
                } else {
                    if (name.isEmpty()) {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndDateAndAmountGreaterThanEqual(userinfo.get(), date, amount);
                    } else {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndDateAndNameAndAmountGreaterThanEqual(userinfo.get(), date, name, amount);
                    }
                }
            } else if (symbol.equals("<")) {
                if (date == null) {
                    if (name.isEmpty()) {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndAmountLessThanEqual(userinfo.get(), amount);
                    } else {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndNameAndAmountLessThanEqual(userinfo.get(), name, amount);
                    }
                } else {
                    if (name.isEmpty()) {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndDateAndAmountLessThanEqual(userinfo.get(), date, amount);
                    } else {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndDateAndNameAndAmountLessThanEqual(userinfo.get(), date, name, amount);
                    }
                }
            } else if (symbol.equals("=")) {
                if (date == null) {
                    if (name.isEmpty()) {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndAmount(userinfo.get(), amount);
                    } else {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndNameAndAmount(userinfo.get(), name, amount);
                    }
                } else {
                    if (name.isEmpty()) {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndDateAndAmount(userinfo.get(), date, amount);
                    } else {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndDateAndNameAndAmount(userinfo.get(), date, name, amount);
                    }
                }
            } else {
                if (date == null) {
                    if (name.isEmpty()) {
                        incomeStatuses = incomeStatusRepository.findAllByUserId_UserId(user_id);
                    } else {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndName(userinfo.get(), name);
                    }
                } else {
                    if (name.isEmpty()) {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndDate(userinfo.get(), date);
                    } else {
                        incomeStatuses = incomeStatusRepository.findAllByUserIdAndDateAndName(userinfo.get(), date, name);
                    }
                }
            }
        } else if ("지출".equals(type)) {
            if (symbol.equals(">")) {
                if (date == null) {
                    if (name.isEmpty()) {
                        expenses = expenseRepository.findAllByUserIdAndAmountGreaterThanEqual(userinfo.get(), amount);
                    } else {
                        expenses = expenseRepository.findAllByUserIdAndNameAndAmountGreaterThanEqual(userinfo.get(), name, amount);
                    }
                } else {
                    if (name.isEmpty()) {
                        expenses = expenseRepository.findAllByUserIdAndDateAndAmountGreaterThanEqual(userinfo.get(), date, amount);
                    } else {
                        expenses = expenseRepository.findAllByUserIdAndDateAndNameAndAmountGreaterThanEqual(userinfo.get(), date, name, amount);
                    }
                }
            } else if (symbol.equals("<")) {
                if (date == null) {
                    if (name.isEmpty()) {
                        expenses = expenseRepository.findAllByUserIdAndAmountLessThanEqual(userinfo.get(), amount);
                    } else {
                        expenses = expenseRepository.findAllByUserIdAndNameAndAmountLessThanEqual(userinfo.get(), name, amount);
                    }
                } else {
                    if (name.isEmpty()) {
                        expenses = expenseRepository.findAllByUserIdAndDateAndAmountLessThanEqual(userinfo.get(), date, amount);
                    } else {
                        expenses = expenseRepository.findAllByUserIdAndDateAndNameAndAmountLessThanEqual(userinfo.get(), date, name, amount);
                    }
                }
            } else if (symbol.equals("=")) {
                if (date == null) {
                    if (name.isEmpty()) {
                        expenses = expenseRepository.findAllByUserIdAndAmount(userinfo.get(), amount);
                    } else {
                        expenses = expenseRepository.findAllByUserIdAndNameAndAmount(userinfo.get(), name, amount);
                    }
                } else {
                    if (name.isEmpty()) {
                        expenses = expenseRepository.findAllByUserIdAndDateAndAmount(userinfo.get(), date, amount);
                    } else {
                        expenses = expenseRepository.findAllByUserIdAndDateAndNameAndAmount(userinfo.get(), date, name, amount);
                    }
                }
            } else {
                if (date == null) {
                    if (name.isEmpty()) {
                        System.out.println("입장");
                        expenses = expenseRepository.findAllByUserId_UserId(user_id);
                    } else {
                        expenses = expenseRepository.findAllByUserIdAndName(userinfo.get(), name);
                    }
                } else {
                    if (name.isEmpty()) {
                        expenses = expenseRepository.findAllByUserIdAndDate(userinfo.get(), date);
                    } else {
                        expenses = expenseRepository.findAllByUserIdAndDateAndName(userinfo.get(), date, name);
                    }
                }
            }
        } else {
            model.addAttribute("message", "검색 시 타입을 꼭 포함해 주세요");
            return "searchResult";
        }

        if (type.equals("수입")) {
            model.addAttribute("incomeStatusList", incomeStatuses);
            model.addAttribute("type", "수입");
            model.addAttribute("input", input);
        } else if (type.equals("지출")) {
            System.out.println(expenses);
            model.addAttribute("expenseList", expenses);
            model.addAttribute("type", "지출");
            model.addAttribute("input", input);
        }

        return "searchResult";
    }
}
