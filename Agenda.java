abstract class Agenda{
	abstract public void addLocation(MazeGridLocation loc);

	abstract public MazeGridLocation removeLocation();

	abstract public boolean containsLocation(MazeGridLocation loc);

	abstract public void clear();

	abstract public String toString();

	abstract public boolean isEmpty();
}