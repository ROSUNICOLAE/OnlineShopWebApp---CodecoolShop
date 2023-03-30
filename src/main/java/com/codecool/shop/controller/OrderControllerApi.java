package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.ApplicationService;
import com.codecool.shop.utils.LoggerService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "orderApi", urlPatterns = "/order/api", loadOnStartup = 9)
public class OrderControllerApi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationService applicationService = new ApplicationService();


            OrderDao orderDao = applicationService.getOrderDao();


        if (req.getParameter("first-name") != null) {




            HttpSession session=req.getSession();
            UUID userId = null;
            try{
                userId = UUID.fromString(session.getAttribute("user-id").toString());
            }
            catch (Exception e){
                e.printStackTrace();
            }

            Map<String, String> clientDetails = new HashMap<>();
            CartDao cart = applicationService.getCartDao();


            clientDetails.put("First Name", req.getParameter("first-name"));
            clientDetails.put("Last Name", req.getParameter("last-name"));
            clientDetails.put("Email", req.getParameter("email"));
            clientDetails.put("Phone", req.getParameter("phone"));
            clientDetails.put("Address", req.getParameter("address"));

            Order order = orderDao.createOrder(clientDetails, cart, userId);


            try{

                session = req.getSession();
                session.setAttribute("order-id", order.getOrderId());


            }catch(Exception e){e.printStackTrace();}




            if (session.getAttribute("user-name") != null) {
                resp.sendRedirect(req.getContextPath() + "/card-payment");
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationService applicationService = new ApplicationService();

        OrderDao orderDao = applicationService.getOrderDao();

        HttpSession session=req.getSession();
        UUID orderId = null;
        try{
            orderId = UUID.fromString(session.getAttribute("order-id").toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        LoggerService l = LoggerService.getInstance();


//        Order currentOrder = orderService.getOrder(orderId);

        Order currentOrder = orderDao.getOrder(orderId);
        //TODO add to DB, next sprint
        l.log(currentOrder);
        currentOrder.setOrderConfirmed(true);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("Success","Yes");


        resp.sendRedirect(req.getContextPath() + "/success");
    }
}
