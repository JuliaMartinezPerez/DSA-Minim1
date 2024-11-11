package edu.upc.dsa;

import edu.upc.dsa.models.ElementType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertEquals(3, manager.sizeUsuarios());
        //Assert.assertTrue(manager.listarUsuarios());
    }
}
