import java.util.ArrayList;

public class StackAgenda extends Agenda{
	private ArrayList<MazeGridLocation> stagenda;

	public StackAgenda() {
		this.stagenda = new ArrayList<MazeGridLocation>();
	}

	public void addLocation(MazeGridLocation loc) {
		stagenda.add(loc);
	}

	/*
	 * Removes the last location from the agenda based on stack's Last In First Out mechanism.
	 */
	public MazeGridLocation removeLocation() {
		return stagenda.remove(stagenda.size()-1); // stack = LIFO
	}

	/*
	 * Checks if the agenda contains a location.
	 */
	public boolean containsLocation(MazeGridLocation loc) {
		return stagenda.contains(loc);
	}
	
	/*
	 * Removes all existing elements inside the agenda.
	 */
	public void clear() {
		stagenda.clear();
	}

	public String toString() {
		return stagenda.toString();
	}

	/*
	 * Checks if the agenda has no elements.
	 */
	public boolean isEmpty() {
		return stagenda.isEmpty();
	}

}