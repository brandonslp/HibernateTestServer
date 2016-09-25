/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.service.webservices;

import cl.model.dao.ProductoDAO;
import cl.model.pojos.Productos;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author brand
 */
@WebService(serviceName = "ProductosWS")
public class ProductosWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "IngresarProducto")
    public String IngresarProducto(@WebParam(name = "nombre") String nombre, @WebParam(name = "price") float price, @WebParam(name = "quantity") int quantity, @WebParam(name = "description") String description) {
        //TODO write your implementation code here:
        Productos p = new Productos(nombre, price, quantity, description);
        ProductoDAO pdao = new ProductoDAO();
        pdao.ingresarProducto(p);
        return "Ingreso realizado - Hardcodeado :| ";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchProduct")
    public String SearchProduct(@WebParam(name = "cod") int cod) {
        //TODO write your implementation code here:
        ProductoDAO pdao = new ProductoDAO();
        return pdao.consultarProducto(cod);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAll")
    public String getAll() {
        //TODO write your implementation code here:
        ProductoDAO pdao = new ProductoDAO();
        List<Productos> list = pdao.getAll();
        String str="";
        for (Productos productos : list) {
            str+=productos.getName()+"\n";
        }
        return str;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteProduct")
    public String deleteProduct(@WebParam(name = "cod") int cod) {
        //TODO write your implementation code here:
        ProductoDAO pdao = new ProductoDAO();
        if (pdao.deleteProduct(cod)) {
            return "Registro elimnado";
        }else
            return "Error";
    }
 
}
