package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    String id;
    String nom;
    String cognoms;
    String correu;
    String dataNaixement;
    List<PuntoInteres> puntosInteres = new ArrayList<>();

    public Usuario(String id, String nom, String cognoms, String correu, String dataNaixement) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.correu = correu;
        this.dataNaixement = dataNaixement;
    }

    public void addPunto(PuntoInteres puntoInteres){
        puntosInteres.add(puntoInteres);
    }

    public String getPuntos(){
        for(PuntoInteres punto: puntosInteres) {
            return "\n" +punto.datosPunto();
        }
        return null;
    }

    public String datosUsuario(){
        return "id: "+id+", Nom: "+nom+", Cognoms: "+cognoms+", Correu "+correu+" Data de Naixement: "+dataNaixement;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public String getCorreu() {
        return correu;
    }

    public String getDataNaixement() {
        return dataNaixement;
    }
}
