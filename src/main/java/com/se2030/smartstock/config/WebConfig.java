package com.se2030.smartstock.config;

import com.se2030.smartstock.model.Category;
import com.se2030.smartstock.model.Product;
import com.se2030.smartstock.model.Supplier;
import com.se2030.smartstock.service.CategoryService;
import com.se2030.smartstock.service.ProductService;
import com.se2030.smartstock.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Converts entity IDs coming from Thymeleaf select dropdowns (as Strings)
 * back into the actual JPA entity when a form is submitted.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;

    /**
     * Only these portal paths require a login. The landing page, the about
     * page, the login page itself and static files stay public.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns(
                        "/dashboard",
                        "/products/**",
                        "/suppliers/**",
                        "/categories/**",
                        "/purchases/**",
                        "/stocks/**",
                        "/users/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, Category>() {
            @Override
            public Category convert(String source) {
                if (source == null || source.trim().isEmpty()) {
                    return null;
                }
                return categoryService.getCategoryById(Long.valueOf(source));
            }
        });

        registry.addConverter(new Converter<String, Supplier>() {
            @Override
            public Supplier convert(String source) {
                if (source == null || source.trim().isEmpty()) {
                    return null;
                }
                return supplierService.getSupplierById(Long.valueOf(source));
            }
        });

        registry.addConverter(new Converter<String, Product>() {
            @Override
            public Product convert(String source) {
                if (source == null || source.trim().isEmpty()) {
                    return null;
                }
                return productService.getProductById(Long.valueOf(source));
            }
        });
    }
}
