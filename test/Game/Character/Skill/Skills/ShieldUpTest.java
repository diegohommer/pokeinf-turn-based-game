package Skill.Skills;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Game.Character.Player;
import Game.Character.Skill.Skills.ShieldUp;

public class ShieldUpTest {
    private ShieldUp shieldUpSkill;
    private Player player;

    @BeforeEach
    public void setUp() {
        shieldUpSkill = new ShieldUp();
        player = new Player("Defender");

        // Set initial values for testing
        player.setSkillPoints(20);
        player.setShield(0);
    }

    @Test
    public void testApplyEffectSuccess() {
        boolean result = shieldUpSkill.applyEffect(player, player);

        assertTrue(result, "ShieldUp should be successful");
        int expectedShield = 1; // Initial shield generation
        assertEquals(expectedShield, player.getShield(), "Player's shield should be correctly applied");
        assertEquals(10, player.getSkillPoints(), "Player's skill points should be correctly deducted");
    }

    @Test
    public void testApplyEffectInsufficientSkillPoints() {
        player.setSkillPoints(5); // Insufficient skill points
        boolean result = shieldUpSkill.applyEffect(player, player);

        assertFalse(result, "ShieldUp should fail with insufficient skill points");
        assertEquals(0, player.getShield(), "Player's shield should remain unchanged");
    }

    @Test
    public void testUpgradeEffect() {
        boolean result = shieldUpSkill.upgradeEffect();

        assertTrue(result, "Upgrade should be successful");
        assertEquals(2, shieldUpSkill.getSkillLevel(), "Skill level should be 2 after upgrade");
        assertEquals(2, shieldUpSkill.getShieldGen(), "Shield generation should be 2 after upgrade"); // 1 + 1
        assertEquals(15, shieldUpSkill.getCost(), "Skill cost should be 15 after upgrade"); // 10 + 5
    }

    @Test
    public void testUpgradeEffectMaxLevel() {
        for (int i = 0; i < 4; i++) {
            shieldUpSkill.upgradeEffect();
        }

        boolean result = shieldUpSkill.upgradeEffect();
        assertFalse(result, "Upgrade should fail at max level");
        assertEquals(5, shieldUpSkill.getSkillLevel(), "Skill level should be 5 at max level"); // Assuming 5 is the max level
        assertEquals(5, shieldUpSkill.getShieldGen(), "Shield generation should be 5 at max level"); // 1 + (4 * 1)
        assertEquals(30, shieldUpSkill.getCost(), "Skill cost should be 30 at max level"); // 10 + (4 * 5)
    }
}
