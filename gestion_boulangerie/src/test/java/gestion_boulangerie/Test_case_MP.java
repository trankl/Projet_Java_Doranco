package gestion_boulangerie;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import projet.server.gestion_boulangerie.model.MatierePremiere;
import projet.server.gestion_boulangerie.service.MPService;

class Test_case_MP {

	 private MPService mpService = new MPService();
	/*
	@Test
	void GetAllMP() throws Exception {
		
		List<MatierePremiere> listMP = mpService.getAllMatierePremiere();
		
        String[] listMP_nom = new String[listMP.size()];

        int i = 0;
        for (MatierePremiere mp: listMP) {
                 listMP_nom[i] = " MP ID "+mp.getMp_id()+" - "+ mp.getMp_nom() ;
                i++;
            }
        
        System.out.println(" String listMP_nom : ");
        for (int j = 0; j< listMP_nom.length; j++) {
        System.out.println(listMP_nom[j]);	
			
		}
        
        assertEquals(listMP.size(), listMP_nom.length );
        System.out.println("Test -> OK");

	}*/
	

}
