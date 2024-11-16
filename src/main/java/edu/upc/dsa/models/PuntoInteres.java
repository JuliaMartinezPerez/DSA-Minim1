package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PuntoInteres {
    ElementType elementType;
    int horizontal;
    int vertical;
    Set<Usuario> usuariosPunto;

    public PuntoInteres() {
    }

//    public PuntoInteres(ElementType elementType, int x, int y){
//        this.elementType = elementType;
//        horizontal = x;
//        vertical=y;
//        usuariosPunto = new ArrayList<>();
//    }

    public PuntoInteres(ElementType elementType, int x, int y) {
        this();
        this.setHorizontal(x);
        this.setElementType(elementType);
        this.setVertical(y);
        usuariosPunto = new HashSet<>();
    }

    public PuntoInteres setElementType(ElementType elementType) {
        this.elementType = elementType;
        return this;
    }

    public PuntoInteres setHorizontal(int horizontal) {
        this.horizontal = horizontal;
        return this;
    }

    public PuntoInteres setVertical(int vertical) {
        this.vertical = vertical;
        return this;
    }

    public String datosPunto() {
        return "ElementType: " + elementType + ", Coordenadas: [" + horizontal + ", " + vertical + "]";
    }

    public String getCoordenadas() {
        return "[" + horizontal + ", " + vertical + "]";
    }

    public Set<Usuario> getUsuarios() {
        return usuariosPunto;
    }
    public StringBuilder getDatosUsuarios(){
        StringBuilder datos = new StringBuilder();
        for(Usuario usuario: usuariosPunto) {
            datos.append( "\n" +usuario.datosUsuario());
        }
        return datos;
    }

    public void addUsuarioPunto(Usuario usuario) {
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
}
