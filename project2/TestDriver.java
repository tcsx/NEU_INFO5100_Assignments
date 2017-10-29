import org.junit.Test;
import static org.junit.Assert.*;


public class TestDriver {
	
//    @Test
//    public void testCard(){
//        Card c = new Card(14,1);
//        c.display();
//    }
//
//    @Test
//    public void testGroupOfCards(){
//        Card c1 = new Card(10,1);
//        Card c2 = new Card(11,2);
//        Card c3 = new Card(12,3);
//        Card c4 = new Card(13,2);
//        Card c5 = new Card(2,0);
//        GroupOfCards cards = new GroupOfCards(52);
//        cards.addCard(c1);
//        cards.addCard(c2);
//        cards.addCard(c3);
//        cards.addCard(c4);
//        cards.addCard(c5);
//        cards.removeCard(1);
//        cards.display();
//    }
    
//    @Test
//    public void testDeck(){
//    	Deck deck = new Deck();
//    	deck.display();
//    	System.out.println("-----shuffle-----");
//    	assertEquals(52, deck.getCurrentSize());
//    	deck.shuffle();
//    	deck.display();
//    	for (int i = 0; i < 10; i++) {
//    		deck.dealCard();
//		}
//    	System.out.println("----deal----");
//    	assertEquals(42, deck.getCurrentSize());
//    	deck.display();
//    }
    
//    @Test
//    public void testTrick(){
//    	Trick trick = new Trick(5);
//    	Card card1 = new Card(7, 2);
//    	Card card2 = new Card(8,3);
//    	Card card3 = new Card(8,2);
//    	Card card4 = new Card(12,2);
//    	Card card5 = new Card(11,2);
//    	Card[] cards = {card1,card2,card3,card4,card5};
//    	for (int i = 0; i < cards.length; i++) {
//			trick.addCard(cards[i]);
//			trick.update(i, cards[i]);
//		}
//    	assertEquals(card4, trick.getWinningCard());
//    	assertEquals(3, trick.getWinner());
//    }
    
//    @Test
//    public void testSort(){
//    	Hand hand = new Hand(4, 52);
//        for(int i = 0; i < 4; i++) {
//            for (int j = 2; j <= 14; j++) {
//				hand.addCard(new Card(j, i));
//			}
//        }
//        hand.sort();
//        hand.display();
//    }
	
	@Test
	public void testPlayAGame(){
		Game game = new Game(5);
		game.playAGame();
	}
}