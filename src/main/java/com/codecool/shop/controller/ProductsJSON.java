package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.User;
import com.codecool.shop.serializations.ProductSerialization;
import com.codecool.shop.service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@WebServlet(name = "productsServlet", urlPatterns = "/api/products/product", loadOnStartup = 7)
public class ProductsJSON extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductSerialization ps = new ProductSerialization();
        Map<String, String> params = ps.parseReqParams(req);


        ApplicationService applicationService = new ApplicationService();

        ProductDao productDao =  applicationService.getProductDao();
        SupplierDao supplierDao = applicationService.getSupplierDao();
        ProductCategoryDao productCategoryDao = applicationService.getProductCategoryDao();


        boolean added = false;

        System.out.println(params);

        if (params.containsKey("name") && params.containsKey("password")) {
            User user = applicationService.getUserDao().getUserByName(params.get("name"));

            if (user != null && user.getPassword().equals(params.get("password")) && user.getRole() == Role.ADMIN) {

                added = productDao.isProductMissing(supplierDao, productCategoryDao, params.get("productname"), params.get("defaultprice"),
                        params.get("defaultCurrency"), params.get("description"),params.get("productCategory"),
                        params.get("supplier"), params.get("img"));
            }
        }

        PrintWriter out = resp.getWriter();

        if (added) {
            out.println(HttpServletResponse.SC_ACCEPTED);
        } else {
            out.println(HttpServletResponse.SC_EXPECTATION_FAILED);
        }
    }
}
