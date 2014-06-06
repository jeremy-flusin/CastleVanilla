package com.jflusin.castlevanilla.backend.utils.map.astar;

import java.util.ArrayList;

import com.jflusin.castlevanilla.backend.utils.B2DVars;
import com.jflusin.castlevanilla.backend.utils.map.Frame;

/** 
 * PathFinder using the A* algorithm
 * 
 * Reference: http://forums.mediabox.fr/wiki/tutoriaux/flashplatform/jeux/pathfinder_algorithme_astar
 * 
 */
public class PathFinder {

	private ArrayList<Node> map;
	private ArrayList<Node> closed;
	private ArrayList<Node> open;
	private Node start;
	private Node end;
	
	public PathFinder(ArrayList<Node> map, Node start, Node end) {
		this.map = map;
		this.start = start;
		this.end = end;
		closed = new ArrayList<Node>();
		open = new ArrayList<Node>();
	}	
	
	public PathFinder(ArrayList<Frame> map, Frame start, Frame end) {
		this.map = new ArrayList<Node>();
		for (Frame frame : map) {
			Node node = new Node(frame.getX(), frame.getY(), frame.isWalkable());
			this.map.add(node);
		}
		this.start = new Node(start.getX(), start.getY(), start.isWalkable());
		this.end = new Node(end.getX(), end.getY(), end.isWalkable());
		closed = new ArrayList<Node>();
		open = new ArrayList<Node>();
	}
	
	public ArrayList<Node> findPath() {
		
		ArrayList<Node> path = new ArrayList<Node>();

		boolean found = false;
		int stepsFromStart = 0;
		Node current = null;
		
		// Ajout du node de départ à la liste ouverte
		open.add(start);
		
		while(!found && open.size() > 0){
			
			stepsFromStart++;
			
			// Récupération du node avec le plus petit F contenu dans la liste ouverte. On le nommera CURRENT.
			current = getCurrent();
			
			// Basculer CURRENT dans la liste fermée.
			open.remove(current);
			closed.add(current);

			ArrayList<Node> adjacentNodes = getAdjacentNodes(current);
			for (Node adj : adjacentNodes) {
				if(adj.isWalkable() && !closed.contains(adj)){
					if(!open.contains(adj)){
						open.add(adj);
						if(adj.getX() == end.getX() && adj.getY() == end.getY()){
							found = true;
							end.setParent(current);
						}
						adj.setParent(current);
						adj.setG(stepsFromStart);
						adj.setH(getDistanceFromGoal(adj));
					} else {
						if (stepsFromStart < adj.getG()){
							adj.setParent(current);
							adj.setG(stepsFromStart);
							adj.setH(getDistanceFromGoal(adj));
						}
					}
				}
			}
		}
		
		if(found){
			System.out.println("Chemin trouvé !");
			current = end;
			while(current.getX() != start.getX() || current.getY() != start.getY()){
				path.add(current);
				current = current.getParent();
			}
		}else{
			System.out.println("Pas de chemin trouvé !");
		}
		
		
		return path;
	}

	private int getDistanceFromGoal(Node adj) {
		int distX = (int)(Math.abs((end.getX() - adj.getX())/B2DVars.FRAMEWIDTH));
		int distY = (int)(Math.abs((end.getY() - adj.getY())/B2DVars.FRAMEWIDTH));
		return distX + distY;
	}

	private ArrayList<Node> getAdjacentNodes(Node current) {
		ArrayList<Node> adjacentNodes = new ArrayList<Node>();
		for(Node node : map){
			if(
				(Math.abs(node.getX() - current.getX()) 
				<= (B2DVars.FRAMEWIDTH) + B2DVars.PRECISION)
				&& 
				(Math.abs(node.getY() - current.getY()) 
				<= (B2DVars.FRAMEWIDTH) + B2DVars.PRECISION)
			){
				adjacentNodes.add(node);
			}
		}
		return adjacentNodes;
	}

	// Récupération du node avec le plus petit F contenu dans la liste ouverte. On le nommera CURRENT.
	private Node getCurrent() {
		Node candidate = open.get(0);
		for(Node node: open){
			if(node.getF() < candidate.getF()){
				candidate = node;
			}
		}
		return candidate;
	}
}
