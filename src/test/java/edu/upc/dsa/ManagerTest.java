package edu.upc.dsa;

import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.Usuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ManagerTest {
    Manager manager;

    @Before
    public void setUp() {
        this.manager = ManagerImpl.getInstance();

        this.manager.addUsuario("123a","Pep", "Casals de la Cruz",
                "pepe@gmail.com","01/01/1999");
        this.manager.addUsuario("996r","Jordi", "Zara Pilota",
                "jordi@gmail.com","08/09/1999");
        this.manager.addUsuario("381l","Mar", "Cel Terra",
                "mar@gmail.com","14/11/2009");

        this.manager.addPuntoInteres(ElementType.COIN, 3,4);
        this.manager.addPuntoInteres(ElementType.DOOR, 0,5);
        this.manager.addPuntoInteres(ElementType.GRASS, 1,2);

    }

    @After
    public void tearDown() {
        // És un Singleton
        this.manager.clear();
    }

    @Test
    public void addUsuariosTest() {
        Assert.assertEquals(3, manager.sizeUsuarios());
        this.manager.addUsuario("562r","Maria", "Alambra Nse",
                "maria@gmail.com","09/09/1999");
        Assert.assertEquals(4, manager.sizeUsuarios());
    }

    @Test
    public void addPuntosTest() {
        Assert.assertEquals(3, manager.sizePuntos());
        this.manager.addPuntoInteres(ElementType.TREE, 5,5);
        Assert.assertEquals(4, manager.sizePuntos());
    }

    @Test
    public void listarUsuariosTest(){
        List<Usuario> usuarios = manager.listarUsuarios();
        Assert.assertEquals("Casals de la Cruz", usuarios.get(0).getCognoms());
        Assert.assertEquals("Cel Terra", usuarios.get(1).getCognoms());
        Assert.assertEquals("Zara Pilota", usuarios.get(2).getCognoms());
    }
    @Test
    public void consultarUsuarioTest(){
        Usuario u = manager.consultarUsuario("996r");
        Assert.assertEquals("996r", u.getId());
        //Si no existe deberia devolver null:
        Usuario u2 = manager.consultarUsuario("aaa");
        Assert.assertNull(u2);
    }

    @Test
    public void usuarioPuntoInteresTest(){
        int u = manager.usuarioPuntoInteres("996r", 0, 5);
        Assert.assertEquals(0, u);
        //Si no existe:
        int u2 = manager.usuarioPuntoInteres("aaa", 3, 3);
        Assert.assertEquals(-1, u2);
    }

    @Test
    public void consultarPuntosTest(){
        manager.usuarioPuntoInteres("996r", 0, 5);
        manager.usuarioPuntoInteres("996r", 1, 2);
        List<PuntoInteres> p = manager.consultarPuntos("996r");
        Assert.assertEquals(2, p.size());
        //En orden de registro:
        Assert.assertEquals("[0, 5]", p.get(0).getCoordenadas());
        Assert.assertEquals("[1, 2]", p.get(1).getCoordenadas());
    }

    @Test
    public void listarUsuariosPuntoTest(){
        manager.usuarioPuntoInteres("996r", 0, 5);
        manager.usuarioPuntoInteres("123a", 0, 5);
        Set<Usuario> usuarios = manager.listarUsuariosPunto(0,5);
        Assert.assertEquals(2, usuarios.size());
        //Si punto no existe:
        Assert.assertNull(manager.listarUsuariosPunto(3,3));

    }

    @Test
    public void listarPuntosElementTypeTest(){
        manager.addPuntoInteres(ElementType.DOOR, 1, 1);
        String p = manager.listarPuntosElementType(ElementType.DOOR);
        Assert.assertEquals("Puntos de interés del tipo DOOR: [0, 5] [1, 1] ", p);
    }

}
