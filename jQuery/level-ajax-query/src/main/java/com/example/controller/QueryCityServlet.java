package com.example.controller;

import com.example.dao.QueryDao;
import com.example.entity.City;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class QueryCityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = "";
        String pioid = req.getParameter("pioid");
        if (pioid != null && !"".equals(pioid)){
            QueryDao dao = new QueryDao();
            List<City> cities = dao.queryCityList(Integer.parseInt(pioid));
            ObjectMapper mapper = new ObjectMapper();
            s = mapper.writeValueAsString(cities);
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(s);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
