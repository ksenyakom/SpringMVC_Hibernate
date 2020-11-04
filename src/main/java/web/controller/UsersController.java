package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.List;

@Controller
public class UsersController {

    private UserServiceImpl userService;

    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printUsers(ModelMap model) {
        List<User> list = userService.getAll();
        model.addAttribute("listUsers", list);

        return "index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {

        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userService.saveUser(user);

        return "redirect:/";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user",userService.getById(id));

        return "users/edit";
    }

    // patch запрос на адрес /users/id из формы для редактирования edit
    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id")int id) {
        userService.update(id, user);

        return "redirect:/";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id")int id) {
        userService.removeUserById(id);
        return "redirect:/";
    }
}
