package com.example.controller;

import com.example.dao.QueryDao;
import com.example.entity.Province;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class QueryProvinceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s ="";
        //调用dao获取所有的省份信息，它是一个List集合
        QueryDao dao = new QueryDao();
        List<Province> provinces = dao.queryProvinceList();
        //把list转为json格式的数据，输出给ajax请求
        if (provinces != null) {
            ObjectMapper om = new ObjectMapper();
            s = om.writeValueAsString(provinces);
        }
        //输出ajax数据，响应ajax请求，返回数据
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(s);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
