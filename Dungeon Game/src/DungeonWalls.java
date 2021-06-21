import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class DungeonWalls {
	
	boolean[] walls;
	List<Tile> wallTiles;
	int[] wallLengths;

	public void init(boolean[] walls, int z, int roomW, int roomH) {
		this.wallLengths = new int[16];
		for (int i = 0; i < 8; i++) {
			wallLengths[2*i] = (MathFunctions.Projection(70,70,100.25+(2.5*i))[0]-MathFunctions.Projection(-60,-60,100.25+(2.5*i))[0]);
			wallLengths[(2*i)+1] = (MathFunctions.Projection(70,70,102.25+(2.5*i))[0]-MathFunctions.Projection(-60,-60,102.25+(2.5*i))[0]);

		}
		System.out.println("k");
		this.wallTiles = new ArrayList<Tile>();
		this.walls = walls;
		if (!this.walls[0]) {
			for (int i = 0; i < (int)((roomW/2)-1); i++) {
				this.wallTiles.add(new Tile(i-(roomW/2),0-(roomH/2),z,1));
			}
			for (int i = 0; i < (int)((roomW/2)-1); i++) {
				this.wallTiles.add(new Tile(roomW-i-1-(roomW/2),0-(roomH/2),z,1));
			}
		} else {
			for (int i = 0; i < roomW; i++) {
				this.wallTiles.add(new Tile(i-(roomW/2),0-(roomH/2),z,1));
			}
		}
		//right
		if (!this.walls[1]) {
			for (int i = 0; i < (int)((roomH/2)-2); i++) {
				this.wallTiles.add( new Tile(roomW-1-(roomW/2),i+1-(roomH/2),z,1));
			}
			for (int i = 0; i < (int)((roomH/2)-2); i++) {
				this.wallTiles.add(new Tile(roomW-1-(roomW/2),roomH-i-2-(roomH/2),z,1));
			}
		} else {
			for (int i = 0; i < roomW-2; i++) {
				this.wallTiles.add(new Tile(roomW-1-(roomW/2),i+1-(roomH/2),z,1));
			}
		}
		//bottom
		if (!this.walls[2]) {
			for (int i = 0; i < (int)((roomW/2)-1); i++) {
				this.wallTiles.add(new Tile(i-(roomW/2),roomH-1-(roomH/2),z,1));
			}
			for (int i = 0; i < (int)((roomW/2)-1); i++) {
				this.wallTiles.add(new Tile(roomW-i-1-(roomW/2),roomH-1-(roomH/2),z,1));
			}
		} else {
			for (int i = 0; i < roomW; i++) {
				this.wallTiles.add(new Tile(i-(roomW/2),roomH-1-(roomH/2),z,1));
			}
		}
		//left
		if (!this.walls[3]) {
			for (int i = 0; i < (int)((roomH/2)-2); i++) {
				this.wallTiles.add(new Tile(0-(roomW/2),i+1-(roomH/2),z,1));
			}
			for (int i = 0; i < (int)((roomH/2)-2); i++) {
				this.wallTiles.add(new Tile(0-(roomW/2),roomW-i-2-(roomH/2),z,1));
			}
		} else {
			for (int i = 0; i < roomW-2; i++) {
				this.wallTiles.add(new Tile(0-(roomW/2),i+1-(roomH/2),z,1));
			}
		}
	}
	
	public void Draw(Graphics g) {
		
		g.setColor(new Color(150,150,150));
		g.fillPolygon(new int[] {MathFunctions.Projection(-60-Game.player.x-5,-60-Game.player.y-5, 100)[0], MathFunctions.Projection(70-Game.player.x-5,-60-Game.player.y-5, 100)[0], MathFunctions.Projection(70-Game.player.x-5,-60-Game.player.y-5, 120)[0], MathFunctions.Projection(-60-Game.player.x-5,-60-Game.player.y-5, 120)[0]},
					  new int[] {MathFunctions.Projection(-60-Game.player.x-5,-60-Game.player.y-5, 100)[1], MathFunctions.Projection(70-Game.player.x-5,-60-Game.player.y-5, 100)[1], MathFunctions.Projection(70-Game.player.x-5,-60-Game.player.y-5, 120)[1], MathFunctions.Projection(-60-Game.player.x-5,-60-Game.player.y-5, 120)[1]}, 4);
		
		if (!this.walls[0]) {
			DrawWall(g,-60,-60,50,0);
			DrawWall(g,20,-60,50,0);
			if (Game.player.x > -10) {
				DrawWall(g,-11,-70,10,1);
			}
			if (Game.player.x < 20) {
				DrawWall(g,21,-70,10,1);
			}
		} else {
			DrawWall(g,-60,-60,130,0);
		}
		if (!this.walls[1]) {
			DrawWall(g,70,-60,50,1);
			DrawWall(g,70,20,50,1);
			if (Game.player.y > -11) {
				DrawWall(g,70,-10,10,0);
			}
			if (Game.player.y < 21) {
				DrawWall(g,70,20,10,0);
			}
		} else {
			DrawWall(g,70,-60,130,1);
		}
		if (!this.walls[2]) {
			DrawWall(g,-60,70,50,0);
			DrawWall(g,20,70,50,0);
			if (Game.player.x > -11) {
				DrawWall(g,-10,70,10,1);
			}
			if (Game.player.x < 21) {
				DrawWall(g,20,70,10,1);
			}
		} else {
			DrawWall(g,-60,70,130,0);
		}
		if (!this.walls[3]) {
			DrawWall(g,-60,-60,50,1);
			DrawWall(g,-60,20,50,1);
			if (Game.player.y > -11) {
				DrawWall(g,-70,-10,10,0);
			}
			if (Game.player.y < 21) {
				DrawWall(g,-70,20,10,0);
			}
		} else {
			DrawWall(g,-60,-60,130,1);
		}

		
		for (int i = 0; i < wallTiles.size(); i++) {
			this.wallTiles.get(i).Show(g);
		}
	}
	
	private void DrawWall(Graphics g, int posX, int posY, int w, int side) {
		int brickTop1;
		int brickBottom1;
		int brickTop2;
		int brickBottom2;
		if (side == 0) {
			for (double y = 0; y < 20; y+=5) {
				
	
				brickTop1 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y)[1];
				brickBottom1 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y)[1];
				brickTop2 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1];
				brickBottom2 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[1];
				for (double x = 0; x < w; x+=10) {
					g.setColor(new Color(50,50,50));
					g.fillPolygon(new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y)[0]+MathFunctions.ScaleNumberLine(0.25+x, 130, this.wallLengths[(int) ((y/5)*4)])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y)[0]+MathFunctions.ScaleNumberLine(9.75+x, 130, this.wallLengths[(int) ((y/5)*4)])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y)[0]+MathFunctions.ScaleNumberLine(9.75+x, 130, this.wallLengths[(int) ((y/5)*4)+1])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y)[0]+MathFunctions.ScaleNumberLine(0.25+x, 130, this.wallLengths[(int) ((y/5)*4)+1]))},
							 new int[] {brickTop1,brickTop1,brickBottom1,brickBottom1},4);
				}
				for (double x = 5; x < w-10; x+=10) {
					g.setColor(new Color(50,50,50));
					g.fillPolygon(new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(0.25+x, 130, this.wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(9.75+x, 130, this.wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(9.75+x, 130, this.wallLengths[(int) ((y/5)*4)+3])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(0.25+x, 130, this.wallLengths[(int) ((y/5)*4)+3]))},
							      new int[] {brickTop2,brickTop2,brickBottom2,brickBottom2}, 4);
				}
				g.fillPolygon(new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(0.25, 130, this.wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(4.75, 130, this.wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(4.75, 130, this.wallLengths[(int) ((y/5)*4)+3])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(0.25, 130, this.wallLengths[(int) ((y/5)*4)+3]))},
					      new int[] {brickTop2,brickTop2,brickBottom2,brickBottom2}, 4);
				g.fillPolygon(new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(w-4.75, 130, this.wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(w-0.25, 130, this.wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(w-0.25, 130, this.wallLengths[(int) ((y/5)*4)+3])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[0]+MathFunctions.ScaleNumberLine(w-4.75, 130, this.wallLengths[(int) ((y/5)*4)+3]))},
					      new int[] {brickTop2,brickTop2,brickBottom2,brickBottom2}, 4);
				
			}
		}
		if (side == 1) {
			for (double y = 0; y < 20; y+=5) {
				
				
				brickTop1 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y)[0];
				brickBottom1 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y)[0];
				brickTop2 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[0];
				brickBottom2 = MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[0];
				for (double x = 0; x < w; x+=10) {
					g.setColor(new Color(50,50,50));
					g.fillPolygon(new int[] {brickTop1,brickTop1,brickBottom1,brickBottom1},
							      new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y)[1]+MathFunctions.ScaleNumberLine(0.25+x, 130, this.wallLengths[(int) ((y/5)*4)])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y)[1]+MathFunctions.ScaleNumberLine(9.75+x, 130, this.wallLengths[(int) ((y/5)*4)])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y)[1]+MathFunctions.ScaleNumberLine(9.75+x, 130, this.wallLengths[(int) ((y/5)*4)+1])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y)[1]+MathFunctions.ScaleNumberLine(0.25+x, 130, this.wallLengths[(int) ((y/5)*4)+1]))},
							      4);
				}
				for (double x = 5; x < w-10; x+=10) {
					g.setColor(new Color(50,50,50));
					g.fillPolygon(new int[] {brickTop2,brickTop2,brickBottom2,brickBottom2},
							      new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(0.25+x, 130, this.wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(9.75+x, 130, this.wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(9.75+x, 130, this.wallLengths[(int) ((y/5)*4)+3])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(0.25+x, 130, this.wallLengths[(int) ((y/5)*4)+3]))},
							      4);
				}
				g.fillPolygon(new int[] {brickTop2,brickTop2,brickBottom2,brickBottom2},
							  new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(0.25, 130, this.wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(4.75, 130, this.wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(4.75, 130, this.wallLengths[(int) ((y/5)*4)+3])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(0.25, 130, this.wallLengths[(int) ((y/5)*4)+3]))},
					          4);
				g.fillPolygon(new int[] {brickTop2,brickTop2,brickBottom2,brickBottom2},
							  new int[] {(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(w-4.75, 130, this.wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 100.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(w-0.25, 130, this.wallLengths[(int) ((y/5)*4)+2])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5,102.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(w-0.25, 130, this.wallLengths[(int) ((y/5)*4)+3])),(int)(MathFunctions.Projection(posX-Game.player.x-5,posY-Game.player.y-5, 102.25+y+2.5)[1]+MathFunctions.ScaleNumberLine(w-4.75, 130, this.wallLengths[(int) ((y/5)*4)+3]))},
					          4);
				
			}
		}
		
		
	}
	
	
	public void updateCollisions(Player player, int tileD, int roomW, int roomH) {
		if (!this.walls[0]) {
			if (player.y < -60) {
				if (player.x == -11) {
					player.x = -10;
				} else if (player.x == 11) {
					player.x = 10;
				} else if (player.y == -61 && (player.x < -10 || player.x > 10)) {
					player.y = -60;
				}
			}
		} else if (player.y == -61) {
			player.y = -60;
		}
		
		if (!this.walls[1]) {
			if (player.x > 60) {
				if (player.y == -11) {
					player.y = -10;
				} else if (player.y == 11) {
					player.y = 10;
				} else if (player.x == 61 && (player.y < -10 || player.y > 10)) {
					player.x = 60;
				}
			}
		} else if (player.x == 61) {
			player.x = 60;
		}
		
		if (!this.walls[2]) {
			if (player.y > 60) {
				if (player.x == -11) {
					player.x = -10;
				} else if (player.x == 11) {
					player.x = 10;
				} else if (player.y == 61 && (player.x < -10 || player.x > 10)) {
					player.y = 60;
				}
			}
		} else if (player.y == 61) {
			player.y = 60;
		}
		
		if (!this.walls[3]) {
			if (player.x < -60) {
				if (player.y == -11) {
					player.y = -10;
				} else if (player.y == 11) {
					player.y = 10;
				} else if (player.x == -61 && (player.y < -10 || player.y > 10)) {
					player.x = -60;
				}
			}
		} else if (player.x == -61) {
			player.x = -60;
		}
		

	}

}
