
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.List;

public class Dungeon {
	
	ArrayList<DungeonSection> Sections;
	
	int tileD = 10;
	int w, h;
	
	int start = 0;
	
	DungeonSection current;
	
	int currentRoom = 0;
	
	List<DungeonSection> stack;
	
	public Dungeon(int w, int h) {
		this.currentRoom = 0;
		this.stack = new ArrayList<DungeonSection>();
		this.w = w;
		this.h = h;
		Sections = new ArrayList<DungeonSection>();
		for (int y = 0; y < w; y++) {
			for (int x = 0; x < h; x++) {
				Sections.add(new DungeonSection(x,y,w,h,tileD));
			}
		}
		
		boolean allChecked = false;
		
		current = Sections.get(0);
		while (!allChecked) {
			allChecked = true;
			for (int i = 0; i < Sections.size(); i++) {
				if (!Sections.get(i).visited) allChecked = false;
			}
			UpdateMazeCreation();
		}
		for (int i = 0; i < this.Sections.size(); i++) {
			Sections.get(i).CreateRoom();
		}
	}
	
	public void UpdateMazeCreation() {
		current.visited = true;
		DungeonSection next = current.checkNeigbors(Sections);
		if (next != null) {
			next.visited = true;
			stack.add(current);
			removeWalls(current,next);
			current = next;
		} else if (stack.size() > 0){
			this.current = this.stack.get(this.stack.size()-1);
			this.stack.remove(this.stack.size()-1);
		}
	}
	
	
	
	public void show(Graphics g) {
//		for (int i = 0; i < Sections.size(); i++) {
//			Sections.get(i).show(g);
//		}
		Sections.get(this.currentRoom).show(g);
	}
	
	public void removeWalls(DungeonSection a, DungeonSection b) {
		
		int x = a.x - b.x;
		if (x == 1) {
			a.walls[3] = false;
			b.walls[1] = false;
		} else if (x == -1) {
			a.walls[1] = false;
			b.walls[3] = false;
		}
		int y = a.y - b.y;
		if (y == 1) {
			a.walls[0] = false;
			b.walls[2] = false;
		} else if (y == -1) {
			a.walls[2] = false;
			b.walls[0] = false;
		}
	}
	
	public void updateRoomNav() {
		if (Game.player.x > 70) {
			this.currentRoom += 1;
			Game.player.x = -70;
		} else if (Game.player.x < -70) {
			this.currentRoom -= 1;
			Game.player.x = 70;
		} else if (Game.player.y > 70) {
			this.currentRoom += this.w;
			Game.player.y = -70;
		} else if (Game.player.y < -70) {
			this.currentRoom -= this.w;
			Game.player.y = 70;
		}
	}
	
	public void Update() {
		updateRoomNav();
		Sections.get(currentRoom).updateRoom(Game.player);
		
	}

}
