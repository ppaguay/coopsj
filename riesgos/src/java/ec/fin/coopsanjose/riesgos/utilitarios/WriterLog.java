/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.riesgos.utilitarios ;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriterLog {

    private String strPath;
    private String strFileName;
    private String strExtension = "log";
    private FileOutputStream fosFile;

    public WriterLog() {
        configuration();
    }

    private void configuration() {
        java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("ec.fin.coopsanjose.riesgos.utilitarios.log");
        this.strPath = Configuracion.getString("path");
       
        this.strPath = System.getProperty("jboss.home.dir")+"/standalone/log/";
        
        String sSistemaOperativo =System.getProperty("os.name");
        if(sSistemaOperativo.length()>=7)
            sSistemaOperativo=sSistemaOperativo.substring(0,7);
        if(sSistemaOperativo.equalsIgnoreCase("WINDOWS"))
            this.strPath=System.getProperty("jboss.home.dir")+"\\standalone\\log\\";
    }

    // propiedades
    /**
     * Obtiene el path o ubicacion del archivo
     * 
     * @return Ubicacion fisica del archivo
     */
    public String getStrPath() {
        return strPath;
    }

    /**
     * Asigna la ubicacion del archivo
     * 
     * @param strPath Ubicacion fisica del archivo
     */
    public void setStrPath(String strPath) {
        this.strPath = strPath;
    }

    /**
     * Obtiene el nombre del archivo sin la extension
     * 
     * @return Nombre del archivo
     */
    public String getStrFileName() {
        return strFileName;
    }

    /**
     * Asigna el nombre del archivo con el cual se creara el archivo fisico
     * 
     * @param strFileName Nombre del archivo
     */
    public void setStrFileName(String strFileName) {
        this.strFileName = strFileName;
    }

    /**
     * Obtiene el objeto para crear el archivo con la ubicacion y nombre del archivo
     * 
     * @return Objeto creador del archivo
     */
    public FileOutputStream getFosFile() {
        return fosFile;
    }

    /**
     * Asigna el objeto para crear el archivo de transacciones
     * 
     * @param fosFile Objeto creador del archivo
     */
    public void setFosFile(FileOutputStream fosFile) {
        this.fosFile = fosFile;
    }

    /**
     * Obtiene la extension del archivo
     * 
     * @return Extension del archivo
     */
    public String getStrExtension() {
        return strExtension;
    }

    /**
     * Asigna la extension del archivo 
     * 
     * @param strExtension Extension del archivo
     */
    public void setStrExtension(String strExtension) {
        this.strExtension = strExtension;
    }

    // metodos generales    
    public void write(String strClase, String strMetodo, String strExcepcion, String strDescripcion) {
        try {     
            this.strFileName = Tools.obtieneFechaActual();
            this.fosFile = new FileOutputStream(this.strPath + "riesgos_"+this.strFileName + "." + this.strExtension, true);
            PrintStream ps = new PrintStream(this.fosFile);

            ps.println("Clase: " + strClase);
            ps.println("Método: " + strMetodo);
            ps.println("Excepción: " + strExcepcion);
            ps.println("Descripción: " + strDescripcion);
            ps.println("Hora: " + (new Date().toString().substring(11, 19)));
            ps.println("-----------------------------------------------------------");
            ps.println();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WriterLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}