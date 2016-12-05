package com.ibeifeng.shop.controller;

import com.ibeifeng.shop.model.Shop;
import com.ibeifeng.shop.model.User;
import com.ibeifeng.shop.service.IShopService;
import com.ibeifeng.shop.service.IUserService;
import com.ibeifeng.shop.util.Pager;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/3.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private IUserService userService;
    private IShopService shopService;
    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session, Model model){
        String status=userService.login(username,password,session);
        String errorMessage=null;
        if (status.equals("passworderror")){
            errorMessage="密码错误！请重新输入!";
            model.addAttribute("errorMessage",errorMessage);
            return "login";
        }else if (status.equals("usernameerror")){
            errorMessage="用户名错误，请确认后重新输入";
            model.addAttribute("errorMessage",errorMessage);
            return "login";
        }else if (status.equals("isExist")){
            errorMessage="该用户已登陆，如非本人，请修改密码";
            model.addAttribute("errorMessage",errorMessage);
            return "login";
        }
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        User user=(User) session.getAttribute("LoginUser");
        Map<String,String> loginUserMap=(Map<String, String>) session.getServletContext().getAttribute("loginUM");
        loginUserMap.remove(user.getName());
        session.getServletContext().setAttribute("LoginUser",loginUserMap);
        session.removeAttribute("LoginUser");
        return "index";
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String beforereg(Model model){
        model.addAttribute(new User());
        return "register";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@DateTimeFormat(pattern="yyyy-MM-dd")Date birthday, User user, BindingResult result, HttpSession session){
        Shop shop=new Shop();
        user.setBirthday(birthday);
        shopService.add(shop);
        user.setShop(shop);
        userService.add(user,session);
        return "reg-result";
    }
    /**
     *
     * ajax注册时验证用户名
     * @param name
     * @return
     */
    @PostMapping(value = "/searchname")
    @ResponseBody
    public String searchname(String name){
        return userService.searchusername(name);
    }

    /**
     * 后台管理页面验证权限
     */
    @RequestMapping(value = "/manager")
    public String judgeManage(HttpSession session){
        String result=userService.judgeStatus(session);
        if (result.equals("OK")){
            return "manage/index";
        }else {
            return "index";
        }
    }

    /**
     * 显示所有用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/manager/user/list")
    public String manageUser(HttpServletRequest request){
        Pager<User> list=userService.pageList();
        request.setAttribute("userlist",list);
        return "manage/user";
    }
    @GetMapping(value = "/manager/user/modify/{id}")
    public String modifyUser(@PathVariable int id,Model model){
        User modifyUser=userService.load(id);
        model.addAttribute(modifyUser);
        return "manage/user-modify";
    }
    @PostMapping(value = "/manager/user/modify")
    public String modifyUser(@DateTimeFormat(pattern="yyyy-MM-dd")Date birthday,User user,BindingResult result){
        user.setBirthday(birthday);
        userService.update(user);
        return "manage/manage-result";
    }

    @Resource
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    @Resource
    public void setShopService(IShopService shopService) {
        this.shopService = shopService;
    }
}
