/**
 *@author Brandon Slusser
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class QRCode {
	static final int DEFAULT_DIMENSION = 30;
	static final int DEFAULT_SEED = 160;
	static int dimension;
	static int seed;
	int[][] grid;
	
	QRCode(){
		dimension = DEFAULT_DIMENSION;
		seed = DEFAULT_SEED;
	}
	
	QRCode(int dim, int sed){

		dimension = dim;
		seed = sed;
	}
	
	int[] createPattern(int dim, int seed) {
		dim *= dim;
		int[] newArray = new int[dim];
		Random rand = new Random(seed);
		for(int i = 0; i<dim; i++) {
			boolean stuff = rand.nextBoolean();
			newArray[i] = stuff ? 1 : 0;
		}
		return newArray;
	}
	void setGrid(int dim, int[] pattern) {
		int count = 0;
		this.grid = new int[dim][dim];
		for(int i = 0; i<dim; i++) {
			for(int j = 0; j<dim; j++) {
				this.grid[i][j] = pattern[count];
				count++;
			}
		}
	}
	
	int[][] getGrid() {
		return this.grid;
	}
	
	void setFinder(int xPos, int yPos) {
		if(grid.length > 0) {

			for(int y = yPos; y < (yPos+15); y++) {
						
				for(int x = xPos; x < (xPos+15); x++) {
					//ROWS 1 2 , 14,15
					if (y == yPos || y == yPos + 1 || y == yPos+13 || y == yPos + 14) {
						grid[y][x] = 1;
					}
					
					//ROWS 3,4,12,13
					else if (y == yPos + 2 || y == yPos + 3 || y == yPos+11 || y == yPos + 12) {
						if(x> xPos + 1 && x < xPos + 13) {
							grid[y][x] = 0;
						}
						else {
							grid[y][x] = 1;
						}
					}
					//ROWS 5,6,10,11
					else if (y == yPos + 4 || y == yPos + 5 || y == yPos+9 || y == yPos + 10) {
						if(x> xPos + 1 && x < xPos + 13) {
							if(x> xPos + 3 && x < xPos + 11) {
								grid[y][x] = 2;
								
							}
							else {
								grid[y][x] = 0;
							}
							
						}
						else {
							grid[y][x] = 1;
						}
					}
					//ROWS 7,8,9
					else if (y == yPos + 6 || y == yPos + 7 || y == yPos+8) {
						if(x> xPos + 1 && x < xPos + 13) {
							if(x> xPos + 3 && x < xPos + 11) {
								if(x> xPos + 5 && x < xPos + 9) {
									grid[y][x] = 3;
								}
								else {
									grid[y][x] = 2;
								}
								
								
							}
							else {
								grid[y][x] = 0;
							}
							
						}
						else {
							grid[y][x] = 1;
						}
					}
					
					
				}
			}
		}
		else {
			return;
		}
	}
	
	void print() {
		if (grid.length > 0) {
			for(int i = 0; i<dimension; i++) {
				for(int j = 0; j<dimension; j++) {
					System.out.print(this.grid[i][j]);
				
				}
				System.out.println();
			}
		}
		else {
			System.out.println("Error: empty grid");
		}
	}
	
	void print(int[] pattern) {
		if(pattern.length > 0) {
			for(int i = 0; i<pattern.length; i++) {
				System.out.print(pattern[i]);
			}
		}
		else {
			System.out.println("Error: empty pattern");
		}
		
	}
	
	void print(int[][] matrix) {
		if (matrix.length > 0) {
			for(int i = 0; i<matrix.length; i++) {
				for(int j = 0; j<matrix[i].length; j++) {
					System.out.print(matrix[j][i]);
				}
			}
		}
		else {
			System.out.println("Error: empty matrix");
		}
	}
	
	public static void main(String[] args)
    {
		
		if (args.length == 2) {
			
			int dimension = Integer.parseInt(args[0]);
			int seed = Integer.parseInt(args[1]);
			QRCode qrcode = new QRCode(dimension, seed);
			int[] pattern = qrcode.createPattern(dimension, seed);
			qrcode.setGrid(dimension, pattern);
			qrcode.setFinder(0,0);
			qrcode.setFinder(dimension-15, 0);
			qrcode.setFinder(0, dimension-15);
			qrcode.print();
		}
		else {
			QRCode qrcode = new QRCode(DEFAULT_DIMENSION, DEFAULT_SEED);
			int[] pattern = qrcode.createPattern(DEFAULT_DIMENSION, DEFAULT_SEED);
			qrcode.setGrid(DEFAULT_DIMENSION, pattern);
			qrcode.setFinder(0,0);
			qrcode.setFinder(DEFAULT_DIMENSION-15, 0);
			qrcode.setFinder(0, DEFAULT_DIMENSION-15);
			qrcode.print();
			
		}
		Map<String, String> map = new HashMap<>();

		map.put("key","value");

		map.put("newKey","newValue");

		map.put("key","updatedValue");

		String data = map.get("key");

		System.out.println(data);
        
        
    }
	

}
