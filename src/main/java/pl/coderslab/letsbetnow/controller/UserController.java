package pl.coderslab.letsbetnow.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.letsbetnow.model.Operation;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.service.OperationService;
import pl.coderslab.letsbetnow.service.UserService;
import pl.coderslab.letsbetnow.serviceimpl.CurrentUser;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OperationService operationService;

    @GetMapping("/details")
    public String userDetails(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());

        model.addAttribute(user);

        return "user/details";
    }

    @PostMapping("/details")
    public String userDetails(@AuthenticationPrincipal UserDetails userDetails, @Valid User user, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return "details";
        }

        User oldUser = userService.findUserByUsername(userDetails.getUsername());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        userService.saveUser(oldUser);

        return "user/details";
    }

    @PostMapping("/delete")
    public String userDelete(@RequestParam String username, HttpSession session){

        User user = userService.findUserByUsername(username);
        userService.deleteUser(user);
        session.invalidate();

        return "redirect:/logout";
    }


    @GetMapping("/wallet")
    public String userHome(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());

        model.addAttribute("user",user);

        return "user/wallet";
    }

    @GetMapping("/wallet/recharge")
    public String walletRecharge(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());
        model.addAttribute("user",user);

        return "user/recharge";
    }

    @PostMapping("/wallet/recharge")
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

        return "redirect:/user/wallet";
    }

    @GetMapping("/wallet/withdraw")
    public String walletWithdraw(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());
        model.addAttribute("user",user);

        return "user/withdraw";
    }

    @PostMapping("/wallet/withdraw")
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

        return "redirect:/user/wallet";
    }

    @GetMapping("/card/add")
    public String addCard(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());

        model.addAttribute("user",user);

        return "user/wallet";
    }

}
