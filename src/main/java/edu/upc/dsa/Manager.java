package edu.upc.dsa;

import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.Usuario;

import java.util.List;
import java.util.Set;

public interface Manager {
    public Usuario addUsuario(String id, String nom, String cognoms, String correu, String dataNaixement);
    public List<Usuario> listarUsuarios();
    public Usuario consultarUsuario(String id);
    public int usuarioPuntoInteres(String id, int x, int y);
    public Set<Usuario> listarUsuariosPunto(int x, int y);
    public PuntoInteres addPuntoInteres(ElementType elementType, int x, int y);
    public List<PuntoInteres> consultarPuntos(String id);
    public String listarPuntosElementType(ElementType elementType);
    public void clear();
    public int sizeUsuarios();
    public int sizePuntos();


}
