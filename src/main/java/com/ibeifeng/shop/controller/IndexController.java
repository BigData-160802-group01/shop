package com.ibeifeng.shop.controller;

import com.ibeifeng.shop.model.*;
import com.ibeifeng.shop.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.hibernate.Session;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 2016/12/8.
 */
@Controller
@RequestMapping("/show")
public class IndexController {
    private IUserService userService;
    private IShopService shopService;
    private IProTypeListService proTypeListService;
    private IProTypeService proTypeService;
    private IProductService productService;
    private IOrderService orderService;
    private IGuestBookService guestBookService;
    private INewsService newsService;
    private IUserAdressService userAdressService;

    @GetMapping(value = "/index")
    public String showIndex(HttpSession session){
        List<ProTypeList> proTypeListList=proTypeListService.listnolimit();
        List<News> newsList=newsService.listnolimit();
        session.setAttribute("protypelist",proTypeListList);
        session.setAttribute("newslist",newsList);
        return "/page/index";
    }

    @GetMapping(value = "/productC/{id}")
    public String showProductDetail(@PathVariable int id,Model model){
        model.addAttribute("product",productService.load(id)) ;
        return "page/product-view";
    }
    @GetMapping(value = "/payproduct/{id}")
    public String payProduct(@PathVariable int id, Model model, HttpSession session){
        User user=(User) session.getAttribute("LoginUser");
        if (user==null){
            return "page/login";
        }else {
            session.setAttribute("LoginUser",userService.load(user.getId()));
            List<Order> needPay=new ArrayList<Order>();
            Order order=new Order();
            order.setProduct(productService.load(id));
            needPay.add(order);
            model.addAttribute("needPay",needPay) ;
            return "page/address";
        }
    }

    @PostMapping(value = "/createOrder")
    public String createOrder(@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")Date createtime, int number,Order order, BindingResult result, HttpSession session,Model model){
        order.setCreatetime(createtime);
        order.setCount(number);
        orderService.createOrder(session,order);
        model.addAttribute("curOrder",order);
        return "page/payfor";
    }

    /**
     * 跳转至新增地址页面
     * @return
     */
    @GetMapping(value = "/createAdress")
    public String createAdress(Model model){
        List<UserAdress> list=userAdressService.listnolimit();
        model.addAttribute("addresslist",list);
        return "page/addressAdd";
    }

    /**
     *
     * @param adress
     * @param result
     * @param session
     * @return
     */
    @PostMapping(value = "/addAddress")
    public String addAddress(UserAdress adress,BindingResult result,HttpSession session){
        User user=(User) session.getAttribute("LoginUser");
        adress.setAduser(user);
        for (UserAdress userAdress:user.getUserAdresses()){
            userAdress.setChose(0);
            userAdressService.update(userAdress);
        }
        userAdressService.add(adress);
        session.setAttribute("LoginUser",userService.load(user.getId()));
        return "page/addAddress-result";
    }

    @PostMapping(value = "/payfor/resoult")
    public String payresult (int orId){
        Order order=orderService.load(orId);
        order.setStatus(1);
        orderService.update(order);
        return "/page/shopping-result";
    }
    @PostMapping(value = "/payfor1/resoult")
    public String payresult1 (HttpSession session){
        User user=(User) session.getAttribute("LoginUser");
        Set<Order> orders=user.getShop().getOrders();
        for (Order o:orders){
            o.setStatus(1);
            orderService.update(o);
        }
        return "/page/shopping-result";
    }

    /**
     * 跳转至购物车
     * @param id
     * @param session
     * @param model
     * @return
     */
    @GetMapping(value = "/joinshopping/{id}")
    public String joinshopping(@PathVariable int id, HttpSession session, Model model){
        User user=(User) session.getAttribute("LoginUser");
        if (user==null){
            return "/page/login";
        }
        user=userService.load(user.getId());
        Product product=productService.load(id);
        shopService.joinOrder(user,product);
        session.setAttribute("LoginUser",user);
        return "page/shopping";
    }
    @GetMapping(value = "/delShopOrder/{id}")
    public String delShopOrder(@PathVariable int id){
        orderService.delete(id);
        return "redirect:/show/shopping";

    }
    @GetMapping(value = "/shopping")
    public String goshopping(HttpSession session){
        User user=(User) session.getAttribute("LoginUser");
        if (user==null){
            return "/page/login";
        }
        session.setAttribute("LoginUser",userService.load(user.getId()));
       return "/page/shopping";
    }
    @PostMapping(value = "/payfor/{id}")
    public String payforShopping(@PathVariable int id,Model model,HttpSession session){
        Shop shop=shopService.load(id);
        User user=(User) session.getAttribute("LoginUser");
        session.setAttribute("LoginUser",userService.load(user.getId()));
        model.addAttribute("needPay",shopService.payShopping(shop));
        return "/page/shoppingaddress";
    }

    @GetMapping(value = "/showtypePro/{name}")
    public String showProductByType(@PathVariable String name,Model model){
        model.addAttribute("ProductList",productService.listByTypeName(name));
        return "/page/product-list";
    }

    @GetMapping(value = "/showtypelistPro/{name}")
    public String showtlP(@PathVariable String name,Model model,HttpSession session){
        model.addAttribute("ptl",proTypeListService.loadByName(name)) ;
        return "/page/productByType-list";
    }

    @PostMapping(value = "/payforShopping")
    public String payforShopping(HttpSession session,Model model){
        User user=(User)session.getAttribute("LoginUser");
        Set<Order> orders=user.getShop().getOrders();
        List<String> list=new ArrayList<String>();
        int totalCount=0;
        for (Order o:orders){
            if (o.getStatus()==0){
                list.add(o.getProduct().getProname());
                totalCount+=o.getProduct().getPrice()*o.getCount();
            }

        }
        model.addAttribute("prolist",list);
        model.addAttribute("totalvalue",totalCount);
        return "/page/payforShopping";

    }



    @Resource
    public void setUserAdressService(IUserAdressService userAdressService) {
        this.userAdressService = userAdressService;
    }
    @Resource
    public void setNewsService(INewsService newsService) {
        this.newsService = newsService;
    }
    @Resource
    public void setGuestBookService(IGuestBookService guestBookService) {
        this.guestBookService = guestBookService;
    }
    @Resource
    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }
    @Resource
    public void setProductService(IProductService productService) {
        this.productService = productService;
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
    @Resource
    public void setProTypeService(IProTypeService proTypeService) {
        this.proTypeService = proTypeService;
    }
}
