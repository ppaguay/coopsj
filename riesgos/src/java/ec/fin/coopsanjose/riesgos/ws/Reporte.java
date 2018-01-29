/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.riesgos.ws;

import ec.fin.coopsanjose.riesgos.rnegocio.dao.IConsultaDao;
import ec.fin.coopsanjose.riesgos.rnegocio.daoimpl.ConsultaDaoImpl;
import ec.fin.coopsanjose.riesgos.utilitarios.WriterLog;
import ec.fin.coopsanjose.riesgos.utilitarios.HandlerExample;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONObject;

/**
 *
 * @author Paul
 */
@Path("reporte")
public class Reporte {

//    @GET
//    @Path("/general/{fechai}/{fechaf}")
//    @Produces("application/json")
//    public List<String[]> getgeneral(@PathParam("fechai") String fechai, @PathParam("fechaf") String fechaf) {
//        WriterLog log = new WriterLog();
//        ArrayList<String[]> datos = new ArrayList<>();
//        try {
//            IConsultaDao consultaDao = new ConsultaDaoImpl();
//            datos = consultaDao.consultageneral(fechai, fechaf);
//            log.write(Reporte.class.getName(), "general", "Consulta Satisfactoria", "");
//        } catch (Exception e) {
//            System.out.println("Error en el sw: " + e.getMessage());            
//        }
//        return datos;
//    }
    private static final Logger LOGGER = Logger.getLogger(Reporte.class.getName());

    @POST
    @Path("/general")
    @Consumes("application/json")
    @Produces("application/json")
    public List<String[]> general(String fechas) {
        ArrayList<String[]> datos = new ArrayList<>();
        WriterLog log = null;
        try {
            log = new WriterLog();
            IConsultaDao consultaDao = new ConsultaDaoImpl();
            JSONObject objJson = new JSONObject(fechas);
            datos = consultaDao.consultageneral(objJson.getString("fechai"), objJson.getString("fechaf"));
            log.write(Reporte.class.getName(), "general", "Consulta Satisfactoria", "");
        } catch (Exception e) {
            System.out.println("Error en el sw: " + e.getMessage());
            if (log != null) {
                log.write(Reporte.class.getName(), "general", "Error", e.getMessage());
            }
        }
        return datos;
    }
}
