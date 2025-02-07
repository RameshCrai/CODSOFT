package Game;

import java.util.Random;

public class GenerateNumberImpl implements GenerateNumber{
	private int MIN;
	private int MAX;
	private Random random;
	
	GenerateNumberImpl(int Min, int Max){
		this.MIN = Min;
		this.MAX = Max;
		this.random = new Random();
	}
	

	@Override
	public int generateRandom() {
		
		return this.random.nextInt((MAX-MIN)+1)+MIN;
	}

}
