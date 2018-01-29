package ec.fin.coopsanjose.riesgos.rnegocio.daoimpl;

import ec.fin.coopsanjose.riesgos.accesodatos.*;
import ec.fin.coopsanjose.riesgos.rnegocio.dao.IConsultaDao;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDaoImpl implements IConsultaDao {

    @Override
    public ArrayList<String[]> consultageneral(String fechainicio, String fechafin) {
        ArrayList<String[]> datos = new ArrayList<>();
        String sql = "SELECT  IFNULL(incident.ID_INCIDENT_PROJECT,0) AS ID_INCIDENT_PROJECT, IFNULL(IFNULL((SELECT tlv.VALUE FROM list_values lv INNER JOIN "
                + "translate_list_values tlv ON (tlv.ID_LIST_VALUE = lv.ID_LIST_VALUE AND tlv.ID_LANGUAGE='ESP') WHERE lv.code_value=incident.STATUS AND lv.ID_FIELD=36),"
                + "incident.STATUS),'- Sin valor -') AS STATUS, IFNULL((SELECT CONCAT(U.NAME,' ',U.SURNAME) FROM agent A INNER JOIN username U ON (U.ID_USERNAME = "
                + "A.ID_USERNAME) WHERE A.ID_AGENT=incident.ASSIGNED_TO),'- Sin valor -') AS ASSIGNED_TO, DATE_FORMAT(DATE_ADD(incident.CREATION_DATE,INTERVAL 0 HOUR),"
                + "'%d/%m/%Y %H:%i') AS CREATION_DATE, IFNULL(IFNULL((SELECT tlv.VALUE FROM list_values lv INNER JOIN translate_list_values tlv ON (tlv.ID_LIST_VALUE = "
                + "lv.ID_LIST_VALUE AND tlv.ID_LANGUAGE='ESP') WHERE lv.code_value=incident_0001.sucursal AND lv.ID_FIELD=122),incident_0001.sucursal),'- Sin valor -') "
                + "AS sucursal, IFNULL(incident_0001.NAME,'- Sin valor -') AS NAME, IFNULL(incident_0001.SURNAME1,'- Sin valor -') AS "
                + "SURNAME1, IFNULL(IFNULL((SELECT tlv.VALUE FROM list_values lv INNER JOIN translate_list_values tlv ON (tlv.ID_LIST_VALUE = lv.ID_LIST_VALUE AND "
                + "tlv.ID_LANGUAGE='ESP') WHERE lv.code_value=incident_0001.tipo AND lv.ID_FIELD=115),incident_0001.tipo),'- Sin valor -') AS tipo, "
                + "IFNULL(IFNULL((SELECT tlv.VALUE FROM list_values lv INNER JOIN translate_list_values tlv ON (tlv.ID_LIST_VALUE = lv.ID_LIST_VALUE AND "
                + "tlv.ID_LANGUAGE='ESP') WHERE lv.code_value=incident_0001.categoria AND lv.ID_FIELD=97),incident_0001.categoria),'- Sin valor -') AS categoria, "
                + "IFNULL(incident.TITLE,'- Sin valor -') AS TITLE, IFNULL(incident_0001.cedula_cliente,'- Sin valor -') AS cedula_cliente, "
                + "IFNULL(incident_0001.nombre_cliente,'- Sin valor -') AS nombre_cliente, IFNULL(IFNULL((SELECT tlv.VALUE FROM list_values lv INNER JOIN "
                + "translate_list_values tlv ON (tlv.ID_LIST_VALUE = lv.ID_LIST_VALUE AND tlv.ID_LANGUAGE='ESP') WHERE lv.code_value=incident_0001.Subcategoria AND "
                + "lv.ID_FIELD=114),incident_0001.Subcategoria),'- Sin valor -') AS Subcategoria, IFNULL(IFNULL((SELECT tlv.VALUE FROM list_values lv INNER JOIN "
                + "translate_list_values tlv ON (tlv.ID_LIST_VALUE = lv.ID_LIST_VALUE AND tlv.ID_LANGUAGE='ESP') WHERE lv.code_value=incident_0001.tipo_transaccion "
                + "AND lv.ID_FIELD=116),incident_0001.tipo_transaccion),'- Sin valor -') AS tipo_transaccion, IFNULL(incident_0001.usuario_sistema,'- Sin valor -')"
                + " AS usuario_sistema, IFNULL(IFNULL((SELECT tlv.VALUE FROM list_values lv INNER JOIN translate_list_values tlv ON (tlv.ID_LIST_VALUE = lv.ID_LIST_VALUE "
                + "AND tlv.ID_LANGUAGE='ESP') WHERE lv.code_value=incident_0001.Autorizador AND lv.ID_FIELD=96),incident_0001.Autorizador),'- Sin valor -') AS Autorizador, "
                + "IFNULL(incident.DESCRIPTION,'- Sin valor -') AS DESCRIPTION FROM incident, incident_0001 WHERE incident_0001.ID_INCIDENT=incident.ID_INCIDENT  AND "
                + "(incident.ID_PROJECT=1 AND  (incident.CREATION_DATE>=?"
                + " AND incident.CREATION_DATE <=?))   ORDER BY  incident.CREATION_DATE ASC";
        ArrayList<Parametro> parametros = new ArrayList<>();
        parametros.add(new Parametro(1, fechainicio));
        parametros.add(new Parametro(2, fechafin));
        try {
            ConjuntoResultado crs = AccesoDatos.ejecutaQuery(sql, parametros);
            String[] registro;
            while (crs.next()) {
                registro = new String[]{crs.getString(1), crs.getString(2), crs.getString(3),
                    crs.getString(4), crs.getString(5), crs.getString(6), crs.getString(7),
                    crs.getString(8), crs.getString(9), crs.getString(10), crs.getString(11), crs.getString(12),
                    crs.getString(13), crs.getString(14), crs.getString(15), crs.getString(16), crs.getString(17)};
                datos.add(registro);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener datos consulta general:" + e.getMessage());
        }
        return datos;
    }

}
