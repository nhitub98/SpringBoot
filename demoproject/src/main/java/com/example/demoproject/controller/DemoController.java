package com.example.demoproject.controller;

import com.example.demoproject.dto.*;
import com.example.demoproject.entities.Category;
import com.example.demoproject.entities.Customer;
import com.example.demoproject.mapper.CategoryMapper;
import com.example.demoproject.repository.*;
import com.example.demoproject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DemoController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PaymentMethodService paymentMethodService;
    @Autowired
    PaymentMethodRepository paymentMethodRepository;
    @Autowired
    TransportMethodService transportMethodService;
    @Autowired
    TransportMethodRepository transportMethodRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrdersService ordersService;
    @Autowired
    ProductImagesService productImagesService;
    @Autowired
    ProductImagesRepository productImagesRepository;
    @Autowired
    OrdersDetailsService orderDetailsService;
    @Autowired
    OrdersPaymentService ordersPaymentService;
    @Autowired
    OrdersTransportService ordersTransportService;
    @Autowired
    CustomerRepository customerRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/products")
    public String findAllProduct(Model model) {
        List<ProductDTO> productDTOList = productService.findAll();
        for (ProductDTO productDTO : productDTOList) {
            String baseUrl = "/img/";
            productDTO.setImage(baseUrl + productDTO.getImage());

            }
        model.addAttribute("product", productDTOList);
        List<CategoryDTO> categoryDTOList = categoryService.findAllCategories();
        model.addAttribute("categories", categoryDTOList);
        return "features/findallproduct";
    }



    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        List<CategoryDTO> categoryDTOList = categoryService.findAllCategories();
        model.addAttribute("categories", categoryDTOList);
        return "features/formaddproduct";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") ProductDTO productDTO,
                             @RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (productDTO != null && productDTO.getCategoryDTO()!=null) {
            Category category = categoryMapper.toEntity(productDTO.getCategoryDTO());
            category = categoryRepository.save(category);
            List<Category> allCategories = categoryRepository.findAll();
            model.addAttribute("categories", allCategories);
            productService.saveProduct(productDTO, file);
        }
        return "redirect:/products";
    }

    @GetMapping("/edit-product/{id}")
    public String showUpdateProductForm(@PathVariable int id, Model model) {
        ProductDTO productDTO = productService.findById(id);
        model.addAttribute("product", productDTO);
        Category category = categoryMapper.toEntity(productDTO.getCategoryDTO());
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categories", categoryList);
        return "features/formeditproduct";
    }
    @PostMapping("/edit-product/{id}")
    public String updateProduct(@PathVariable int id, @ModelAttribute("product") ProductDTO productDTO, @RequestParam("file") MultipartFile file) {
        try {
            Category category = categoryMapper.toEntity(productDTO.getCategoryDTO());

            Optional<Category> existingCategory = categoryRepository.findById(category.getId());
            if (!existingCategory.isPresent()) {
                return "Danh mục không tồn tại";
            }
            category = categoryRepository.save(category);
            productService.updateProduct(id, productDTO, file);
            return "redirect:/products";
        } catch (Exception e) {
            e.printStackTrace();
            return "Có lỗi xảy ra khi cập nhật sản phẩm";
        }
    }


    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/categories")
    public String findAllCategories(Model model) {
        List<CategoryDTO> categoryDTOList = categoryService.findAllCategories();
        for (CategoryDTO categoryDTO : categoryDTOList) {
            String baseUrl = "/img/";
                    categoryDTO.setIcon(baseUrl + categoryDTO.getIcon());
        }
        model.addAttribute("categories", categoryDTOList);
        return "features/findallcategories";
    }
    @GetMapping("/add-category")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new CategoryDTO());
        return "features/formaddcategory";
    }

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute("category") CategoryDTO categoryDTO,
                            @RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes) throws IOException {
        if (categoryDTO != null) {
            categoryService.saveCategory(categoryDTO, file);
            redirectAttributes.addFlashAttribute("successMessage", "Category added successfully");
            return "redirect:/categories";
        }
        return null;
    }


    @GetMapping("/edit-category/{id}")
    public String showUpdateCategoryForm(@PathVariable int id, Model model) {
        CategoryDTO categoryDTO = categoryService.findByIdParent(id);
        model.addAttribute("category", categoryDTO);
        return "features/formeditcategory";
    }
    @PostMapping("/edit-category/{id}")
    public String updateCategory(@PathVariable int id, @ModelAttribute("category") CategoryDTO categoryDTO,@RequestParam("file") MultipartFile file) {
        categoryService.updateCategory(id, categoryDTO,file);
        return "redirect:/categories";
    }



    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }


    @GetMapping("/transportmethods")
    public String findAllTransportMethods(Model model){
        List<TransportMethodDTO> transportMethodDTOList = transportMethodService.findAllTransportMethod();
        model.addAttribute("transportmethods", transportMethodDTOList);
        return "features/findalltransportmethods";
    }
    @GetMapping("/add-transportmethod")
    public String showTransportMethodForm(Model model) {
        model.addAttribute("transportmethod", new TransportMethodDTO());
        return "features/formaddtransportmethod";
    }

    @PostMapping("/add-transportmethod")
    public String addTransportMethod(@ModelAttribute("transportmethod") TransportMethodDTO transportMethodDTO, Model model) {
        if (transportMethodDTO != null) {
            transportMethodService.saveTransportMethod(transportMethodDTO);
        }
        return "redirect:/transportmethods";
    }

    @GetMapping("/edit-transportmethod/{id}")
    public String showUpdateTransportMethodForm(@PathVariable int id, Model model) {
        TransportMethodDTO transportMethodDTO= transportMethodService.findTransportMethodById(id);
        model.addAttribute("transportmethod", transportMethodDTO); // Đổi từ "categories" thành "category"
        return "features/formedittransportmethod";
    }

    @PostMapping("/edit-transportmethod/{id}")
    public String updateTransportMethod(@ModelAttribute("transportmethod") TransportMethodDTO transportMethodDTO, @PathVariable int id) { // Thêm @PathVariable cho id
        transportMethodService.updateTransportMethod(id, transportMethodDTO);
        return "redirect:/transportmethods";
    }


    @GetMapping("/delete-transportmethod/{id}")
    public String deleteTransportMethod(@PathVariable("id") int id) {
        transportMethodService.deleteTransportMethod(id);
        return "redirect:/transportmethods";
    }

    @GetMapping("/paymentmethods")
    public String findAllPaymentMethods(Model model){
        List<PaymentMethodDTO> paymentMethodDTOList = paymentMethodService.findAllPaymentMethod();
        model.addAttribute("paymentmethods", paymentMethodDTOList);
        return "features/findallpayment";
    }
    @GetMapping("/add-paymentmethod")
    public String showAddPaymentMethodForm(Model model) {
        model.addAttribute("paymentmethod", new PaymentMethodDTO());
        return "features/formaddpaymentmethod";
    }

    @PostMapping("/add-paymentmethod")
    public String addPaymentMethod(@ModelAttribute("paymentmethod") PaymentMethodDTO paymentMethodDTO, Model model) {
        if (paymentMethodDTO != null) {
            paymentMethodService.savePaymentMethod(paymentMethodDTO);
        }
        return "redirect:/paymentmethods";
    }

    @GetMapping("/edit-paymentmethod/{id}")
    public String showUpdatePaymentMethodForm(@PathVariable int id, Model model) {
        PaymentMethodDTO paymentMethodDTO = paymentMethodService.findPaymentMethodById(id);
        model.addAttribute("paymentmethod", paymentMethodDTO);
        return "features/formeditpaymentmethod";
    }

    @PostMapping("/edit-paymentmethod/{id}")
    public String updatePaymentMethod(@ModelAttribute("paymentmethod") PaymentMethodDTO paymentMethodDTO, @PathVariable int id) { // Thêm @PathVariable cho id
        paymentMethodDTO.setId(id);
        paymentMethodService.updatePaymentMethod(id, paymentMethodDTO);
        return "redirect:/paymentmethods";
    }
    @GetMapping("/delete-paymentmethod/{id}")
    public String deletePaymentMethod(@PathVariable("id") int id) {
        paymentMethodService.deletePaymentMethod(id);
        return "redirect:/paymentmethods";
    }
    @GetMapping("/customers")
    public String findAllCustomers(Model model){
        List<CustomerDTO> customerDTOS = customerService.findAllCustomers();
        model.addAttribute("customers",customerDTOS);
        return "features/findallcustomers";
    }

    @GetMapping("/orders")
    public String findAllOrders(Model model){
        List<OrdersDTO> ordersDTOList = ordersService.findAllOrders();
        model.addAttribute("orders", ordersDTOList);
        return "features/findallorders";
    }
    @GetMapping("/edit-orders/{id}")
    public String showUpdateOrdersForm(@PathVariable int id, Model model) {
        OrdersDTO ordersDTO = ordersService.findOrdersById(id);
        model.addAttribute("orders", ordersDTO);
        OrdersDetailsDTO ordersDetailsDTO = orderDetailsService.findOrdersDetailsById(id);
        model.addAttribute("orders_details", ordersDetailsDTO);
        OrdersPaymentDTO ordersPaymentDTO = ordersPaymentService.findOrdersPaymentById(id);
        model.addAttribute("orders_payment",ordersPaymentDTO);
        OrdersTransportDTO ordersTransportDTO = ordersTransportService.findOrdersTransportById(id);
        model.addAttribute("orders_transport",ordersTransportDTO);
        return "features/formeditorders";
    }

    @PostMapping("/edit-orders/{id}")
    public String updateOrders(@ModelAttribute("orders") OrdersDTO ordersDTO, @PathVariable int id, @RequestParam("status") int status,Model model) {
        ordersDTO.setId(id);
        ordersDTO.setStatus(status);
        ordersService.updateOrders(id, ordersDTO);
        OrdersDetailsDTO ordersDetailsDTO = orderDetailsService.findOrdersDetailsById(id);
        model.addAttribute("orders_details",ordersDetailsDTO);
        OrdersPaymentDTO ordersPaymentDTO = ordersPaymentService.findOrdersPaymentById(id);
        model.addAttribute("orders_payment",ordersPaymentDTO);
        OrdersTransportDTO ordersTransportDTO = ordersTransportService.findOrdersTransportById(id);
        model.addAttribute("orders_transport",ordersTransportDTO);
        return "redirect:/orders";
    }

    @GetMapping("/delete-orders/{id}")
    public String deleteOrders(@PathVariable("id") int id) {
        ordersService.deleteOrders(id);
        return "redirect:/orders";
    }

    @GetMapping("/productimages")
    public String findAllProductImages(Model model){
        List<ProductImagesDTO> productImagesDTOList = productImagesService.findAll();
        model.addAttribute("productImages", productImagesDTOList);
        return "features/findallproductimages";
    }

    @GetMapping("/add-productimages")
    public String showProductImagesForm(Model model) {
        model.addAttribute("productImages", new ProductImagesDTO());
        return "features/formaddproductimages";
    }

    @PostMapping("/add-productimages")
    public String addProductImages(@ModelAttribute("productImages") ProductImagesDTO productImagesDTO, Model model, @RequestParam("file") MultipartFile file) {
        if (productImagesDTO != null) {
            try {
                productImagesService.saveProductImages(productImagesDTO, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/productimages";
    }

    @GetMapping("/edit-productimages/{id}")
    public String showUpdateProductImagesForm(@PathVariable int id, Model model) {
        ProductImagesDTO productImagesDTO =  productImagesService.findById(id);
        model.addAttribute("productImages", productImagesDTO);
        return "features/formeditproductimages";
    }

    @PostMapping("/edit-productimages/{id}")
    public String updateProductImages(@ModelAttribute("productImages") ProductImagesDTO productImagesDTO, @PathVariable int id,@RequestParam("file") MultipartFile file) {
        productImagesService.updateProductImages(id, productImagesDTO,file);
        return "redirect:/productimages";
    }

    @GetMapping("/delete-productimages/{id}")
    public String deleteProductImages(@PathVariable("id") int id) {
        productImagesService.deleteProductImages(id);
        return "redirect:/productimages";
    }
    @GetMapping("/login")
    public String showFormLogin() {
        return "layout/login";
    }


    @GetMapping("/register")
    public String showFormRegister() {
        return "layout/register";
    }

    @PostMapping("/registerAccount")
    public String registerAccount(@ModelAttribute("customer") Customer customer, BindingResult result, Model model) {
        if (result.hasErrors() || customerService.existsByUsernameOrEmail(customer.getUsername(), customer.getEmail())) {
            return "layout/register";
        }
        try {
            customer.setCreatedDate(new Date());
            customerService.saveCustomer(customer);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed. Please try again later.");
            return "layout/register";
        }
    }
    @GetMapping("/forgot-password")
    public String showFormForgotPassWord() {
        return "layout/forgot-password";
    }


}








