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
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2016/12/3.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private IUserService userService;
    private IShopService shopService;
//    @RequestMapping("/login")
//    public String login(String username, String password,String code, HttpSession session, Model model){
//        String status=userService.login(username,password,session);
//        String errorMessage=null;
//        if (status.equals("passworderror")){
//            errorMessage="密码错误！请重新输入!";
//            model.addAttribute("errorMessage",errorMessage);
//            return "page/login";
//        }else if (status.equals("usernameerror")){
//            errorMessage="用户名错误，请确认后重新输入";
//            model.addAttribute("errorMessage",errorMessage);
//            return "page/login";
//        }else if (status.equals("isExist")){
//            errorMessage="该用户已登陆，如非本人，请修改密码";
//            model.addAttribute("errorMessage",errorMessage);
//            return "page/login";
//        }else if (!code.equals(session.getAttribute("valcode"))){
//            errorMessage="验证码错误";
//            model.addAttribute("errorMessage",errorMessage);
//            return "page/login";
//        }
//        return "page/index";
//    }

    @RequestMapping(value = "/login1",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(String username, String password,String code, HttpSession session,HttpServletResponse response){
        String status=userService.login(username,password,session);
        String errorMessage=null;
        if (status.equals("passworderror")){
            errorMessage="密码错误！请重新输入!";
            return errorMessage;
        }else if (status.equals("usernameerror")){
            errorMessage="用户名错误，请确认后重新输入";
            return errorMessage;
        }else if (status.equals("isExist")){
            errorMessage="该用户已登陆，如非本人，请修改密码";
            return errorMessage;
        }else if (!code.equals(session.getAttribute("valcode"))){
            errorMessage="验证码错误";
            return errorMessage;
        }
        return "success";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        User user=(User) session.getAttribute("LoginUser");
        Map<String,String> loginUserMap=(Map<String, String>) session.getServletContext().getAttribute("loginUM");
        loginUserMap.remove(user.getName());
        session.getServletContext().setAttribute("LoginUser",loginUserMap);
        session.removeAttribute("LoginUser");
        return "redirect:/show/index";
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String beforereg(Model model){
        model.addAttribute(new User());
        return "page/register";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@DateTimeFormat(pattern="yyyy-MM-dd")Date birthday, User user, BindingResult result, HttpSession session){
        Shop shop=new Shop();
        user.setBirthday(birthday);
        shopService.add(shop);
        user.setShop(shop);
        userService.add(user,session);
        return "page/reg-result";
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
        return userService.searchusername1(name);
    }

    /**
     * 后台管理页面验证权限
     */
    @RequestMapping(value = "/manager")
    public String judgeManage(HttpSession session){
        String result=userService.judgeStatus(session);
        if (result.equals("OK")){
            return "WEB-INF/manage/index";
        }else {
            return "redirect:/show/index";
        }
    }
    @GetMapping("/validLogin")
    public void valiadlogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置浏览器 缓存
        response.setContentType("image/jpeg");
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");
//        定义验证码长度
        int length=4;
//        接受验证码
        String valcode="";
        Random rd= new Random();
//        生成验证码
        for (int i = 0; i <length ; i++) {
            valcode+=rd.nextInt(10);
        }
        //将验证码存入session 方便比对
        request.getSession().setAttribute("valcode",valcode);
        //定义图片的属性
        int width=80;
        int height=25;
        BufferedImage img=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g=img.getGraphics();
//        填充背景
        g.setColor(Color.white);
        g.fillRect(0,0,width,height);
//        填充干扰线
        for (int i = 0; i <50 ; i++) {
            g.setColor(new Color(rd.nextInt(100)+155,rd.nextInt(100)+155,rd.nextInt(100)+155));
            g.drawLine(rd.nextInt(width),rd.nextInt(height),rd.nextInt(width),rd.nextInt(height));
        }
//        设置边框
        g.setColor(Color.gray);
        g.drawRect(0,0,width-1,height-1);
//        绘制验证码
        Font[] font={new Font("隶书",Font.BOLD,18),new Font("楷体",Font.BOLD,18),new Font("宋体",Font.BOLD,18),new Font("微软雅黑",Font.BOLD,18)};
        for (int i = 0; i <length ; i++) {
            g.setColor(new Color(rd.nextInt(150),rd.nextInt(150),rd.nextInt(150)));
            g.setFont(font[rd.nextInt(font.length)]);
            g.drawString(valcode.charAt(i)+"",width/valcode.length()*i+2,18);
        }
        g.dispose();
        ImageIO.write(img,"jpeg",response.getOutputStream());
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
