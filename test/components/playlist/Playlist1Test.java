package components.playlist;

/**
 * Customized JUnit test fixture for {@code Playlist1}.
 */
public class Playlist1Test extends PlaylistTest {

    @Override
    protected final Playlist constructorTest() {
        return new Playlist1();
    }
}
