package com.grs.wms.sap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sap.smb.sbo.api.ICompany;
import com.sap.smb.sbo.api.IRecordset;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOCOMException;
import com.sap.smb.sbo.api.SBOCOMUtil;
import com.sap.smb.sbo.api.SBOErrorMessage;

public class Conexion {
	private ICompany company;
	private IRecordset recordSet;
	private static Conexion instancia=null;
	public static synchronized Conexion getInstancia(){
		return (instancia==null)? new Conexion():instancia;
	}
	public Conexion(){
		int connectionResult = 0;
        try 
        {
            // initialise company instance
            company = SBOCOMUtil.newCompany();
            // set database server host
            company.setServer("192.168.1.100");
            // set company database
            company.setCompanyDB("Test_GT");
            // set SAP user
            company.setUserName("manager");
            // set SAP user password
            company.setPassword("M@N4G3R");
            // set SQL server version
            company.setDbServerType(SBOCOMConstants.BoDataServerTypes_dst_MSSQL2008);
            // set whether to use trusted connection to SQL server
            company.setUseTrusted(false);
            // set SAP Business One language
            company.setLanguage(SBOCOMConstants.BoSuppLangs_ln_Spanish_La);
            // set database user
            company.setDbUserName("datos");
            // set database user password
            company.setDbPassword("datos");
            // set license server and port
            company.setLicenseServer("192.168.1.100:30000");
            
            // initialise connection
            connectionResult = company.connect();
            
            // if connection successful
            if (connectionResult == 0) 
            {
                System.out.println("Successfully connected to " + company.getCompanyName());
            }
            // if connection failed
            else 
            {
                // get error message fom SAP Business One Server
                SBOErrorMessage errMsg = company.getLastError();
                System.out.println(
                        "Cannot connect to server: "
                        + errMsg.getErrorMessage()
                        + " "
                        + errMsg.getErrorCode()
                );
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	
	public void disconnect() 
    {
        company.disconnect();
        System.out.println("Application disconnected successfully");
    }

	public ICompany getCompany() {
		return company;
	}
	
	public IRecordset obtenerConsulta(String consulta){
		IRecordset rs=null;
		try {
			rs = SBOCOMUtil.newRecordset(company);
			rs.doQuery(consulta);
		} catch (SBOCOMException e) {
			e.printStackTrace();
		}
		disconnect();
		return rs;	
	}
	public List<HashMap<String, Object>> queryToList(String query) throws SBOCOMException{
		List<HashMap<String, Object>> listaDatos=new ArrayList<>();
		IRecordset recordSet=SBOCOMUtil.newRecordset(company);
		recordSet.doQuery(query);
		Integer colCount =recordSet.getFields().getCount();
		Integer rowCount =recordSet.getRecordCount();
		recordSet.moveFirst();
		for (int row = 0; row < rowCount; row++) {
			HashMap<String,Object> rowData=new HashMap<>();
			for (int col = 0; col < colCount; col++) {
				rowData.put(recordSet.getFields().item(col).getName(), recordSet.getFields().item(col).getValue());
			}
			listaDatos.add(rowData);
			recordSet.moveNext();
		}
		recordSet.release();
		this.disconnect();
		return listaDatos;
	}
	
}
