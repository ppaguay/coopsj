/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fin.coopsanjose.riesgos.rnegocio.dao;

import java.util.ArrayList;

/**
 *
 * @author Paul
 */
public interface IConsultaDao {
    ArrayList<String[]> consultageneral(String fechainicio, String fechafin);
}
