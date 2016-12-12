package com.ibeifeng.shop.controller;

import com.ibeifeng.shop.dao.INewsDao;
import com.ibeifeng.shop.dao.IProTypeListDao;
import com.ibeifeng.shop.model.*;
import com.ibeifeng.shop.service.*;
import com.ibeifeng.shop.util.Pager;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */
@Controller()
@RequestMapping(value = "/manager")
public class ManageController {
    private IUserService userService;
    private IShopService shopService;
    private IProTypeListService proTypeListService;
    private IProTypeService proTypeService;
    private IProductService productService;
    private IOrderService orderService;
    private IGuestBookService guestBookService;
    private INewsService newsService;

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
    public String updateProductClass(ProTypeList proTypeList,BindingResult result){
        proTypeListService.update(proTypeList);
        return "WEB-INF/manage/manage-result";
    }

    @GetMapping(value = "/productClass/delete/{id}")
    public String deleteProTypeList(@PathVariable int id){
        proTypeListService.delete(id);
        return "WEB-INF/manage/manage-result";
    }
    @GetMapping(value = "/productClass/modify1/{id}")
    public String updateType(@PathVariable int id,Model model){
        ProType proType=proTypeService.load(id);
        model.addAttribute("updateProType",proType);
        return "WEB-INF/manage/productClass1-modify";
    }
    @PostMapping(value = "/productClass/modify1")
    public String updataType(ProType proType,BindingResult result){
        proTypeService.update(proType);
        System.out.println("123");
        return "WEB-INF/manage/manage-result";
    }

    /**
     * 删除二级分类
     * @param id
     * @return
     */
    @GetMapping(value = "productClass/deleteType/{id}")
    public String deleteprotype(@PathVariable int id){
        proTypeService.delete(id);
        System.out.println("123");
        return "WEB-INF/manage/manage-result";
    }

    /**
     * 跳转至添加分类的页面
     * @return
     */
    @GetMapping(value = "productClass/addtype")
    public String addProtype(Model model){
        model.addAttribute("protL",proTypeListService.listnolimit());
        return "WEB-INF/manage/productClass-add";
    }

    /**
     * 执行增加分类操作
     * @param proType
     * @param result
     * @return
     */
    @PostMapping(value = "productClass/addtype")
    public String addProtype(ProType proType,BindingResult result){
        proTypeService.add(proType);
        return "WEB-INF/manage/manage-result";
    }

    /**
     * 跳转至商品管理页面
     * 读取商品信息 并返回至页面
     * @return
     */
    @GetMapping(value = "product/list")
    public String manageProduct(Model model){
        model.addAttribute("propag",productService.list());
        System.out.println(productService.list());
        return "WEB-INF/manage/product";
    }

    /**
     * 跳转至修改商品页面
     * 根据id 查询出对应的商品
     * 查询出所有分类 返回页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/product/modify/{id}")
    public String updateProduct(@PathVariable int id,Model model){
        Product product=productService.load(id);
        List<ProTypeList> list=proTypeListService.listnolimit();
        model.addAttribute("updateProduct",product);
        model.addAttribute("protypelist",list);
        System.out.println("123");
        return "WEB-INF/manage/product-modify";
    }

    /**
     * 修改商品信息
     * @param photo
     * @param product
     * @param result
     * @param session
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/product/modify")
    public String updateProduct(MultipartFile photo, Product product, BindingResult result, HttpSession session) throws IOException {
        ProType proType=proTypeService.loadByName(product.getProType().getTypeName());
        product.setProType(proType);
        if(photo!=null&&!photo.isEmpty()){
            String fileName=photo.getOriginalFilename();
            String path=session.getServletContext().getRealPath("/resources/upload/");
            product.setPitch("\\resources\\upload\\"+fileName);
            FileUtils.copyInputStreamToFile(photo.getInputStream(),new File(path+File.separator+fileName));
        }
       productService.update(product);
        return "WEB-INF/manage/manage-result";
    }

    /**
     * 根据id 删除商品
     * @param id
     * @return
     */
    @GetMapping(value = "/product/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        productService.delete(id);
        return "WEB-INF/manage/manage-result";
    }

    /**
     * 跳转至增加商品的页面
     * @return
     */
    @GetMapping(value = "/product/addproduct")
    public String addProduct(Model model){
        List<ProTypeList> list=proTypeListService.listnolimit();
        model.addAttribute("protypelist",list);
        return "WEB-INF/manage/product-add";
    }

    /**
     * 新增商品到数据库
     * @param product
     * @param photo
     * @param result
     * @param session
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/product/addproduct")
    public String addProduct(Product product,MultipartFile photo,BindingResult result,HttpSession session) throws IOException {
        if(photo!=null&&!photo.isEmpty()){
            String fileName=photo.getOriginalFilename();
            String path=session.getServletContext().getRealPath("/resources/upload/");
            System.out.println(fileName);
            product.setPitch("\\resources\\upload\\"+fileName);
            FileUtils.copyInputStreamToFile(photo.getInputStream(),new File(path+File.separator+fileName));
        }
        productService.add(product);
        return "WEB-INF/manage/manage-result";
    }

    /**
     * 跳转至订单管理页面
     * 获取订单集合传参
     * @return
     */
    @GetMapping(value = "/order/list")
    public String orderList(Model model){
        model.addAttribute("orderList", orderService.list());
        System.out.println("123");
        return "WEB-INF/manage/order";
    }

    /**
     * 跳转至留言页面
     * 查询出留言集合 返回至页面
     * @param model
     * @return
     */
    @GetMapping(value = "/guestbook/list")
    public String guestbookList(Model model){
        model.addAttribute("guestBooklist",guestBookService.list());
        return "WEB-INF/manage/guestbook";
    }

    /**
     * 修改单个留言记录
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/guestbook/modify/{id}")
    public String beforupdateguest(@PathVariable int id,Model model){
        GuestBook guestBook=guestBookService.load(id);
        model.addAttribute("updateGuestBook",guestBook);
        return "WEB-INF/manage/guestbook-modify";
    }

    /**
     * 进行修改留言处理，处理完成进入留言列表页面
     * @param replytime
     * @param createtime
     * @param guestBook
     * @param result
     * @return
     */
    @PostMapping(value = "/guestbook/modify")
    public String updateguestbook(@DateTimeFormat(pattern="yyyy-MM-dd")Date replytime,@DateTimeFormat(pattern="yyyy-MM-dd")Date createtime,GuestBook guestBook,BindingResult result){
        guestBook.setUser(userService.load(guestBook.getUser().getId()));
        guestBook.setReplytime(replytime);
        guestBook.setCreatetime(createtime);
        guestBookService.update(guestBook);
        return "redirect:/manager/guestbook/list";
    }

    /**
     * 删除留言，并且跳转至留言列表
     * @param id
     * @return
     */
    @GetMapping(value = "/guestbook/delete/{id}")
    public String deleteguestbook(@PathVariable int id){
        guestBookService.delete(id);
        return "redirect:/manager/guestbook/list";
    }

    /**
     * 跳转至新闻列表
     * @param model
     * @return
     */
    @GetMapping(value = "/news/list")
    public String newsList(Model model){
        model.addAttribute("newsList",newsService.list());
        return "WEB-INF/manage/news";
    }

    /**
     * 跳转至新闻修改页面
     * 查询出需要修改的新闻
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/news/update/{id}")
    public String updateNews(@PathVariable int id,Model model){
        News news=newsService.load(id);
       model.addAttribute("updateNews",news);
        return "WEB-INF/manage/news-modify";
    }

    /**
     * 执行修改新闻的操作
     * @param title
     * @param content
     * @param id
     * @return
     */
    @PostMapping(value = "/news/update")
    public String updateNews(String title,String content,int id){
        News news=newsService.load(id);
        news.setNewContent(content);
        news.setTitle(title);
        newsService.update(news);
        return "redirect:/manager/news/list";
    }

    /**
     * 根据id 执行删除news 的操作
     * 成功后跳转至news 列表页面
     * @param id
     * @return
     */
    @GetMapping(value = "/news/delete/{id}")
    public String deleteNews(@PathVariable int id){
        newsService.delete(id);
        return "redirect:/manager/news/list";
    }
    @GetMapping(value = "/news/addNews")
    public String addNews(){
        return "WEB-INF/manage/news-add";
    }
    @PostMapping(value = "/news/addNews")
    public String addNews(News news,BindingResult result){
        newsService.add(news);
        return "redirect:/manager/news/list";
    }

//    @PostMapping(value = "/order/byid")
//    public String searchOrderById(int entityId,String userName){
//
//    }



    /**
     * ajax 根据前台传递的 总类 名称 查询出 分类的名称并返回给前台
     * @param name
     * @return
     */
    @PostMapping(value = "/product/modify/search")
    @ResponseBody
    public List<String> updateProductSearchProtype(String name){
        ProTypeList proTypeList=proTypeListService.loadByName(name);
        List<String> proNamelist=new ArrayList<String>();
        for (ProType proType:proTypeList.getType()){
            proNamelist.add(proType.getTypeName());
        }
        return proNamelist;
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
