package com.neux.spring.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RestController
@RequestMapping("/member")
public class MemberController {

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

    @RequestMapping(value = "/login",method = RequestMethod.POST)

    public String login(@RequestParam("username") String username,@RequestParam("password") String password) {
        JSONObject jsonObject = new JSONObject();

        boolean success = false;
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.122:3306/cms3.5?useSSL=false&serverTimezone=UTC","root","iiipwd");

            PreparedStatement ps = conn.prepareStatement("select password from ec_insurance_member where member_id = ?");
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String dbPassword = rs.getString("password");
                if(password.equalsIgnoreCase(dbPassword)) success = true;
            }
        }catch(Exception e) {
            logger.error("MemberController login fail!!",e);
        }finally{
            try{
                if(conn != null) {
                    conn.close();
                }
            }catch(Exception ex) {
                ;
            }
        }

        jsonObject.put("success",success);

        return jsonObject.toString();
    }

}
