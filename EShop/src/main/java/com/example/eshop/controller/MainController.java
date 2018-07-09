package com.example.eshop.controller;

import com.example.eshop.model.*;
import com.example.eshop.repository.BrandRepository;
import com.example.eshop.repository.CategoryRepository;
import com.example.eshop.repository.ProductRepository;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.security.CurrentUser;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandsRepository;

    @Value("${image.upload.dir}")
    private String imageUploadDir;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap map) {
        map.addAttribute("categories", categoryRepository.findAll());
        map.addAttribute("brands", brandsRepository.findAll());
        map.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap map) {
        map.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String loginUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = ((CurrentUser) userDetails).getUser();
        if (user.getType() == UserType.USER) {
            return "redirect:/";
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(ModelMap map) {
        map.addAttribute("category", new Category());
        map.addAttribute("brand", new Brand());
        map.addAttribute("product", new Product());
        map.addAttribute("categories", categoryRepository.findAll());
        map.addAttribute("brands", brandsRepository.findAll());

        return "admin";

    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("category") Category category) {
        categoryRepository.save(category);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/addBrand", method = RequestMethod.POST)
    public String addBrand(@ModelAttribute("brand") Brand brand) {
        brandsRepository.save(brand);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") Product product,
                             @RequestParam("picture") MultipartFile multipartFile) throws IOException {
        File imageDir = new File(imageUploadDir);
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }
        String pictureName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File file = new File(imageDir, pictureName);
        multipartFile.transferTo(file);
        product.setImage(pictureName);
        productRepository.save(product);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/product/image", method = RequestMethod.GET)
    public void getImageAsByteArray(HttpServletResponse response,
                                    @RequestParam("fileName") String fileName) throws IOException {
        InputStream in = new FileInputStream(imageUploadDir + fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
        user.setType(UserType.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "index";
    }


    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public String shop(ModelMap map) {
        map.addAttribute("categories",categoryRepository.findAll());
        map.addAttribute("brands",brandsRepository.findAll());
        map.addAttribute("products",productRepository.findAll());
        return "shop";
    }

    @RequestMapping(value = "/productDetails", method = RequestMethod.GET)
    public String productDetails() {
        return "product-details";
    }
}
