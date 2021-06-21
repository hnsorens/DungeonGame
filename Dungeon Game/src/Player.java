import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	int x, y;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		if (Game.listener.up) {
			this.y -= 1;
		}
		if (Game.listener.down) {
			this.y += 1;
		}
		if (Game.listener.left) {
			this.x -= 1;
		}
		if (Game.listener.right) {
			this.x += 1;
		}
		this.x = ((int)this.x*10)/10;
		this.y = ((int)this.y*10)/10;
	}
	
	public void move(int moveX, int moveY) {
		this.x += moveX;
		this.y += moveY;
		
	}
	
	public void Draw(Graphics g) {
		int tileRenderSize = MathFunctions.Projection(this.x+10, this.y+10, 120)[0] - MathFunctions.Projection(this.x, this.y, 120)[0];
		g.drawRect(MathFunctions.Projection(-5,-5, 120)[0],MathFunctions.Projection(-5,-5, 120)[1],(int)tileRenderSize,(int)tileRenderSize);
	}

}
