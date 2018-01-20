import java.util.ArrayList;

public class Test 
{	

	public static void main(String[]args)
	{
		ArrayList <Integer> cardList = new ArrayList <Integer> ();
		int cardIndex = 0;


		cardList.add(10);
		cardList.add(11);
		cardList.add(12);
		cardList.add(12);
		cardList.add(2);
		cardList.add(7);
		cardList.add(4);
		cardList.add(8);

		int lowestCard = cardList.get(0);

		while(cardIndex < cardList.size())
		{
			if(cardList.get(cardIndex) < lowestCard)
			{
				lowestCard = cardList.get(cardIndex);
			}
			cardIndex++;
		}

		System.out.println(lowestCard);






	}
}
