package co.edu.uniandes.csw.login.service;

import java.io.IOException;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/auth")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_HTML)
public class AuthService {

    @GET
    @Path("session/user")
    public String getLogedUserStack(@Context HttpServletRequest req) {
        
        return "<b>"+req.getRemoteUser()+"</b></br><a href=\"webresources/auth/session/logout\">Log Out</a>";
    }

    @GET
    @Path("session/logout")
    public void logout(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            req.getSession().invalidate();
            res.sendRedirect(req.getContextPath() + "/logout.html");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @POST
    public String appLogin(@Context HttpServletRequest req, String usr, String pwd) {
        try {
            req.login(usr, pwd);
            return "Login success";
        } catch (ServletException ex) {
            return "Login Error";
        }

    }
}
