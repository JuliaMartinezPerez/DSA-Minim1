package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Usuario {

    String id;
    String nom;
    String cognoms;
    String correu;
    String dataNaixement;
    List<PuntoInteres> puntosInteres;

    public Usuario(){
    }

//    public Usuario(String id, String nom, String cognoms, String correu, String dataNaixement) {
//        this.id = id;
//        this.nom = nom;
//        this.cognoms = cognoms;
//        this.correu = correu;
//        this.dataNaixement = dataNaixement;
//    }
    public Usuario(String id, String nom, String cognoms, String correu, String dataNaixement) {
        this();
        this.setId(id);
        this.setNom(nom);
        this.setCognoms(cognoms);
        this.setCorreu(correu);
        this.setDataNaixement(dataNaixement);
        //puntosInteres = new HashSet<>();
        puntosInteres = new ArrayList<>();
    }

    public void addPunto(PuntoInteres puntoInteres){
        puntosInteres.add(puntoInteres);
        puntoInteres.addUsuarioPunto(this);
    }

    public List<PuntoInteres> getPuntos(){
        return puntosInteres;
    }
    public StringBuilder getDatosPuntos(){
        StringBuilder datos = new StringBuilder();
        for(PuntoInteres punto: puntosInteres) {
            datos.append("\n" +punto.datosPunto());
        }
        return datos;
    }

    public String datosUsuario(){
        return "id: "+id+", Nom: "+nom+", Cognoms: "+cognoms+", Correu "+correu+", Data de Naixement: "+dataNaixement;
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

    public Usuario setId(String id) {
        this.id = id;
        return this;
    }

    public Usuario setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public Usuario setCognoms(String cognoms) {
        this.cognoms = cognoms;
        return this;
    }

    public Usuario setCorreu(String correu) {
        this.correu = correu;
        return this;
    }

    public Usuario setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
        return this;
    }
}
