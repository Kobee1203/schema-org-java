/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import org.schema.model.Person;
import org.schema.model.VideoObject;
import org.schema.model.datatype.Text;
import org.schema.model.CreativeWork;
import org.schema.model.GameServer;
import org.schema.model.GamePlayMode;
import org.schema.model.datatype.URL;
import org.schema.model.Thing;
import org.schema.model.MusicGroup;

/**
 * A video game is an electronic game that involves human interaction with a user interface to generate visual feedback on a video device.
 *
 * @see <a href="https://schema.org/VideoGame">https://schema.org/VideoGame</a>
 */
public interface VideoGame extends SoftwareApplication, Game {

    /**
     * A director of e.g. tv, radio, movie, video gaming etc. content, or of an event. Directors can be associated with individual items or with a series, episode, clip.
     *
     * @return {@link Person}
     */
    Person getDirector();

    /**
     * A director of e.g. tv, radio, movie, video gaming etc. content, or of an event. Directors can be associated with individual items or with a series, episode, clip.
     *
     * @param director Person value to set.
     */
    void setDirector(Person director);

    /**
     * An actor, e.g. in tv, radio, movie, video games etc., or in an event. Actors can be associated with individual items or with a series, episode, clip.
     *
     * @return {@link Person}
     */
    Person getActor();

    /**
     * An actor, e.g. in tv, radio, movie, video games etc., or in an event. Actors can be associated with individual items or with a series, episode, clip.
     *
     * @param actor Person value to set.
     */
    void setActor(Person actor);

    /**
     * The trailer of a movie or tv/radio series, season, episode, etc.
     *
     * @return {@link VideoObject}
     */
    VideoObject getTrailer();

    /**
     * The trailer of a movie or tv/radio series, season, episode, etc.
     *
     * @param trailer VideoObject value to set.
     */
    void setTrailer(VideoObject trailer);

    /**
     * The edition of a video game.
     *
     * @return {@link Text}
     */
    Text getGameEdition();

    /**
     * The edition of a video game.
     *
     * @param gameEdition Text value to set.
     */
    void setGameEdition(Text gameEdition);

    /**
     * Cheat codes to the game.
     *
     * @return {@link CreativeWork}
     */
    CreativeWork getCheatCode();

    /**
     * Cheat codes to the game.
     *
     * @param cheatCode CreativeWork value to set.
     */
    void setCheatCode(CreativeWork cheatCode);

    /**
     * The server on which  it is possible to play the game.
     *
     * @return {@link GameServer}
     */
    GameServer getGameServer();

    /**
     * The server on which  it is possible to play the game.
     *
     * @param gameServer GameServer value to set.
     */
    void setGameServer(GameServer gameServer);

    /**
     * Links to tips, tactics, etc.
     *
     * @return {@link CreativeWork}
     */
    CreativeWork getGameTip();

    /**
     * Links to tips, tactics, etc.
     *
     * @param gameTip CreativeWork value to set.
     */
    void setGameTip(CreativeWork gameTip);

    /**
     * An actor, e.g. in tv, radio, movie, video games etc. Actors can be associated with individual items or with a series, episode, clip.
     *
     * @return {@link Person}
     */
    Person getActors();

    /**
     * An actor, e.g. in tv, radio, movie, video games etc. Actors can be associated with individual items or with a series, episode, clip.
     *
     * @param actors Person value to set.
     */
    void setActors(Person actors);

    /**
     * Indicates whether this game is multi-player, co-op or single-player.  The game can be marked as multi-player, co-op and single-player at the same time.
     *
     * @return {@link GamePlayMode}
     */
    GamePlayMode getPlayMode();

    /**
     * Indicates whether this game is multi-player, co-op or single-player.  The game can be marked as multi-player, co-op and single-player at the same time.
     *
     * @param playMode GamePlayMode value to set.
     */
    void setPlayMode(GamePlayMode playMode);

    /**
     * The electronic systems used to play <a href="http://en.wikipedia.org/wiki/Category:Video_game_platforms">video games</a>.
     *
     * @return {@link Text} or {@link URL} or {@link Thing}
     */
    <T> T getGamePlatform();

    /**
     * The electronic systems used to play <a href="http://en.wikipedia.org/wiki/Category:Video_game_platforms">video games</a>.
     *
     * @param gamePlatform Text value to set.
     */
    void setGamePlatform(Text gamePlatform);
    /**
     * The electronic systems used to play <a href="http://en.wikipedia.org/wiki/Category:Video_game_platforms">video games</a>.
     *
     * @param gamePlatform URL value to set.
     */
    void setGamePlatform(URL gamePlatform);
    /**
     * The electronic systems used to play <a href="http://en.wikipedia.org/wiki/Category:Video_game_platforms">video games</a>.
     *
     * @param gamePlatform Thing value to set.
     */
    void setGamePlatform(Thing gamePlatform);

    /**
     * The composer of the soundtrack.
     *
     * @return {@link Person} or {@link MusicGroup}
     */
    <T> T getMusicBy();

    /**
     * The composer of the soundtrack.
     *
     * @param musicBy Person value to set.
     */
    void setMusicBy(Person musicBy);
    /**
     * The composer of the soundtrack.
     *
     * @param musicBy MusicGroup value to set.
     */
    void setMusicBy(MusicGroup musicBy);

    /**
     * A director of e.g. tv, radio, movie, video games etc. content. Directors can be associated with individual items or with a series, episode, clip.
     *
     * @return {@link Person}
     */
    Person getDirectors();

    /**
     * A director of e.g. tv, radio, movie, video games etc. content. Directors can be associated with individual items or with a series, episode, clip.
     *
     * @param directors Person value to set.
     */
    void setDirectors(Person directors);
}
