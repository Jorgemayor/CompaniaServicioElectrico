package src.managers;

import org.junit.Test;
import org.junit.Assert;

import src.managers.gestionClientes.GestionClienteLib;
import src.managers.gestionActivos.GestionActivosLib;
import src.managers.gestionCiudad.GestionCiudadLib;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.json.JSONObject;

public class PruebasLib {

    public PruebasLib(){
        probarGestionClienteLib();
        probarGestionCiudadLib();
        imprimirResultados();
    }
    @Test
    private void probarGestionClienteLib() {
        GestionClienteLib lib = new GestionClienteLib();
        try {
            assertEquals("{\"tipo_identificacion\":[\"RC\"],\"direccion\":[\"calle locura 47\"],\"id_ciudad\":[\"8\"],\"identificacion\":[\"147852\"],\"id\":[\"4\"],\"habilitado\":[true],\"nombre\":[\"Caditos Parra\"]}", lib.obtenerClientePorId(147852));
            assertEquals("{\"tipo_identificacion\":[\"CC\"],\"direccion\":[\"sí\"],\"id_ciudad\":[\"191\"],\"identificacion\":[\"1107523456\"],\"id\":[\"5\"],\"habilitado\":[true],\"nombre\":[\"Niconi\"]}", lib.obtenerClientePorId(1107523456));
            assertEquals("{\"tipo_identificacion\":[\"CC\"],\"direccion\":[\"prueba12345\"],\"id_ciudad\":[\"28\"],\"identificacion\":[\"12345\"],\"id\":[\"3\"],\"habilitado\":[true],\"nombre\":[\"prueba12345\"]}", lib.obtenerClientePorId(12345));
            assertEquals("{\"tipo_identificacion\":[\"RC\"],\"direccion\":[\"calle x 123\"],\"id_ciudad\":[\"9\"],\"identificacion\":[\"123456789\"],\"id\":[\"1\"],\"habilitado\":[true],\"nombre\":[\"Cesar Becerra\"]}", lib.obtenerClientePorId(123456789));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void probarGestionCiudadLib() {
        GestionCiudadLib lib = new GestionCiudadLib();
        try {
            assertEquals("SAN JACINTO DEL CAUCA",lib.obtenerCiudadPorId(55));
            assertEquals("SAN JUAN NEPOMUCENO",lib.obtenerCiudadPorId(56));
            assertEquals("SAN MARTIN DE LOBA",lib.obtenerCiudadPorId(57));
            assertEquals("SAN PABLO",lib.obtenerCiudadPorId(58));
            assertEquals("SANTA CATALINA",lib.obtenerCiudadPorId(59));
            assertEquals("SANTA ROSA",lib.obtenerCiudadPorId(60));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void imprimirResultados(){
        GestionActivosLib lib = new GestionActivosLib();
        try {
            assertEquals("{\"numero_serie\":[\"ABC123\"],\"estado\":[\"Dañado\"],\"id_ciudad\":[1],\"id\":[\"1\"],\"habilitado\":[true],\"nombre\":[\"ACTIVO1\"]}", lib.obtenerActivoPorSerial("ABC123"));
            assertEquals("{\"numero_serie\":[\"serial12345\"],\"estado\":[\"Dañado\"],\"id_ciudad\":[18],\"id\":[\"2\"],\"habilitado\":[true],\"nombre\":[\"prueba12345\"]}", lib.obtenerActivoPorSerial("serial12345"));
            assertEquals("{\"numero_serie\":[\"serie1234\"],\"estado\":[\"Buen estado\"],\"id_ciudad\":[8],\"id\":[\"3\"],\"habilitado\":[true],\"nombre\":[\"activoPrueba\"]}",lib.obtenerActivoPorSerial("serie1234"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
