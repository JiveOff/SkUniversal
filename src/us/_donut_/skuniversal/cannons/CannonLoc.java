package us._donut_.skuniversal.cannons;

import at.pavlov.cannons.Cannons;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.UUID;

@Name("Cannons - Cannon Location")
@Description("Returns the location of a cannon.")
@Examples({"send \"%the location of the cannon with id (id of cannon at player)%\""})
public class CannonLoc extends SimpleExpression<Location> {

    private Expression<String> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the location of cannon with ID " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected Location[] get(Event e) {
        if (id.getSingle(e) != null) {
            if (Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))) != null) {
                return new Location[]{ Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).getLocation() };
            } else {
                return null;
            }
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }

}
