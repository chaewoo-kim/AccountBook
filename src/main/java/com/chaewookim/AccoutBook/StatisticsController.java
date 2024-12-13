package com.chaewookim.AccoutBook;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsRepository statisticsRepository;
    private final ExpenseRepository expenseRepository;
    private final String user_id = "ck12369";

    @GetMapping("/statistics")
    String statistics(Model model) {

        int dayLimit = 0;
        int dayExpense = 0;
        int remainedDayAmount = 0;
        int overDayAmount = 0;
        int monthLimit = 0;
        int monthExpense = 0;
        int remainedMonthAmount = 0;
        int overMonthAmount = 0;

        int [] day = {dayLimit, dayExpense, remainedDayAmount, overDayAmount};
        int [] month = {monthLimit, monthExpense, remainedMonthAmount, overMonthAmount};

        Userinfo userId = new Userinfo();
        userId.setUserId(user_id);

        LocalDate today = LocalDate.now();
        int currentMonth = YearMonth.from(today).getMonthValue();
        int lastDate = YearMonth.from(today).lengthOfMonth();
        LocalDate startDate = LocalDate.of(today.getYear(), currentMonth, 1);
        LocalDate endDate = LocalDate.of(today.getYear(), currentMonth, lastDate);

        Optional<Statistics> limits = statisticsRepository.findByUserId_UserId(user_id);
        if (limits.isPresent()) {
            List<Expense> todayExpense = expenseRepository.findAllByUserId_UserIdAndDate(user_id, LocalDate.now());
            List<Expense> thisMonthExpense = expenseRepository.findAllByUserIdAndDateBetween(userId, startDate, endDate);

            for (int i=0; i < todayExpense.size(); i++) {
                Expense expense = todayExpense.get(i);

                day[1] += expense.getAmount();

                if (i == todayExpense.size()-1) {

                    day[0] = limits.get().getDay_expense_recommended();

                    if (day[0] <= day[1]) {
                        day[3] = day[1] - day[0];
                    } else {
                        day[2] = day[0] - day[1];
                    }
                }
            }

            System.out.println(day);
            model.addAttribute("day", day);

            for (int i=0; i < thisMonthExpense.size(); i++) {
                Expense expense = thisMonthExpense.get(i);

                month[1] += expense.getAmount();

                if (i == thisMonthExpense.size()-1) {

                    month[0] = limits.get().getMonthly_expense_recommended();

                    if (month[0] <= month[1]) {
                        month[3] = month[1] - month[0];
                    } else {
                        month[2] = month[0] - month[1];
                    }
                }
            }
            model.addAttribute("month", month);
        } else {
            return "redirect:/main";
        }



        return "statistics.html";
    }



}
