package com.nlp.type;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nlviews.api.MetaData;
import com.nlviews.api.NaturalType;
import com.nlviews.api.annotation.NaturalTypeComponent;
import com.nlviews.api.annotation.NlpTypeMatcher;

@NaturalTypeComponent( "Getränke" )
public class DrinkType
    implements NaturalType
{
    private static Pattern pattern = null;
    private MetaData metaData = null;
    private String text = null;
    
    private String drink = null;

    static
    {
    	StringBuffer expression = new StringBuffer();
    	expression.append("(?i)").append("\\s*");
    	
    	expression.append("(?<properties>");
    	expression.append("\\b");
    	expression.append("still[emnrs]{1,2}").append("|");
    	expression.append("frisch[emnrs]{1,2}").append("|");
    	expression.append("kalt[emnrs]{1,2}").append("|");
    	expression.append("eiskalt[emnrs]{1,2}");
    	expression.append("\\b");
    	expression.append(")?");

    	expression.append("\\s*");
    	
    	expression.append("(?<drink>");
    	expression.append("\\b");
    	expression.append("cola").append("|");
    	expression.append("orangensaft").append("|");
    	expression.append("wasser").append("|");
    	expression.append("mineralwasser").append("|");
    	expression.append("bier");
    	expression.append("\\b");
    	expression.append(")");
    	
    	expression.append("\\s*");
    	
    	expression.append("((?<with>mit|ohne)\\s+eiswürfel\\s*)?\\s*");

        pattern = Pattern.compile( expression.toString() );
    }

    @NlpTypeMatcher
    public String getDrink( )
    {
        Matcher matcher = pattern.matcher( text );
        if ( matcher.find() )
        {
            if ( getGroup(matcher, "drink" ) != null )
            {
                String  value = matcher.group("drink");

                metaData = new MetaData();
                metaData.setPhrase(matcher.group());
                metaData.setStartPos( matcher.start() );
                metaData.setEndPos( matcher.end() );

                return value;
            }
        }
        return null;
    }

    @Override
    public boolean matches( String text )
    {
        if ( text == null )
        {
            return false;
        }
        this.text = text;

        Pattern pattern = Pattern.compile( "(?i)\\s*(cola|wasser|bier)\\s*" );
        return pattern.matcher(text).find();
    }

	@Override
	public MetaData getMetaData() {
		return metaData;
	}
	
	private String getGroup(Matcher matcher, String name)
	{
		try
		{
			return matcher.group( name );
		}
		catch(Exception e)
		{
		}

		return null;
	}
    
}
