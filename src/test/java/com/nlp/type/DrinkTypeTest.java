package com.nlp.type;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith( Parameterized.class )
public class DrinkTypeTest
{
    private String text;

    public DrinkTypeTest( String words  )
        throws ParseException
    {
        this.text = words;
    }

    @Test
    public void test() throws ParseException
    {
        DrinkType drinkType = new DrinkType();
        String value = null;

        assertTrue( drinkType.matches( text ) );
        value = drinkType.getDrink();
        assertTrue( value != null );
    }

    @Parameters
    public static Collection<Object[]> generateData()
    {
        //@formatter:off
    	return Arrays.asList(new Object[][] {
    		{ "Ich trinke Cola mit Eiswürfeln" },
    		{ "Ich möchte Wasser" },
    		{ "Ich trinke Bier" }
    	});
       //@formatter:on
    }
}
