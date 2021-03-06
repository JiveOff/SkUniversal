package us._donut_.skuniversal.bedwars;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Bedwars - Game Exists")
@Description("Checks if a Bedwars game with given name exists.")
@Examples({"if bedwars game text-argument exists:"})
public class CondGameExists extends Condition {

    private Expression<String> game;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        game = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "Bedwars game " + game.getSingle(e) + " exists";
    }

    @Override
    public boolean check(Event e) {
        if (game.getSingle(e) != null) {
            return GameManager.existGame(game.getSingle(e));
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return false;
        }
    }
}
