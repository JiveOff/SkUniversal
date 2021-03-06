package us._donut_.skuniversal.cannons;

import at.pavlov.cannons.Cannons;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.UUID;

@Name("Cannons - Is Cannon Overheated")
@Description("Checks if a cannon is overheated.")
@Examples({"if the cannon with id (id of cannon at player) is overheated:"})
public class CondOverheated extends Condition {

    private Expression<String> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "cannon with ID " + id.getSingle(e) + " is overheated";
    }

    @Override
    public boolean check(Event e) {
        if(id.getSingle(e)!=null){
            return Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))) != null && Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).isOverheated();
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return false;
        }
    }

}
