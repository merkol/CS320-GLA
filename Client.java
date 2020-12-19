import java.util.ArrayList;

public class Client implements User{
	protected float balance;
	protected ArrayList<Game> library = new ArrayList<Game>();
	
	public Client(float balance, ArrayList<Game> library) {
		super();
		this.balance = balance;
		this.library = library;
	}
	
	public void buy_game(Game game) {
		if(this.balance >= game.price) {
			library.add(game);
			this.balance = this.balance - game.price;
		}
		else {
			System.out.println("ERROR");
		}
	}
	
	public void refund_game(Game game) {
		boolean contains = false;
		int gameIndex = -1;
		for(int i = 0;i<library.size();i++) {
			if(game.name.equals(library.get(i).name)) {
				contains = true;
				gameIndex = i;
			}
		}
		if(contains == true) {
			library.remove(gameIndex);
			this.balance = this.balance + game.price;
		}
	}
	
	
}
