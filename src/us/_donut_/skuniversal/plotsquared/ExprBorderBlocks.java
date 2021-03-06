package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotId;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Name("PlotSquared - Plot Border Blocks")
@Description("Returns the border blocks of a plot.")
@Examples({"send \"%the border blocks at height 65 of the plot with id (id of plot at player)%\""})
public class ExprBorderBlocks extends SimpleExpression<Block> {

    private PlotAPI plot = new PlotAPI();
    private Expression<String> id;
    private Expression<Number> borderHeight;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Block> getReturnType() {
        return Block.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        borderHeight = (Expression<Number>) e[0];
        id = (Expression<String>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "border blocks of plot of with id " + id.getSingle(e);
    }

    private Boolean isNorthEastCorner(com.intellectualcrafters.plot.object.Location cornerInQuestion, com.intellectualcrafters.plot.object.Location corner2, com.intellectualcrafters.plot.object.Location corner3, com.intellectualcrafters.plot.object.Location corner4) {
        return cornerInQuestion.getZ() <= corner2.getZ() && cornerInQuestion.getZ() <= corner3.getZ() && cornerInQuestion.getZ() <= corner4.getZ() && cornerInQuestion.getX() >= corner2.getX() && cornerInQuestion.getX() >= corner3.getX() && cornerInQuestion.getX() >= corner4.getX();
    }

    private Boolean isNorthWestCorner(com.intellectualcrafters.plot.object.Location cornerInQuestion, com.intellectualcrafters.plot.object.Location corner2, com.intellectualcrafters.plot.object.Location corner3, com.intellectualcrafters.plot.object.Location corner4) {
        return cornerInQuestion.getZ() <= corner2.getZ() && cornerInQuestion.getZ() <= corner3.getZ() && cornerInQuestion.getZ() <= corner4.getZ() && cornerInQuestion.getX() <= corner2.getX() && cornerInQuestion.getX() <= corner3.getX() && cornerInQuestion.getX() <= corner4.getX();
    }

    private Boolean isSouthEastCorner(com.intellectualcrafters.plot.object.Location cornerInQuestion, com.intellectualcrafters.plot.object.Location corner2, com.intellectualcrafters.plot.object.Location corner3, com.intellectualcrafters.plot.object.Location corner4) {
        return cornerInQuestion.getZ() >= corner2.getZ() && cornerInQuestion.getZ() >= corner3.getZ() && cornerInQuestion.getZ() >= corner4.getZ() && cornerInQuestion.getX() >= corner2.getX() && cornerInQuestion.getX() >= corner3.getX() && cornerInQuestion.getX() >= corner4.getX();
    }

    private Boolean isSouthWestCorner(com.intellectualcrafters.plot.object.Location cornerInQuestion, com.intellectualcrafters.plot.object.Location corner2, com.intellectualcrafters.plot.object.Location corner3, com.intellectualcrafters.plot.object.Location corner4) {
        return cornerInQuestion.getZ() >= corner2.getZ() && cornerInQuestion.getZ() >= corner3.getZ() && cornerInQuestion.getZ() >= corner4.getZ() && cornerInQuestion.getX() <= corner2.getX() && cornerInQuestion.getX() <= corner3.getX() && cornerInQuestion.getX() <= corner4.getX();
    }

    private com.intellectualcrafters.plot.object.Location getNorthEastCorner(com.intellectualcrafters.plot.object.Location corner1, com.intellectualcrafters.plot.object.Location corner2, com.intellectualcrafters.plot.object.Location corner3, com.intellectualcrafters.plot.object.Location corner4) {
        if (isNorthEastCorner(corner1, corner2, corner3, corner4)) {
            return corner1;
        } else if (isNorthEastCorner(corner2, corner1, corner3, corner4)) {
            return corner2;
        } else if (isNorthEastCorner(corner3, corner4, corner2, corner1)) {
            return corner3;
        } else {
            return corner4;
        }
    }

    private com.intellectualcrafters.plot.object.Location getNorthWestCorner(com.intellectualcrafters.plot.object.Location corner1, com.intellectualcrafters.plot.object.Location corner2, com.intellectualcrafters.plot.object.Location corner3, com.intellectualcrafters.plot.object.Location corner4) {
        if (isNorthWestCorner(corner1, corner2, corner3, corner4)) {
            return corner1;
        } else if (isNorthWestCorner(corner2, corner1, corner3, corner4)) {
            return corner2;
        } else if (isNorthWestCorner(corner3, corner4, corner2, corner1)) {
            return corner3;
        } else {
            return corner4;
        }
    }

    private com.intellectualcrafters.plot.object.Location getSouthEastCorner(com.intellectualcrafters.plot.object.Location corner1, com.intellectualcrafters.plot.object.Location corner2, com.intellectualcrafters.plot.object.Location corner3, com.intellectualcrafters.plot.object.Location corner4) {
        if (isSouthEastCorner(corner1, corner2, corner3, corner4)) {
            return corner1;
        } else if (isSouthEastCorner(corner2, corner1, corner3, corner4)) {
            return corner2;
        } else if (isSouthEastCorner(corner3, corner4, corner2, corner1)) {
            return corner3;
        } else {
            return corner4;
        }
    }

    private com.intellectualcrafters.plot.object.Location getSouthWestCorner(com.intellectualcrafters.plot.object.Location corner1, com.intellectualcrafters.plot.object.Location corner2, com.intellectualcrafters.plot.object.Location corner3, com.intellectualcrafters.plot.object.Location corner4) {
        if (isSouthWestCorner(corner1, corner2, corner3, corner4)) {
            return corner1;
        } else if (isSouthWestCorner(corner2, corner1, corner3, corner4)) {
            return corner2;
        } else if (isSouthWestCorner(corner3, corner4, corner2, corner1)) {
            return corner3;
        } else {
            return corner4;
        }
    }

    @Override
    @Nullable
    protected Block[] get(Event e) {
        if (id.getSingle(e) != null) {
            if (borderHeight.getSingle(e) != null) {
                List<Block> blocks = new ArrayList<>();
                PlotId plotId = PlotId.fromString(id.getSingle(e));
                if (plotId == null) {
                    Skript.error("Invalid plot ID, please refer to the syntax");
                    return null;
                } else {
                    for (Plot aPlot : plot.getAllPlots()) {
                        if (aPlot.getId().equals(plotId)) {
                            if (aPlot.getAllCorners().size() == 4) {
                                com.intellectualcrafters.plot.object.Location northEastCorner = getNorthEastCorner(aPlot.getAllCorners().get(0), aPlot.getAllCorners().get(1), aPlot.getAllCorners().get(2), aPlot.getAllCorners().get(3)).add(1, borderHeight.getSingle(e).intValue(), -1);
                                com.intellectualcrafters.plot.object.Location northWestCorner = getNorthWestCorner(aPlot.getAllCorners().get(0), aPlot.getAllCorners().get(1), aPlot.getAllCorners().get(2), aPlot.getAllCorners().get(3)).add(-1, borderHeight.getSingle(e).intValue(), -1);
                                com.intellectualcrafters.plot.object.Location southEastCorner = getSouthEastCorner(aPlot.getAllCorners().get(0), aPlot.getAllCorners().get(1), aPlot.getAllCorners().get(2), aPlot.getAllCorners().get(3)).add(1, borderHeight.getSingle(e).intValue(), 1);
                                com.intellectualcrafters.plot.object.Location southWestCorner = getSouthWestCorner(aPlot.getAllCorners().get(0), aPlot.getAllCorners().get(1), aPlot.getAllCorners().get(2), aPlot.getAllCorners().get(3)).add(-1, borderHeight.getSingle(e).intValue(), 1);
                                for (double x = northWestCorner.getX(); x < northEastCorner.getX() + 1; x++) {
                                    blocks.add(new Location(Bukkit.getWorld(northWestCorner.getWorld()), x, borderHeight.getSingle(e).intValue(), northWestCorner.getZ()).getBlock());
                                }
                                for (double z = northEastCorner.getZ(); z < southEastCorner.getZ() + 1; z++) {
                                    blocks.add(new Location(Bukkit.getWorld(northEastCorner.getWorld()), northEastCorner.getX(), borderHeight.getSingle(e).intValue(), z).getBlock());
                                }
                                for (double z = northWestCorner.getZ(); z < southWestCorner.getZ() + 1; z++) {
                                    blocks.add(new Location(Bukkit.getWorld(northWestCorner.getWorld()), northWestCorner.getX(), borderHeight.getSingle(e).intValue(), z).getBlock());
                                }
                                for (double x = southWestCorner.getX(); x < southEastCorner.getX() + 1; x++) {
                                    blocks.add(new Location(Bukkit.getWorld(southWestCorner.getWorld()), x, borderHeight.getSingle(e).intValue(), southWestCorner.getZ()).getBlock());
                                }
                                return blocks.toArray(new Block[blocks.size()]);
                            } else {
                                Skript.error("Plot must be a square");
                            }
                        }
                    }
                    Skript.error("Invalid plot ID, please refer to the syntax");
                    return null;
                }
            } else {
                Skript.error("Must provide a number, please refer to the syntax");
                return null;
            }
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }
}