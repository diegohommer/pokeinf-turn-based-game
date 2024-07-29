package Skill.Skills;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Game.Character.Player;
import Game.Character.Skill.Skills.QuickAttack;

public class QuickAttackTest {
    private QuickAttack quickAttackSkill;
    private Player caster;
    private Player target;

    @BeforeEach
    public void setUp() {
        quickAttackSkill = new QuickAttack();
        caster = new Player("Attacker");
        target = new Player("Defender");

        // Set the initial values for the target's shield and life
        target.setShield(0);
        target.setLife(100);
    }

    @Test
    public void testApplyEffectSucess() {
        int expectedLife = Math.max(0, target.getLife() - quickAttackSkill.getDamage());
        boolean result = quickAttackSkill.applyEffect(caster, target);

        assertTrue(result, "Attack should be successful");
        assertEquals(expectedLife, target.getLife(), "Target's life should be reduced by the damage amount");
        assertEquals(0, target.getShield(), "Target's shield should remain 0");
    }

    @Test
    public void testApplyEffectWithShield() {
        int expectedLife = target.getLife();
        target.setShield(10); // Set shield to 10
        boolean result = quickAttackSkill.applyEffect(caster, target);

        assertTrue(result, "Attack should be successful");
        assertEquals(expectedLife, target.getLife(), "Target's life should be reduced by the damage amount");
        assertEquals(9, target.getShield(), "Target's shield should be reduced by 1");
    }

    @Test
    public void testUpgradeEffect() {
        boolean result = quickAttackSkill.upgradeEffect();

        assertTrue(result, "Upgrade should be successful");
        assertEquals(2, quickAttackSkill.getSkillLevel(), "Skill level should be 2 after upgrade");
        assertEquals(25, quickAttackSkill.getDamage(), "Damage should be 25 after upgrade"); // 20 + 5
        assertEquals(7, quickAttackSkill.getCost(), "Skill cost should be 7 after upgrade"); // 5 + 2
    }

    @Test
    public void testUpgradeEffectMaxLevel() {
        for (int i = 0; i < 4; i++) {
            quickAttackSkill.upgradeEffect();
        }

        boolean result = quickAttackSkill.upgradeEffect();
        assertFalse(result, "Upgrade should fail at max level");
        assertEquals(5, quickAttackSkill.getSkillLevel(), "Skill level should be 5 at max level"); // Assuming 5 is the max level
        assertEquals(40, quickAttackSkill.getDamage(), "Damage should be 40 at max level"); // 20 + (4 * 5)
        assertEquals(13, quickAttackSkill.getCost(), "Skill cost should be 13 at max level"); // 5 + (4 * 2)
    }

    @Test
    public void testApplyEffectInsufficientSkillPoints() {
        caster.setSkillPoints(0); // Insufficient skill points
        boolean result = quickAttackSkill.applyEffect(caster, target);

        assertFalse(result, "Quick-Attack should fail with insufficient skill points");
    }
}
