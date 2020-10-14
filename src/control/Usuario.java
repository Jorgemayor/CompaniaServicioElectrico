package src.control;

public class Usuario {

    private String usuario;
    private int id;
    private int idRol;

    public int getId(){
        return id;
    }

    public int getRol(){
        return idRol;
    }

    public String getNombreUsuario(){
        return usuario;
    }
    
    public Usuario(int id, String usuario, int idRol){
        this.usuario = usuario;
        this.id = id;
        this.idRol = idRol;
    }
}
