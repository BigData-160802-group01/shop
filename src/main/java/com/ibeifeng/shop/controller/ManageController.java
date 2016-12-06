package com.ibeifeng.shop.controller;

import com.ibeifeng.shop.dao.IProTypeListDao;
import com.ibeifeng.shop.model.ProTypeList;
import com.ibeifeng.shop.model.User;
import com.ibeifeng.shop.service.IProTypeListService;
import com.ibeifeng.shop.service.IShopService;
import com.ibeifeng.shop.service.IUserService;
import com.ibeifeng.shop.util.Pager;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/6.
 */
@Controller()
@RequestMapping(value = "/manager")
public class ManageController {
    private IUserService userService;
    private IShopService shopService;
    private IProTypeListService proTypeListService;

    /**
     * 显示所有用户信息
     * @param request
     * @return
     */
        @RequestMapping(value = "/user/list")
    public String manageUser(HttpServletRequest request){
        Pager<User> list=userService.pageList();
        request.setAttribute("userlist",list);
        return "WEB-INF/manage/user";
    }
    @GetMapping(value = "/user/modify/{id}")
    public String modifyUser(@PathVariable int id, Model model){
        User modifyUser=userService.load(id);
        model.addAttribute(modifyUser);
        return "WEB-INF/manage/user-modify";
    }
    @PostMapping(value = "/user/modify")
    public String modifyUser(@DateTimeFormat(pattern="yyyy-MM-dd")Date birthday, User user, BindingResult result){
        user.setBirthday(birthday);
        userService.update(user);
        return "WEB-INF/manage/manage-result";
    }
    @GetMapping(value = "/user/delete/{id}")
    public String deleteUser(@PathVariable int id){
        User user=userService.load(id);
        userService.delete(user);
        return "WEB-INF/manage/manage-result";
    }
    @GetMapping(value = "/productClass/list")
    public String manageProductClass(HttpServletRequest request ){
        request.setAttribute("typelist",proTypeListService.list());
        return "WEB-INF/manage/productClass";
    }
    @GetMapping(value = "/productClass/modify/{name}")
    public String updateProductClass(@PathVariable String name,Model model){
        ProTypeList typeList=proTypeListService.loadByName(name);
        model.addAttribute("updateProductClass",typeList);
        return "WEB-INF/manage/productClass-modify";
    }

    @PostMapping(value = "/productClass/modify")
    public String updateProductClass(ProTypeList proTypeList,BindingResult result,HttpServletRequest request){
        proTypeListService.update(proTypeList);
        return "WEB-INF/manage/manage-result";
    }

    @GetMapping(value = "/productClass/delete/{id}")
    public String deleteProTypeList(@PathVariable int id){
        System.out.println("123");
        proTypeListService.delete(id);
        System.out.println("321");
        return "WEB-INF/manage/manage-result";
    }



    @Resource
    public void setProTypeListService(IProTypeListService proTypeListService) {
        this.proTypeListService = proTypeListService;
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
