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

    /**
     * Servicio que ofrece el html con el nobre de usuario y el link de cierre
     * de sesion
     *
     * @param req Contexto del usuario que llama al servicio de donde se
     * obtienen los datos de sesion
     */
    @GET
    @Path("session/userstack")

    public String getLogedUserStack(@Context HttpServletRequest req) {

        return "<b>" + req.getRemoteUser() + "</b></br><a href=\"webresources/auth/session/logout\">Log Out</a>";
    }

    /**
     * Servicio que deslogea al usuario que lo invoca, y luego lo redirige a la
     * página de logout.html
     *
     * @param req Contexto del usuario que llama al servicio de donde se
     * obtienen o modifican los datos de sesion
     * @param res Contexto del usuario en la respuesta del servicio
     */
    @GET
    @Path("session/logout")
    public void logout(@Context HttpServletRequest req, @Context HttpServletResponse res) {
        try {
            //Se invalida la sesión del usuario que llama al servicio
            req.getSession().invalidate();
            //Se le indica redirigirse a la página de logout
            res.sendRedirect(req.getContextPath() + "/logout.html");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Servicio que autentica un usuario ante el sistema
     *
     * @param req Contexto del usuario que llama al servicio de donde se
     * obtienen o modifican los datos de sesion
     * @param usr nombre de usuario a autenticar
     * @param pwd contraseña del usuario
     */
    @POST
    public String appLogin(@Context HttpServletRequest req, String usr, String pwd) {
        try {
            req.login(usr, pwd);
            return "Login success";
        } catch (ServletException ex) {
            return "Login Error";
        }

    }

    /**
     * Servicio que retruna el nombre del usuario autenticado. Si no lo está
     * retorna null
     *
     * @param req Contexto del usuario que llama al servicio de donde se
     * obtienen los datos de sesion
     */
    @GET
    @Path("session/user")
    public String getLogedUser(@Context HttpServletRequest req) {

        return req.getRemoteUser();
    }

}
