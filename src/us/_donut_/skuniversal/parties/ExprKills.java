package us._donut_.skuniversal.parties;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.alessiodp.parties.Parties;
import com.alessiodp.parties.utils.api.PartiesAPI;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Parties - Party Kills")
@Description("Returns the amount of kills of a party.")
@Examples({"send \"%the kills of the party named \"cool\"%\""})
public class ExprKills extends SimpleExpression<Number> {

    PartiesAPI parties = new PartiesAPI();
    private Expression<String> name;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "kills of party named " + name.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (name.getSingle(e) != null) {
            return new Number[]{parties.getPartyKills(name.getSingle(e))};
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        Number killsChange = (Number) delta[0];
        Number currentKills = parties.getPartyKills(name.getSingle(e));
        if (mode == Changer.ChangeMode.SET) {
            Parties.getInstance().getPartyHandler().loadParty(name.getSingle(e)).setKills(killsChange.intValue());
        } else if (mode == Changer.ChangeMode.ADD) {
            Parties.getInstance().getPartyHandler().loadParty(name.getSingle(e)).setKills(currentKills.intValue()+killsChange.intValue());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            Parties.getInstance().getPartyHandler().loadParty(name.getSingle(e)).setKills(currentKills.intValue()-killsChange.intValue());;
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }
}