/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.riesgos.utilitarios;

/**
 *
 * @author Paúl Paguay
 */
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author ulvier
 */
public class Tools {

    public static String formato4decimal(double var) {
        String valor = "";
        try {
            Locale.setDefault(Locale.ENGLISH);
            DecimalFormat num = new DecimalFormat("##########.####");
            valor = num.format(var);
        } catch (Exception ex) {
            valor = "Error!!";
        }
        return valor;
    }

    public static String formato2decimal(double var) {
        String valor = "";
        try {
            Locale.setDefault(Locale.ENGLISH);
            DecimalFormat num = new DecimalFormat("##########.##");
            valor = num.format(var);
        } catch (Exception ex) {
            valor = "____";
        }
        return valor;
    }
    
    public static String formato2decimal(String var) {
        String valor = "";
        try {
            Locale.setDefault(Locale.ENGLISH);
            DecimalFormat num = new DecimalFormat("##########.##");
            valor = num.format(Double.parseDouble(var));
        } catch (Exception ex) {
            valor = "____";
            System.out.println(ex.getMessage());
        }
        return valor;
    }

    public static java.sql.Date obtieneFecha(Object fecha) throws ParseException {
        java.sql.Date dt = null;
        if (fecha != null) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d = format.parse(fecha.toString());
            dt = new java.sql.Date(d.getTime());
        }
        return dt;
    }
    public static java.sql.Date obtieneFecha(Object fecha, String formatoentrada) throws ParseException {
        java.sql.Date dt = null;
        if (fecha != null) {
            DateFormat format = new SimpleDateFormat(formatoentrada);
            java.util.Date d = format.parse(fecha.toString());
            dt = new java.sql.Date(d.getTime());
        }
        return dt;
    }

    public static java.sql.Date obtieneDate(java.util.Date fecha) throws ParseException {
        return new java.sql.Date(fecha.getTime());
    }

    public static java.sql.Timestamp obtieneTimeStamp(java.util.Date fecha) throws ParseException {
        return (new java.sql.Timestamp(fecha.getTime()));
    }

    public static String obtieneFechaconFormato(Object fecha, String formatosalida) throws ParseException {
        String dsal = "";
        if (fecha != null) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date d = format.parse(fecha.toString());
            SimpleDateFormat formatsal = new SimpleDateFormat(formatosalida);
            dsal = formatsal.format(d);
        }
        return dsal;
    }

    public static long obtieneFechaActualenMiliseg() throws ParseException {
        java.util.Date dt = new java.util.Date();
        long fecha = dt.getTime();
        return fecha;
    }

    public static String obtieneFechaHoraActual() {
        java.util.Date fechaActual = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(fechaActual);
        return d;
    }
public static String obtieneFechaActual() {
        java.util.Date fechaActual = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(fechaActual);
        return d;
    }
    public static String obtieneFechaFormatoCompleto(Date fecha) {
        String res = "";
        try {
            java.util.Date fechaActual = obtieneFecha(fecha);
            SimpleDateFormat format = new SimpleDateFormat("dd & MMMM | yyyy");
            String d = format.format(fechaActual).replace("&", "de").replace("|", "del");
            res = d;
        } catch (Exception e) {
            res = "";
        }
        return res;

    }
    

    public static boolean fileExist(String path) {
        File fe = new File(path);
        return fe.exists();
    }

}
