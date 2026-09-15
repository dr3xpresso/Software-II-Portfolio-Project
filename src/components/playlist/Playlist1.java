package components.playlist;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * {@code Playlist} represented as a {@link components.queue.Queue} with
 * implementations of primary methods.
 *
 * @correspondence this = entries($this.rep)
 * @convention <this.rep> is not null
 */
public class Playlist1 extends PlaylistSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private Queue<Song> rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Queue1L<>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Playlist1() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final Playlist newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Playlist source) {
        assert source != null : "Violation: source is not null";
        assert source != this : "Violation: source is not this";

        Playlist1 localSource = (Playlist1) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods --------------------------------------------------------x
     */
    @Override
    public final void addSong(Song song) {
        this.rep.enqueue(song);
    }

    @Override
    public final Song removeSong(Song song) {
        assert this.hasSong(song) : "Violation: song is not in this!";

        Queue<Song> temp = this.rep.newInstance();
        Song removed = null;
        boolean removedFirst = false;

        while (this.rep.length() > 0) {
            Song x = this.rep.dequeue();

            if (!removedFirst && x.equals(song)) {
                removed = x;
                removedFirst = true;
            } else {
                temp.enqueue(x);
            }
        }

        this.rep.transferFrom(temp);

        return removed;
    }

    @Override
    public final int size() {
        int length = this.rep.length();

        return length;
    }

    @Override
    public final Song removeLastSong() {
        assert this.rep.length() > 0 : "Violation of: |this| > 0";

        Queue<Song> temp = this.rep.newInstance();

        while (this.rep.length() > 1) {
            temp.enqueue(this.rep.dequeue());
        }
        Song removed = this.rep.dequeue();

        this.rep.transferFrom(temp);

        return removed;
    }

    @Override
    public final boolean hasSong(Song song) {
        boolean songIn = false;
        Queue<Song> temp = this.rep.newInstance();

        while (this.rep.length() > 0) {
            Song x = this.rep.dequeue();
            if (x.equals(song)) {
                songIn = true;
            }
            temp.enqueue(x);
        }

        this.rep.transferFrom(temp);
        return songIn;
    }
}
