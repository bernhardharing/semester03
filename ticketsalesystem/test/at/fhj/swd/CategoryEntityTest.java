package at.fhj.swd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by NUC on 13.10.2016.
 */
public class CategoryEntityTest  {

    CategoryEntity categoryEntity;
    String testString = "ultra";
    int testPrice = 30;

    @Before
    public void preContition(){
        categoryEntity = new CategoryEntity(testString,testPrice);
    }

    @Test
    public void getDescription() throws Exception {
        assertTrue(categoryEntity.getDescription().equals(testString));
    }


    @Test
    public void getPrice() throws Exception {
        assertTrue(categoryEntity.getPrice() == testPrice);
    }
}