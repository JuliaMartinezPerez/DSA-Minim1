package edu.upc.dsa;

import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.*;

public class ManagerImpl implements Manager {
    private static Manager instance;
    private List<Usuario> usuarios;
    private List<PuntoInteres> puntos;

    final static Logger logger = Logger.getLogger(ManagerImpl.class);

    private ManagerImpl() {
        this.usuarios = new ArrayList<>();
        this.puntos = new ArrayList<>();
    }

    public static Manager getInstance() {
        if (instance == null) instance = new ManagerImpl();
        return instance;
    }

    //Añadir un usuario
    @Override
    public Usuario addUsuario(String id, String nom, String cognoms, String correu, String dataNaixement) {
        Usuario usuarioNuevo = new Usuario(id, nom, cognoms, correu, dataNaixement);
        this.usuarios.add(usuarioNuevo);
        logger.info("Nuevo usuario añadido");
        return usuarioNuevo;
    }

    //Listar todos los usuarios ordenados alfabéticamente (apellidos, nombre).
    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarioNombres = new ArrayList<>(this.usuarios);
        usuarioNombres.sort((d1, d2) -> d2.getCognoms().compareTo(d1.getCognoms()));
        for (Usuario usuario : usuarioNombres) {
            logger.info("Usuario: " + usuario.getCognoms() + ", " + usuario.getNom());
        }
        return usuarioNombres;
    }

    //Consultar la información de un usuario usando su identificador
    @Override
    public String consultarUsuario(String id) {
        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getId(), id)) {
                logger.info(usuario.datosUsuario());
                return usuario.datosUsuario();
            }
        }
        return null;
    }

    //Añadir un punto de interés en el mapa
    @Override
    public PuntoInteres addPuntoInteres(ElementType elementType, int x, int y) {
        PuntoInteres puntoNuevo = new PuntoInteres(elementType, x, y);
        puntos.add(puntoNuevo);
        logger.info("Nuevo punto de interés añadido");
        return puntoNuevo;
    }

    //Registrar que un usuario pasa por un punto de interés, identificándolo
    //con el id del usuario y la posición del punto de interés
    @Override
    public void usuarioPuntoInteres(String id, int x, int y) {
//        int usuarioPunto = 0;
//        int puntoInteres = 0;
//        for (Usuario usuario : usuarios) {
//            if (Objects.equals(usuario.getId(), id)) {
//                usuarioPunto =
//            } else {
//                usuarioPunto++;
//            }
//        }
//        for (PuntoInteres punto : puntos) {
//            if (Objects.equals(punto.getHorizontal(), x) && Objects.equals(punto.getVertical(), y)) {
//                puntoInteres = punto;
//            }
//        }
//        if (usuarioPunto != -1 && puntoInteres != -1) {
//            usuarioPunto.addPunto(usuarioPunto);
//            puntoInteres.addUsuarioPunto(usuarioPunto);
//            logger.info("Usuario con el id: " + id + " registrado en el punto: [" + x + ", " + y + "]");
//        } else {
//            logger.error("El usuario con el id: " + id + " y/o el punto con las coordenadas:" +
//                    " [" + x + ", " + y + "], no existe");
//        }


    }

    //Consultar los puntos de interés por los que un usuario ha pasado, en el
    //orden en que se ha registrado
    @Override
    public void consultarPuntos(Usuario usuario) {
        logger.info("Puntos por los que ha pasado el usuario :" + usuario.getPuntos());
    }

    //Listar los usuarios que han pasado por un punto de interés identificado
    //por sus coordenadas
    @Override
    public void listarUsuariosPunto(int x, int y) {
        for (PuntoInteres punto : puntos) {
            if (Objects.equals(punto.getHorizontal(), x) && Objects.equals(punto.getVertical(), y)) {
                logger.info("Usuarios que han pasado por ese punto: " + punto.getUsuarios());
                return;
            }
        }
        logger.error("El punto de interés con las coordenads: [" + x + ", " + y + "], no existe.");
    }

    //Consultar los puntos de interés del mapa que sean de un tipo (ElementType) determinado
    @Override
    public void listarPuntosElementType(ElementType elementType) {
        StringBuilder puntosElementType = new StringBuilder();
        for (PuntoInteres punto : puntos) {
            if (punto.getElementType() == elementType) {
                puntosElementType.append(punto.getCoordenadas());
            }
        }
        logger.info("Puntos de interés del tipo " + puntosElementType + ":");
    }

    @Override
    public void clear() {
        this.usuarios.clear();
        this.puntos.clear();
    }

    @Override
    public int sizeUsuarios() {
        int ret = this.usuarios.size();
        logger.info("size " + ret);

        return ret;
    }

    @Override
    public int sizePuntos() {
        int ret = this.puntos.size();
        logger.info("size " + ret);

        return ret;
    }


}
