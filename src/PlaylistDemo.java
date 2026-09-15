import components.playlist.Playlist;
import components.playlist.Playlist1;
import components.playlist.PlaylistKernel.Song;

/**
 * Demo of playlist component.
 */
public class PlaylistDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Playlist december25 = new Playlist1();

        final Song song1 = new Song("Take a Trip", "TV Girl & Geroge Clanton");
        final Song song2 = new Song("Diary", "Placid State");
        final Song song3 = new Song("Timeless", "The Weeknd & Playboi Carti");
        final Song song4 = new Song("U Wanna Dance?", "Magdalena Bay");
        final Song song5 = new Song("Blue World", "Mac Miller");
        final Song song6 = new Song("milk of the madonna", "Deftones");
        final Song song7 = new Song("Never Late Again", "George Clanton");
        final Song song8 = new Song("Sky", "Playboi Carti");
        final Song song9 = new Song("Birds", "Turnstile");
        final Song song10 = new Song("HYPERYOUTH", "Joey Valence & Brae");

        Song[] songsToAdd = { song1, song2, song3, song4, song5, song6, song7,
                song8, song9, song10 };

        for (int i = 0; i < songsToAdd.length; ++i) {
            december25.addSong(songsToAdd[i]);
        }

        december25.moveToFront(song8);

        Song removed = december25.removeLastSong();

        december25.moveToBack(song2);

        december25.flipOrder();

        int playlistSize = december25.size();

        december25.shuffle();

    }
}
