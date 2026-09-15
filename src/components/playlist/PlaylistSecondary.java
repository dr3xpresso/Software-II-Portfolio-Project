package components.playlist;

/**
 * Layered implementations of secondary methods for {@code Playlist}.
 */
public abstract class PlaylistSecondary implements Playlist {

    /*
     * Public members ---------------------------------------------------------
     */

    /*
     * Common methods (from Object) -------------------------------------------
     */

    @Override
    public final boolean equals(Object obj) {
        boolean result = false;

        if (this == obj) {
            result = true;
        } else if (obj instanceof Playlist) {
            Playlist other = (Playlist) obj;
            if (this.size() == other.size()) {
                Playlist thisTemp = this.newInstance();
                Playlist otherTemp = other.newInstance();
                result = true;

                while (this.size() > 0 && result) {
                    Song song1 = this.removeLastSong();
                    Song song2 = other.removeLastSong();
                    if (!song1.equals(song2)) {
                        result = false;
                    }
                    thisTemp.addSong(song1);
                    otherTemp.addSong(song2);
                }

                this.transferFrom(thisTemp);
                other.transferFrom(otherTemp);
            }
        }

        return result;
    }

    @Override
    public final int hashCode() {
        int hash = 1;
        final int hashMultiplier = 67;
        Playlist temp = this.newInstance();

        while (this.size() > 0) {
            Song currentSong = this.removeLastSong();
            hash = hashMultiplier * hash + currentSong.hashCode();
            temp.addSong(currentSong);
        }

        this.transferFrom(temp);
        return hash;
    }

    @Override
    public final String toString() {
        String result = "<";
        Playlist temp = this.newInstance();

        int i = 0;
        while (this.size() > 0) {
            Song currentSong = this.removeLastSong();
            if (i > 0) {
                result += ", ";
            }
            result += currentSong;
            temp.addSong(currentSong);
            i++;
        }

        this.transferFrom(temp);
        result += ">";
        return result;
    }

    /*
     * Other non-kernel methods -----------------------------------------------
     */

    @Override
    public final void flipOrder() {
        assert this.size() >= 2 : "Violation of: |this| >= 2";

        Playlist temp = this.newInstance();

        while (this.size() > 0) {
            Song song = this.removeLastSong();
            temp.addSong(song);
        }

        this.transferFrom(temp);
    }

    @Override
    public final void moveToFront(Song song) {
        assert this.size() >= 2 : "Violation of: |this| >= 2";
        assert this.hasSong(song) : "Violation of: song is in this";

        Song removed = this.removeSong(song);

        Playlist temp = this.newInstance();
        while (this.size() > 0) {
            temp.addSong(this.removeLastSong());
        }

        Playlist result = this.newInstance();
        result.addSong(removed);
        while (temp.size() > 0) {
            result.addSong(temp.removeLastSong());
        }

        this.transferFrom(result);
    }

    @Override
    public final void moveToBack(Song song) {
        assert this.size() >= 2 : "Violation of: |this| >= 2";
        assert this.hasSong(song) : "Violation of: song is in this";

        Song removed = this.removeSong(song);

        Playlist temp = this.newInstance();
        while (this.size() > 0) {
            temp.addSong(this.removeLastSong());
        }

        Playlist result = this.newInstance();
        while (temp.size() > 0) {
            result.addSong(temp.removeLastSong());
        }
        result.addSong(removed);

        this.transferFrom(result);

    }

    @Override
    public final void shuffle() {
        assert this.size() >= 2 : "Violation of: |this| >= 2";

        Playlist temp = this.newInstance();

        while (this.size() > 0) {
            temp.addSong(this.removeLastSong());
        }

        java.util.Random rand = new java.util.Random();
        while (temp.size() > 0) {
            int remaining = temp.size();
            int randomIndex = rand.nextInt(remaining);

            Playlist holder = this.newInstance();
            for (int i = 0; i < randomIndex; i++) {
                holder.addSong(temp.removeLastSong());
            }

            Song randomSong = temp.removeLastSong();

            while (holder.size() > 0) {
                temp.addSong(holder.removeLastSong());
            }

            this.addSong(randomSong);
        }

    }

    @Override
    public final void startFrom(Song song) {
        assert this.size() >= 2 : "Violation of: |this| >= 2";
        assert this.hasSong(song) : "Violation of: song is in this";

        Playlist before = this.newInstance();
        Playlist after = this.newInstance();

        Song current = this.removeLastSong();
        while (!current.equals(song)) {
            after.addSong(current);
            current = this.removeLastSong();
        }

        while (this.size() > 0) {
            before.addSong(this.removeLastSong());
        }

        Playlist result = this.newInstance();

        result.addSong(current);

        while (after.size() > 0) {
            result.addSong(after.removeLastSong());
        }

        while (before.size() > 0) {
            result.addSong(before.removeLastSong());
        }

        this.transferFrom(result);

    }

}
