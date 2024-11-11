package edu.upc.dsa;

import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.Usuario;

import java.util.List;

public interface Manager {
    //usuarios:
    public Usuario addUsuario(String id, String nom, String cognoms, String correu, String dataNaixement);//POST {usuario}
    public List<Usuario> listarUsuarios();//GET all
    public String consultarUsuario(String id);//GET {id}
    public void usuarioPuntoInteres(String id, int x, int y);//GET {id, x, y}
    public void listarUsuariosPunto(int x, int y); //GET{x, y}


    //punto interes:
    public PuntoInteres addPuntoInteres(ElementType elementType, int x, int y);//POST {punto}
    public void consultarPuntos(Usuario usuario); //GET {usuario}
    public void listarPuntosElementType(ElementType elementType);//GET {elementType}


    //altres:
    public void clear();
    public int sizeUsuarios();
    public int sizePuntos();


}
