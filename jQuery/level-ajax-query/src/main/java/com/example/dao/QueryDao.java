package com.example.dao;

import com.example.entity.City;
import com.example.entity.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDao {
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;

    private String url="jdbc:mysql://127.0.0.1:3306/springdb";
    private String username = "root";
    private String password = "123";
    String sql = "";

    //查询所有的省份信息
    public List<Province> queryProvinceList(){
        List<Province> provinces = new ArrayList<>();
        Province p = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
            sql = "select id,name,jiancheng,shenghui from province order by id";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                p = new Province();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setJiancheng(rs.getString("jiancheng"));
                p.setShenghui(rs.getString("shenghui"));
                provinces.add(p);
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                if (rs != null){
                    rs.close();
                }
                if (pst != null){
                    pst.close();
                }
                if (conn != null){
                    pst.close();
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return provinces;
    }
    //查询所有的市信息
    public List<City > queryCityList(Integer provinceId){
        List<City> cities = new ArrayList<>();
        City c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
            sql = "select id, name from city where provinceid=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,provinceId);
            rs = pst.executeQuery();
            while(rs.next()){
                c = new City();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                cities.add(c);
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                if (rs != null){
                    rs.close();
                }
                if (pst != null){
                    pst.close();
                }
                if (conn != null){
                    pst.close();
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return cities;
    }
}
