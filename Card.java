package blackjack;
import java.util.HashMap;
import java.util.Map;

public class Card {
	
	public int suit;
    public int value;
    public boolean hidden;
    public Map <Integer,String>suit_symbol = new HashMap<Integer, String>();
    public Map <Integer, String>value_number = new HashMap<Integer,String>();

    //symbols
    {
        suit_symbol.put(0,"\u2660"); //spade
        suit_symbol.put(1,"\u2663"); // club
        suit_symbol.put(2,"\u2666"); // diamond 
        suit_symbol.put(3,"\u2665"); // heart
    }
    //values
    {
        value_number.put(1,"A");
        value_number.put(2,"2");
        value_number.put(3,"3");
        value_number.put(4,"4");
        value_number.put(5,"5");
        value_number.put(6,"6");
        value_number.put(7,"7");
        value_number.put(8,"8");
        value_number.put(9,"9");
        value_number.put(10,"T");
        value_number.put(11,"J");
        value_number.put(12,"Q");
        value_number.put(13,"K");
    }
    
    
    public Card(int suit, int value){
        this.suit = suit;
        this.value = value;
        this.hidden = false;
    }   
    
	public String toString() {
        if (this.hidden) {
            return "hidden";
        }
        else {
            return this.suit_symbol.get(this.suit) + " "+  this.value_number.get(this.value);
        }
    } 
}
