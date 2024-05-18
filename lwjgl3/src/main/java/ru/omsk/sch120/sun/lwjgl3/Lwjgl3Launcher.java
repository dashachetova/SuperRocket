package ru.omsk.sch120.sun.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import ru.omsk.sch120.sun.FirstScreen;
import ru.omsk.sch120.sun.GameStarter;
import ru.omsk.sch120.sun.MainGame;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
        //Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        //config.setForegroundFPS(60);
        ////config.setTitle("SunFirstGame");
        //config.setWindowedMode(FirstScreen.WORLD_WIDTH, FirstScreen.SCREEN_HEIGHT);
        //new Lwjgl3Application(new GameStarter(), config);
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new MainGame(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Super Rocket");
        configuration.useVsync(true);
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate);
        configuration.setWindowedMode(MainGame.WORLD_WIDTH, MainGame.WORLD_HEIGHT);
        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");

        return configuration;
    }
}
