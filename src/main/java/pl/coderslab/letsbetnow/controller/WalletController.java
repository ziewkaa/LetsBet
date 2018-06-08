package pl.coderslab.letsbetnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.letsbetnow.service.implementation.config.CurrentUser;
import pl.coderslab.letsbetnow.model.Operation;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.service.OperationService;
import pl.coderslab.letsbetnow.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private UserService userService;

    @Autowired
    private OperationService operationService;

    @GetMapping("/home")
    public String walletHome(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());
        List<Operation> operations = operationService.findAllOperationsByUser(user);

        model.addAttribute("user",user);
        model.addAttribute("operations",operations);

        return "user/wallet";
    }

    @GetMapping("/recharge")
    public String walletRecharge(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());
        model.addAttribute("user",user);

        return "user/recharge";
    }

    @PostMapping("/recharge")
    public String walletRecharge(@RequestParam double amount, @RequestParam Long id, Model model){

        User user = userService.getUserById(id);
        List<Operation> operations = user.getOperations();
        BigDecimal currentFunds = user.getFunds();

        if (amount >= 200.00) {
            double bonus = amount * 0.1;
            amount = amount + bonus;

            Operation operation = new Operation();
            operation.setUser(user);
            operation.setValue(BigDecimal.valueOf(bonus));
            operation.setType("Recharge Bonus");
            operation.setRegisteredTime(LocalTime.now());
            operation.setRegisteredDate(LocalDate.now());
            operationService.saveOperation(operation);
        }
        currentFunds = currentFunds.add(BigDecimal.valueOf(amount));

        Operation operation = new Operation();
        operation.setRegisteredDate(LocalDate.now());
        operation.setRegisteredTime(LocalTime.now());
        operation.setValue(BigDecimal.valueOf(amount));
        operation.setType("Recharge");
        operation.setUser(user);
        operationService.saveOperation(operation);

        operations.add(operation);
        user.setFunds(currentFunds);
        userService.saveUser(user);
        model.addAttribute("amount",amount);
        model.addAttribute("user",user);
        model.addAttribute("creditCard",user.getCreditCard());

        return "redirect:/wallet/home";
    }

    @GetMapping("/withdraw")
    public String walletWithdraw(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());
        model.addAttribute(user);

        return "user/withdraw";
    }

    @PostMapping("/withdraw")
    public String walletWithdraw(@RequestParam double amount, @RequestParam Long id, Model model){

        User user = userService.getUserById(id);
        List<Operation> operations = user.getOperations();

        BigDecimal currentFunds = user.getFunds();
        currentFunds = currentFunds.subtract(BigDecimal.valueOf(amount));

        Operation operation = new Operation();
        operation.setRegisteredDate(LocalDate.now());
        operation.setRegisteredTime(LocalTime.now());
        operation.setValue(BigDecimal.valueOf(amount));
        operation.setType("Withdrawal");
        operation.setUser(user);
        operationService.saveOperation(operation);

        operations.add(operation);
        user.setFunds(currentFunds);
        userService.saveUser(user);
        model.addAttribute(user);
        model.addAttribute("creditCard",user.getCreditCard());

        return "redirect:/wallet/home";
    }


}
