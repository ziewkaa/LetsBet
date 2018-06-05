package pl.coderslab.letsbetnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.letsbetnow.implementation.config.CurrentUser;
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

        model.addAttribute("user",user);

        return "user/wallet";
    }

    @GetMapping("/recharge")
    public String walletRecharge(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());
        model.addAttribute("user",user);

        return "user/recharge";
    }

    @PostMapping("/recharge")
    public String walletRecharge(@RequestParam BigDecimal amount, @RequestParam Long id){

        User user = userService.getUserById(id);
        List<Operation> operations = user.getOperations();

        BigDecimal currentFunds = user.getFunds();
        currentFunds = currentFunds.add(amount);

        Operation operation = new Operation();
        operation.setRegisteredDate(LocalDate.now());
        operation.setRegisteredTime(LocalTime.now());
        operation.setValue(amount);
        operation.setType("Recharge");
        operation.setUser(user);
        operationService.saveOperation(operation);

        operations.add(operation);
        user.setFunds(currentFunds);
        userService.saveUser(user);

        return "redirect:/wallet/home";
    }

    @GetMapping("/withdraw")
    public String walletWithdraw(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());
        model.addAttribute("user",user);

        return "user/withdraw";
    }

    @PostMapping("/withdraw")
    public String walletWithdraw(@RequestParam BigDecimal amount, @RequestParam Long id){

        User user = userService.getUserById(id);
        List<Operation> operations = user.getOperations();

        BigDecimal currentFunds = user.getFunds();
        currentFunds = currentFunds.subtract(amount);

        Operation operation = new Operation();
        operation.setRegisteredDate(LocalDate.now());
        operation.setRegisteredTime(LocalTime.now());
        operation.setValue(amount);
        operation.setType("Withdrawal");
        operation.setUser(user);
        operationService.saveOperation(operation);

        operations.add(operation);
        user.setFunds(currentFunds);
        userService.saveUser(user);

        return "redirect:/wallet/home";
    }


}
