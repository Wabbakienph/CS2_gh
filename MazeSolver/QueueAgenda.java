import java.util.ArrayList;

public class QueueAgenda extends Agenda{
	private ArrayList<MazeGridLocation> queugenda;

	public QueueAgenda() {
		this.queugenda = new ArrayList<MazeGridLocation>();
	}

	public void addLocation(MazeGridLocation loc) {
		queugenda.add(loc);
	}

	/*
	 * Removes the last location from the agenda based on stack's Last In First Out mechanism.
	 */
	public MazeGridLocation removeLocation() {
		return queugenda.remove(0); // queue = FIFO
	}

	/*
	 * Checks if the agenda contains a location.
	 */
	public boolean containsLocation(MazeGridLocation loc) {
		return queugenda.contains(loc);
	}

	public void clear() {
		queugenda.clear();
	}

	public String toString() {
		return queugenda.toString();
	}

	public boolean isEmpty() {
		return queugenda.isEmpty();
	}

}