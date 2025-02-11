package de.jeff_media.jefflib;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;

import java.util.Objects;

/**
 * Experience related methods
 */
@UtilityClass
public final class ExpUtils {

    /**
     * Gets the total amount of XP required to achieve a certain level when starting from 0 levels
     *
     * @param targetLevel Target level
     * @return Amount of XP required to reach the target level
     */
    public static int getTotalXPRequiredForLevel(final int targetLevel) {
        if (targetLevel <= 16) return sqrt(targetLevel) + (6 * targetLevel);
        if (targetLevel <= 31) return (int) ((2.5 * sqrt(targetLevel)) - (40.5 * targetLevel) + 360);
        return (int) ((4.5 * sqrt(targetLevel)) - (162.5 * targetLevel) + 2220);
    }

    /**
     * Gets the total amount of XP required to reach currentLevel+1 from currentLevel
     *
     * @param currentLevel Current level
     * @return Amount of XP required to reach currentLevel+1
     */
    public static int getXPRequiredForNextLevel(final int currentLevel) {
        if (currentLevel <= 15) return (2 * currentLevel) + 7;
        if (currentLevel <= 30) return (5 * currentLevel) - 38;
        return (9 * currentLevel) - 158;
    }

    /**
     * Drops an experience orb at the given location with the given amount of experience
     *
     * @param location Location
     * @param xp       Amount of experience
     */
    public static void dropExp(final Location location, final int xp) {
        final ExperienceOrb orb = (ExperienceOrb) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.EXPERIENCE_ORB);
        orb.setExperience(xp);
    }

    private static int sqrt(final int a) {
        return a * a;
    }
}
