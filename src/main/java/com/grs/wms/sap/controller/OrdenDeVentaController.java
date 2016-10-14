package com.grs.wms.sap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.grs.wms.sap.Conexion;
import com.sap.smb.sbo.api.IRecordset;
import com.sap.smb.sbo.api.SBOCOMException;

public class OrdenDeVentaController {
	private static OrdenDeVentaController instancia=null;
	public static OrdenDeVentaController getInstancia(){
		return (instancia==null)?new OrdenDeVentaController():null;
	}
	//Obtiene Ordenes de ventas pendientes - De SAP
	public List<HashMap<String,Object>> getAll() throws SBOCOMException{
		List<HashMap<String,Object>> listaOV=Conexion.getInstancia().queryToList("Select"+ 
												        		" DocEntry"+
																",DocNum"+
																",CASE when CANCELED = 'Y' then 'Cancelled' ELse 'Not Cancelled' END as [Canceled]"+
																",CASE when DocStatus = 'C' then 'Closed' Else 'Open' end as [DocStatus]"+
																",CardName"+
																",Address2 'Entrega'"+
															"From ORDR "+
																"where "+ 
																	"DocStatus='O'"+ 
																		"AND " +
												        			"DocType='I'");
		
		return listaOV;
	}
	public List<HashMap<String,Object>> getAllItems(Integer numOrdenDeVenta) throws SBOCOMException{
		List<HashMap<String,Object>> listaItems=Conexion.getInstancia().queryToList("Select ItemCode,Dscription,Quantity from rdr1 where DocEntry="+numOrdenDeVenta);
		return listaItems;
	}
}
