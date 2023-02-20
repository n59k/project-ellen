package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl.LwjglBackend;
import sk.tuke.kpi.oop.game.scenarios.FirstSteps;


public class Main {
    public static void main(String[] args) {

        // nastavenie okna hry: nazov okna a jeho rozmery
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 1600, 900);
        // vytvorenie instancie hernej aplikacie
        // pouzijeme implementaciu rozhrania `Game` triedou `GameApplication`
        Game game = new GameApplication(windowSetup, new LwjglBackend());

        // vytvorenie sceny pre hru
        // pouzijeme implementaciu rozhrania `Scene` triedou `World`
        //Scene scene = new World("world", "maps/my-level.tmx");
        //когда нажимаю ЕСКЕЙП будет выход из игры
        game.getInput().onKeyPressed(Input.Key.ESCAPE, () -> game.stop());
        //alebo
        Scene scene = new World("world",
            "maps/my-level.tmx",
            new FirstSteps.Factory());
        scene.addListener(new FirstSteps());

        //ekvivalentný zápis cez referenciu na bezparametrickú metódu
        //game.getInput().onKeyPressed(Input.Key.ESCAPE, game::stop);
        // pridanie sceny do hry
        game.addScene(scene);
        //выставляю свой сценарий
        // spustenie hry
        game.start();
    }
}

