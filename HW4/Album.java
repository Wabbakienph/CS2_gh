/**
 * Album represents an audio production by an artist
 */
public class Album {
	private String artist;
	private String album;

	public Album(String nArtist, String nAlbum){
		this.artist = nArtist;
		this.album = nAlbum;
	}

	public Album(String albumString){
		if (albumString.indexOf(" - ") == -1) {
			throw new IllegalArgumentException("Invalid argument");
		}
		String[] albumArr = albumString.split(" - ");
		this.artist = albumArr[0];
		this.album = albumArr[1];
	}

	@Override
	public String toString(){
		return this.artist + " - " + this.album;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) {
			return true; 
		} else if (o instanceof Album) {
			Album otherAlbum = (Album) o;
			return otherAlbum.artist.equals(this.artist) && otherAlbum.album.equals(this.album);
		} else {
			return false;
		}
	}


	public String getArtist() {
		return this.artist;
	}

	public String getAlbum() {
		return this.album;
	}
	
}
