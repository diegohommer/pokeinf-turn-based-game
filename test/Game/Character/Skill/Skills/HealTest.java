package Skill.Skills;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Game.Character.Player;
import Game.Character.Skill.Skills.Heal;

  public class HealTest {
    private Heal healSkill;
    private Player player;

    @BeforeEach
    public void setUp() {
        healSkill = new Heal();
        player = new Player("Healer");

        // Set the initial life points to a value less than the max to test healing
        player.setSkillPoints(20);
        player.setLife(50);
    }

    @Test
    public void testApplyEffectSuccess() {
        boolean result = healSkill.applyEffect(player, player);

        assertTrue(result, "Healing should be successful");
        int expectedLife = Math.min(player.getMaxLife(), 50 + healSkill.getHealPoints());
        assertEquals(expectedLife, player.getLife(), "Player's life should be correctly healed");
    }

    @Test
    public void testApplyEffectRespectsMaxLife() {
        player.setLife(player.getMaxLife() - 10); // Set life close to max
        boolean result = healSkill.applyEffect(player, player);

        assertTrue(result, "Healing should be successful");
        assertEquals(player.getMaxLife(), player.getLife(), "Player's life should not exceed max life");
    }

    @Test
    public void testUpgradeEffect() {
        boolean result = healSkill.upgradeEffect();

        assertTrue(result, "Upgrade should be successful");
        assertEquals(2, healSkill.getSkillLevel(), "Skill level should be 2 after upgrade");
        assertEquals(30, healSkill.getHealPoints(), "Heal points should be 30 after upgrade"); // 25 + 5
        assertEquals(8, healSkill.getCost(), "Skill cost should be 8 after upgrade"); // 5 + 3
    }

    @Test
    public void testApplyEffectInsufficientSkillPoints() {
        player.setSkillPoints(0); // Insufficient skill points
        boolean result = healSkill.applyEffect(player, player);

        assertFalse(result, "Heal should fail with insufficient skill points");
        assertEquals(50, player.getLife(), "Player's life should remain unchanged");
    }

    @Test
    public void testUpgradeEffectMaxLevel() {
        for (int i = 0; i < 4; i++) {
            healSkill.upgradeEffect();
        }

        boolean result = healSkill.upgradeEffect();
        assertFalse(result, "Upgrade should fail at max level");
        assertEquals(5, healSkill.getSkillLevel(), "Skill level should be 5 at max level"); // Assuming 5 is the max level
        assertEquals(45, healSkill.getHealPoints(), "Heal points should be 45 at max level"); // 25 + (4 * 5)
        assertEquals(17, healSkill.getCost(), "Skill cost should be 17 at max level"); // 5 + (4 * 3)
    }
}
