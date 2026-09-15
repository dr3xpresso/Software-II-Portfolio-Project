import components.map.Map;
import components.map.Map1L;
import components.playlist.Playlist;
import components.playlist.Playlist1;
import components.playlist.PlaylistKernel.Song;

public class MusicLibrary {

    private Map<String, Playlist> playlists;

    public MusicLibrary(Playlist p) {
        assert p != null : "Violation of: playlist is not null";
        this.playlists = new Map1L();
    }

    public void addPlaylist(String name, Playlist p) {
        this.playlists.add(name, p);
    }

    public void removePlaylist(String name, Playlist p) {
        this.playlists.remove(name);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Playlist december25 = new Playlist1();

        final Song song1 = new Song("Take a Trip", "TV Girl & Geroge Clanton");
        final Song song2 = new Song("Diary", "Placid State");

        Song[] songsToAdd = { song1, song2 };

        for (int i = 0; i < songsToAdd.length; ++i) {
            december25.addSong(songsToAdd[i]);
        }

        MusicLibrary library = new MusicLibrary(december25);

        library.removePlaylist("December 25", december25);

    }
}
