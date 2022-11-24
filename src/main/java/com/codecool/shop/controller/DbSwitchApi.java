package com.codecool.shop.controller;

import com.codecool.shop.manager.DatabaseManager;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;


@WebServlet(name = "dbSwitchApi", urlPatterns = "/api/dbswitch", loadOnStartup = 9)
public class DbSwitchApi extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ApplicationService applicationService = new ApplicationService();

        HttpSession session = req.getSession();
        UUID userId = null;
        try{
            userId = UUID.fromString(session.getAttribute("user-id").toString());
            System.out.println(applicationService.getUserDao().getUserByName("admin"));
            System.out.println(session.getAttribute("user-id"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        User user =  applicationService.getUserDao().getUserById(userId);

        if (user != null && user.getRole() == Role.ADMIN){
            DatabaseManager.switchBetweenDb_InMem();
            session.invalidate();

        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
