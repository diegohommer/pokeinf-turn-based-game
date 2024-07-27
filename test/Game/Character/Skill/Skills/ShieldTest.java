package Skill.Skills;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.Character.Player;
import Game.Character.Skill.Skills.Shield;

public class ShieldTest {
    private Shield shieldSkill;
    private Player player;

    @BeforeEach
    public void setUp() {
        shieldSkill = new Shield();
        player = new Player("Defender");
        player.setShield(0);
    }

    @Test
    public void testApplyEffectSuccess() {
        boolean result = shieldSkill.applyEffect(player, player);

        assertTrue(result, "Shielding should be successful");
        int expectedShield = Math.min(player.getMaxShield(), 0 + shieldSkill.getShieldPoints());
        assertEquals(expectedShield, player.getShield(), "Player's shield should be correctly increased");
    }

    @Test
    public void testApplyEffectRespectsMaxShield() {
        player.setShield(player.getMaxShield() - 1); // Set shield close to max
        boolean result = shieldSkill.applyEffect(player, player);

        assertTrue(result, "Shielding should be successful");
        assertEquals(player.getMaxShield(), player.getShield(), "Player's shield should not exceed max shield");
    }

    @Test
    public void testUpgradeEffect() {        assertNotNull(shieldSkill, "shieldSkill should not be null");
        boolean result = shieldSkill.upgradeEffect();

        assertTrue(result, "Upgrade should be successful");
        assertEquals(2, shieldSkill.getSkillLevel(), "Skill level should be 2 after upgrade");
        assertEquals(2, shieldSkill.getShieldPoints(), "Shield points should be 2 after upgrade"); // 1 + 1
        assertEquals(8, shieldSkill.getCost(), "Skill cost should be 8 after upgrade"); // 5 + 3
    }

    @Test
    public void testUpgradeEffectMaxLevel() {
        for (int i = 0; i < 4; i++) {
            shieldSkill.upgradeEffect();
        }

        boolean result = shieldSkill.upgradeEffect();
        assertFalse(result, "Upgrade should fail at max level");
        assertEquals(5, shieldSkill.getSkillLevel(), "Skill level should be 5 at max level"); // Assuming 5 is the max level
        assertEquals(5, shieldSkill.getShieldPoints(), "Shield points should be 5 at max level"); // 1 + (4 * 1)
        assertEquals(17, shieldSkill.getCost(), "Skill cost should be 17 at max level"); // 5 + (4 * 3)
    }
}