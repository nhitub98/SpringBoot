package com.example.demoproject.controller;

import com.example.demoproject.dto.*;
import com.example.demoproject.entities.Category;
import com.example.demoproject.entities.Orders;
import com.example.demoproject.entities.Product;
import com.example.demoproject.mapper.CategoryMapper;
import com.example.demoproject.repository.CategoryRepository;
import com.example.demoproject.repository.OrdersRepository;
import com.example.demoproject.repository.PaymentMethodRepository;
import com.example.demoproject.repository.TransportMethodRepository;
import com.example.demoproject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DemoController {
    @Autowired
    ProductService productService;
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
    @GetMapping("/products")
    public String findAllProduct(Model model) {
        List<ProductDTO> productDTOList = productService.findAll();
        model.addAttribute("product", productDTOList);
        List<CategoryDTO> categoryDTOList = categoryService.findAllCategories();
        model.addAttribute("categories", categoryDTOList);

        return "features/findallproduct";
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) { //hiển thị form add product
        model.addAttribute("product", new ProductDTO());
        return "features/formaddproduct";
    }
    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") ProductDTO productDTO, Model model) {
        if (productDTO != null) {
            productService.saveProduct(productDTO);
        }
        return "redirect:/products";
    }
    @GetMapping("/edit-product/{id}")
    public String showUpdateProductForm(@PathVariable int id, Model model) {
        ProductDTO productDTO = productService.findById(id);
        model.addAttribute("product", productDTO);
        return "features/formeditproduct";
    }
    @PostMapping("/edit-product/{id}")
    public String updateProduct(@PathVariable int id, @ModelAttribute("product") ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
        return "redirect:/products";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/categories")
    public String findAllCategories(Model model){
        List<CategoryDTO> categoryDTOList = categoryService.findAllCategories();
        model.addAttribute("categories", categoryDTOList);
        return "features/findallcategories";
    }
    @GetMapping("/add-category")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new CategoryDTO());
        return "features/formaddcategory";
    }

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute("category") CategoryDTO categoryDTO) {
        if (categoryDTO != null) {
            categoryService.saveCategory(categoryDTO);
        }
        return "redirect:/categories";
    }

    @GetMapping("/edit-category/{id}")
    public String showUpdateCategoryForm(@PathVariable int id, Model model) {
        CategoryDTO categoryDTO = categoryService.findByIdParent(id);
        model.addAttribute("category", categoryDTO);
        return "features/formeditcategory";
    }
    @PostMapping("/edit-category/{id}")
    public String updateCategory(@PathVariable int id, @ModelAttribute("category") CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO);
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

    @PutMapping("/edit-transportmethod/{id}")
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
        return "features/findallpayment";
    }

    @PostMapping("/add-paymentmethod")
    public String addPaymentMethod(@ModelAttribute("paymentmethod") PaymentMethodDTO paymentMethodDTO, Model model) {
        if (paymentMethodDTO != null) {
            paymentMethodService.savePaymentMethod(paymentMethodDTO);
        }
        return "redirect:/formaddpaymentmethods";
    }

    @GetMapping("/edit-paymentmethod/{id}")
    public String showUpdatePaymentMethodForm(@PathVariable int id, Model model) {
        PaymentMethodDTO paymentMethodDTO = paymentMethodService.findPaymentMethodById(id);
        model.addAttribute("paymentmethod", paymentMethodDTO); // Đổi từ "categories" thành "category"
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


}
