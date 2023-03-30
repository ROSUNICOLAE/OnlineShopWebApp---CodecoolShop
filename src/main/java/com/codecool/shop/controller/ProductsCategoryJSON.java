package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.User;
import com.codecool.shop.serializations.ProductSerialization;
import com.codecool.shop.service.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "prodsByCategoryServlet", urlPatterns = "/api/products/category", loadOnStartup = 2)
public class ProductsCategoryJSON extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationService applicationService = new ApplicationService();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String linkId = request.getParameter("id");
        UUID id = null;

        try{
            id = UUID.fromString(linkId);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }

        if(id == null ){
            id = UUID.randomUUID();
        }

        ProductDao productDataStore = applicationService.getProductDao();
        ProductCategoryDao productCategoryDataStore = applicationService.getProductCategoryDao();
        SupplierDao supplierDao= applicationService.getSupplierDao();

        var category =  applicationService.getProductCategoryDao().find(id);
        var productList = applicationService.getProductDao().getBy(category);

        String products = writeListToJsonArray(productList);
        PrintWriter out = response.getWriter();
        out.println(products);
    }

    public String writeListToJsonArray(List<Product> products) throws IOException {
        final StringWriter sw = new StringWriter();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(sw, products);
        sw.close();

        return sw.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductSerialization ps = new ProductSerialization();
        Map<String, String> params = ps.parseReqParams(req);

        ApplicationService applicationService = new ApplicationService();



        ProductCategoryDao productCategoryDao =  applicationService.getProductCategoryDao();



        boolean added = false;

        System.out.println(params);

        if(params.containsKey("name") && params.containsKey("password")){

            User user = applicationService.getUserDao().getUserByName(params.get("name"));


            if(user != null && user.getPassword().equals(params.get("password")) && user.getRole() == Role.ADMIN){

                added = productCategoryDao.isCategoryMissing(params.get("category"), params.get("department"), params.get("description"));
            }
        }

        PrintWriter out = resp.getWriter();

        if(added){
            out.println(HttpServletResponse.SC_ACCEPTED);
        }
        else{
            out.println(HttpServletResponse.SC_EXPECTATION_FAILED);
        }
    }
}