package components.playlist;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.playlist.PlaylistKernel.Song;

/**
 * JUnit test fixture for {@code Playlist}'s constructor, kernel, standard, and
 * secondary methods.
 */
public abstract class PlaylistTest {

    /*
     * Song constants.
     */

    private static final Song A = new Song("Take a Trip",
            "TV Girl & Geroge Clanton");
    private static final Song B = new Song("Diary", "Placid State");
    private static final Song C = new Song("Timeless",
            "The Weeknd & Playboi Carti");
    private static final Song D = new Song("U Wanna Dance?", "Magdalena Bay");
    private static final Song E = new Song("Blue World", "Mac Miller");

    /**
     * Invokes the appropriate {@code Playlist} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new playlist
     * @ensures constructorTest = {}
     */
    protected abstract Playlist constructorTest();

    /**
     * Creates and returnes a {@code Playlist} of the implementation under test
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed playlist
     * @ensures createFromArgsTest = [entries in args]
     */
    private Playlist createFromArgs(Song... args) {
        Playlist playlist = this.constructorTest();
        for (Song s : args) {
            playlist.addSong(s);
        }
        return playlist;
    }

    /*
     * Testing constructor.
     */

    @Test
    public void testConstructorEmpty() {
        Playlist newPlaylist = this.constructorTest();
        Playlist expected = this.constructorTest();
        assertEquals(expected, newPlaylist);
    }

    /*
     * Testing standard methods.
     */

    @Test
    public void testClear() {
        Playlist newPlaylist = this.createFromArgs(A, B, C);
        newPlaylist.clear();
        assertEquals(this.constructorTest(), newPlaylist);
    }

    @Test
    public void testNewInstanceEmpty() {
        Playlist newPlaylist = this.constructorTest();
        Playlist newPlaylistTwo = newPlaylist.newInstance();
        assertEquals(this.constructorTest(), newPlaylistTwo);
    }

    @Test
    public void testTransferFromOne() {
        Playlist newPlaylist = this.constructorTest();
        Playlist newPlaylistTwo = this.createFromArgs(A);

        newPlaylist.transferFrom(newPlaylistTwo);

        assertEquals(this.createFromArgs(A), newPlaylist);
        assertEquals(this.constructorTest(), newPlaylistTwo);
    }

    @Test
    public void testTransferFromMany() {
        Playlist newPlaylist = this.constructorTest();
        Playlist newPlaylistTwo = this.createFromArgs(A, B, C);

        newPlaylist.transferFrom(newPlaylistTwo);

        assertEquals(this.createFromArgs(A, B, C), newPlaylist);
        assertEquals(this.constructorTest(), newPlaylistTwo);
    }

    /*
     * Testing kernel methods.
     */

    @Test
    public void testAddSongNone() {
        Playlist newPlaylist = this.constructorTest();
        newPlaylist.addSong(A);
        assertEquals(this.createFromArgs(A), newPlaylist);
    }

    @Test
    public void testAddSongOne() {
        Playlist newPlaylist = this.createFromArgs(A);
        newPlaylist.addSong(B);
        assertEquals(this.createFromArgs(A, B), newPlaylist);
    }

    @Test
    public void testAddSongMany() {
        Playlist newPlaylist = this.createFromArgs(A, B);
        newPlaylist.addSong(C);
        assertEquals(this.createFromArgs(A, B, C), newPlaylist);
    }

    @Test
    public void testRemoveSongOne() {
        Playlist newPlaylist = this.createFromArgs(A);
        Song removed = newPlaylist.removeSong(A);
        assertEquals(A, removed);
        assertEquals(this.constructorTest(), newPlaylist);
    }

    @Test
    public void testRemoveSongManyFirst() {
        Playlist newPlaylist = this.createFromArgs(A, B, C);
        Song removed = newPlaylist.removeSong(A);
        assertEquals(A, removed);
        assertEquals(this.createFromArgs(B, C), newPlaylist);
    }

    @Test
    public void testRemoveSongManyLast() {
        Playlist newPlaylist = this.createFromArgs(A, B, C);
        Song removed = newPlaylist.removeSong(C);
        assertEquals(C, removed);
        assertEquals(this.createFromArgs(A, B), newPlaylist);
    }

    @Test
    public void testRemoveSongManyMiddle() {
        Playlist newPlaylist = this.createFromArgs(A, B, C);
        Song removed = newPlaylist.removeSong(B);
        assertEquals(B, removed);
        assertEquals(this.createFromArgs(A, C), newPlaylist);
    }

    @Test
    public void testSizeNone() {
        Playlist newPlaylist = this.constructorTest();
        assertEquals(0, newPlaylist.size());
    }

    @Test
    public void testSizeOne() {
        Playlist newPlaylist = this.createFromArgs(A);
        assertEquals(1, newPlaylist.size());
    }

    @Test
    public void testSizeMany() {
        Playlist newPlaylist = this.createFromArgs(A, B, C);
        final int three = 3;
        assertEquals(three, newPlaylist.size());
    }

    @Test
    public void testRemoveLastSongOne() {
        Playlist newPlaylist = this.createFromArgs(A);
        Song removed = newPlaylist.removeLastSong();
        assertEquals(A, removed);
        assertEquals(this.constructorTest(), newPlaylist);
    }

    @Test
    public void testRemoveLastSongMany() {
        Playlist newPlaylist = this.createFromArgs(A, B, C);
        Song removed = newPlaylist.removeLastSong();
        assertEquals(C, removed);
        assertEquals(this.createFromArgs(A, B), newPlaylist);
    }

    @Test
    public void testHasSongNone() {
        Playlist newPlaylist = this.constructorTest();
        assertEquals(false, newPlaylist.hasSong(A));
    }

    @Test
    public void testHasSongTrue() {
        Playlist newPlaylist = this.createFromArgs(A, B, C);
        assertEquals(true, newPlaylist.hasSong(A));
    }

    @Test
    public void testHasSongFalse() {
        Playlist newPlaylist = this.createFromArgs(A, B);
        assertEquals(false, newPlaylist.hasSong(C));
    }

    /*
     * Testing secondary methods.
     */

    @Test
    public void testFlipOrderTwo() {
        Playlist newPlaylist = this.createFromArgs(A, B);
        newPlaylist.flipOrder();
        assertEquals(this.createFromArgs(B, A), newPlaylist);
    }

    @Test
    public void testFlipOrderMany() {
        Playlist newPlaylist = this.createFromArgs(A, B, C);
        newPlaylist.flipOrder();
        assertEquals(this.createFromArgs(C, B, A), newPlaylist);
    }

    @Test
    public void testMoveToFront() {
        Playlist newPlaylist = this.createFromArgs(A, B, C);
        newPlaylist.moveToFront(C);
        assertEquals(this.createFromArgs(C, A, B), newPlaylist);
    }

    @Test
    public void testMoveToBack() {
        Playlist newPlaylist = this.createFromArgs(A, B, C);
        newPlaylist.moveToBack(A);
        assertEquals(this.createFromArgs(B, C, A), newPlaylist);
    }

    @Test
    public void testStartFromMiddle() {
        Playlist newPlaylist = this.createFromArgs(A, B, C, D, E);
        newPlaylist.startFrom(C);
        assertEquals(this.createFromArgs(C, D, E, A, B), newPlaylist);
    }

    @Test
    public void testStartFromEnd() {
        Playlist newPlaylist = this.createFromArgs(A, B, C, D, E);
        newPlaylist.startFrom(E);
        assertEquals(this.createFromArgs(E, A, B, C, D), newPlaylist);
    }
}
