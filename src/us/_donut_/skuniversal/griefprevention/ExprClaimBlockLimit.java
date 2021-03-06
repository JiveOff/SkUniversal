package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("GriefPrevention - Claim Block Limit")
@Description("Returns the claim block limit of a player.")
@Examples({"send \"%the claim block limit of player%\""})
public class ExprClaimBlockLimit extends SimpleExpression<Number> {

    private Expression<OfflinePlayer> player;

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
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "claim block limit of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) != null) {
            return new Number[]{GriefPrevention.instance.dataStore.getPlayerData(player.getSingle(e).getUniqueId()).getAccruedClaimBlocksLimit()};
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }

}