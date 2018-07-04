package controllerTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.Matchers.*;
import org.mockito.*;
import Controllers.ArtikelController;
import data.ArtikelDaoImplement;
import domein.Artikel;
import junit.framework.Assert;



public class ArtikelControllerTest {
	
	@Mock private ArtikelDaoImplement  artikelDaoImplement;
	@Rule public MockitoRule rule = MockitoJUnit.rule();		
	
	Artikel nieuweArtikel1=new Artikel ("Oude kaas", new BigDecimal ("7.20"), 100);
	Artikel nieuweArtikel2=new Artikel ("pinda kaas", new BigDecimal ("10.00"), 30);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testArtikelController() {
	    ArtikelController artikelController = new ArtikelController(artikelDaoImplement);
	    artikelController.voegArtikelToe2(nieuweArtikel1);;
	//    Mockito.verify(artikelDaoImplement).createArtikel(nieuweArtikel1);  
	    Mockito.verify(artikelDaoImplement).createArtikel(nieuweArtikel1); 

	    
	//    Mockito.verify(artikelDaoImplement).createArtikel(refEq(nieuweArtikel1));
   
	}  
		 
	@Test
	public void testVoegArtikelToe() {
    	ArtikelController artikelController = new ArtikelController(artikelDaoImplement);
    //	ArtikelController artikelController = new ArtikelController();
    	nieuweArtikel1.setId(10);
    	int id1 = nieuweArtikel1.getId();	
	//	Mockito.when(artikelDaoImplement.createArtikel(new Artikel(nieuweArtikel1.getNaam(), nieuweArtikel1.getPrijs(), nieuweArtikel1.getVoorraad()))).thenReturn(id1);
	//	Mockito.when(artikelDaoImplement.createArtikel(new Artikel("Oude kaas", new BigDecimal ("7.20"), 100))).thenReturn(10);	
    	Mockito.when(artikelDaoImplement.createArtikel(ArgumentMatchers.any())).thenReturn(id1);
    	
	//	boolean actual =artikelController.voegArtikelToe(nieuweArtikel1.getNaam(), nieuweArtikel1.getPrijs(), nieuweArtikel1.getVoorraad());
		boolean actual =artikelController.voegArtikelToe2(nieuweArtikel1);
		System.out.println("testsing "+ actual);
		Assert.assertTrue(actual);		
		
/*		Artikel actual = artikelController.voegArtikelToe(nieuweArtikel1.getNaam(), nieuweArtikel1.getPrijs(), nieuweArtikel1.getVoorraad()); 
 		Assert.assertEquals(nieuweArtikel1.getNaam(), actual.getNaam());
		Assert.assertEquals(nieuweArtikel1.getPrijs(), actual.getPrijs());
		Assert.assertEquals(nieuweArtikel1.getVoorraad(), actual.getVoorraad());  
*/		
		Mockito.verify(artikelDaoImplement).createArtikel(nieuweArtikel1);
 		
		
	}     
/*
	@Test
	public void testPasNaamAan() {
		ArtikelController artikelController = new ArtikelController();
		nieuweArtikel1.setNaam("nieuwe kaasnaam");
		int id1 = nieuweArtikel1.getId();
		Mockito.when(artikelDaoImplement.updateArtikel(nieuweArtikel1)).thenReturn(true);		
		boolean actual = artikelController.pasNaamAan(id1, "nieuwe kaasnaam"); 		
		Assert.assertTrue(actual);
		Mockito.verify(artikelDaoImplement).updateArtikel(nieuweArtikel1);

	}

	@Test
	public void testPasPrijsAan() {
		ArtikelController artikelController = new ArtikelController();
		nieuweArtikel1.setPrijs(new BigDecimal ("10.60"));
		int id1 = nieuweArtikel1.getId();
		Mockito.when(artikelDaoImplement.updateArtikel(nieuweArtikel1)).thenReturn(true);		
		boolean actual = artikelController.pasPrijsAan(id1, (new BigDecimal ("10.60"))); 		
		Assert.assertTrue(actual);
		Mockito.verify(artikelDaoImplement).updateArtikel(nieuweArtikel1);
	}

	@Test
	public void testPasVoorraadAan() {
		ArtikelController artikelController = new ArtikelController();
		nieuweArtikel1.setVoorraad(200);
		int id1 = nieuweArtikel1.getId();
		Mockito.when(artikelDaoImplement.updateArtikel(nieuweArtikel1)).thenReturn(true);		
		boolean actual = artikelController.pasVoorraadAan(id1, 200); 		
		Assert.assertTrue(actual);
		Mockito.verify(artikelDaoImplement).updateArtikel(nieuweArtikel1);

	}       

	@Test
	public void testZoekArtikel() {
		ArtikelController artikelController = new ArtikelController();
		int id1 = nieuweArtikel1.getId();
		Mockito.when(artikelDaoImplement.getArtikel(id1)).thenReturn(nieuweArtikel1);		
		
		String actual = artikelController.zoekArtikel(id1); 		
		Assert.assertEquals(nieuweArtikel1.getNaam(), actual.gegetNaam());
		Assert.assertEquals(nieuweArtikel1.getPrijs(), actual.getPrijs());
		Assert.assertEquals(nieuweArtikel1.getVoorraad(), actual.getVoorraad());  
		
		Mockito.verify(artikelDaoImplement).getArtikel(id1);
	}

	@Test
	public void testDeleteArtikel() {
		ArtikelController artikelController = new ArtikelController();
		int id1 = nieuweArtikel1.getId();
		Mockito.when(artikelDaoImplement.deleteArtikel(nieuweArtikel1)).thenReturn(true);		
		boolean actual = artikelController.deleteArtikel(id1);		
		Assert.assertTrue(actual);
		Mockito.verify(artikelDaoImplement).deleteArtikel(nieuweArtikel1);
	}

	@Test
	public void testGetAlleArtikelen() {
		ArtikelController artikelController = new ArtikelController();
		ArrayList<Artikel> returnedArtikelen = new ArrayList<>();
		Mockito.when(artikelDaoImplement.getAlleArtikelen()).thenReturn(returnedArtikelen);		
		
		String[] actual = artikelController.getAlleArtikelen();	
		
		for (int i=0; i<actual.length;i++) {	
		Assert.assertEquals(nieuweArtikel1.getNaam(), actual.get(i).getNaam() );
		Assert.assertEquals(nieuweArtikel1.getPrijs(), actual.get(i).getPrijs());
		Assert.assertEquals(nieuweArtikel1.getVoorraad(), actual.get(i).getVoorraad());  
		
		Mockito.verify(artikelDaoImplement).getAlleArtikelen();

	  }
	}     */
}  
