package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class PuntoInteres {
    ElementType elementType;
    int horizontal;
    int vertical;
    List<Usuario> usuariosPunto = new ArrayList<>();

    public PuntoInteres(ElementType elementType, int x, int y){
        this.elementType = elementType;
        horizontal = x;
        vertical=y;
    }

    public String datosPunto(){
        return "ElementType: "+elementType+", Coordenadas: ["+horizontal+", "+vertical+"]";
    }
    public String getCoordenadas(){
        return "\n["+horizontal+", "+vertical+"]";
    }
    public String getUsuarios(){
        for(Usuario usuario: usuariosPunto) {
            return "\n" +usuario.datosUsuario();
        }
        return null;
    }
    public void addUsuarioPunto(Usuario usuario){
        this.usuariosPunto.add(usuario);
    }

    public ElementType getElementType() {
        return elementType;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public List<Usuario> getUsuariosPunto() {
        return usuariosPunto;
    }
}
