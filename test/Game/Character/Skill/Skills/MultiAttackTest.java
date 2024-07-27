package Skill.Skills;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.Character.Player;
import Game.Character.Skill.Skills.MultiAttack;

public class MultiAttackTest {
    private MultiAttack multiAttackSkill;
    private Player attacker;
    private Player target;

    @BeforeEach
    public void setUp() {
        multiAttackSkill = new MultiAttack();
        attacker = new Player("Attacker");
        target = new Player("Target");

        // Initialize target's health and shield
        target.setLife(100);
        target.setShield(10);
    }

    @Test
    public void testApplyEffectSuccess() {
        // Apply effect and verify
        boolean result = multiAttackSkill.applyEffect(attacker, target);

        assertTrue(result, "MultiAttack should succeed");
        int expectedShield = Math.max(0, target.getShield() - multiAttackSkill.getMaxAttacks());
        int expectedLife = target.getLife() - (multiAttackSkill.getDamagePerHit() * (multiAttackSkill.getMaxAttacks() - (target.getShield() > multiAttackSkill.getMaxAttacks() ? target.getShield() : 0)));

        assertEquals(expectedShield, target.getShield(), "Target's shield should be correctly decreased");
        assertEquals(expectedLife, target.getLife(), "Target's life should be correctly decreased based on damage and shield");
    }

    @Test
    public void testApplyEffectRespectsHitChance() {
        // Modify the hit chance to 0 for a specific test case
        multiAttackSkill.setHitChance(0.0);
        
        boolean result = multiAttackSkill.applyEffect(attacker, target);

        assertFalse(result, "MultiAttack should fail due to 0 hit chance");
        assertEquals(100, target.getLife(), "Target's life should remain unchanged due to 0 hit chance");
        assertEquals(10, target.getShield(), "Target's shield should remain unchanged due to 0 hit chance");
    }

    @Test
    public void testUpgradeEffect() {
        boolean result = multiAttackSkill.upgradeEffect();

        assertTrue(result, "Upgrade should be successful");
        assertEquals(2, multiAttackSkill.getSkillLevel(), "Skill level should be 2 after upgrade");
        assertEquals(25, multiAttackSkill.getDamagePerHit(), "Damage per hit should be 25 after upgrade"); // 20 + 5
        assertEquals(15, multiAttackSkill.getCost(), "Skill cost should be 15 after upgrade"); // 10 + 5
        assertEquals(0.55, multiAttackSkill.getHitChance(), "Hit chance should be 0.55 after upgrade"); // 0.5 + 0.05
    }

    @Test
    public void testUpgradeEffectMaxLevel() {
        // Assuming max level is defined somewhere, here assumed as 5 for example
        for (int i = 0; i < 4; i++) {
            multiAttackSkill.upgradeEffect();
        }

        boolean result = multiAttackSkill.upgradeEffect();
        assertFalse(result, "Upgrade should fail at max level");
        assertEquals(5, multiAttackSkill.getSkillLevel(), "Skill level should be 5 at max level"); // Assuming 5 is the max level
        assertEquals(40, multiAttackSkill.getDamagePerHit(), "Damage per hit should be 40 at max level"); // 20 + (4 * 5)
        assertEquals(30, multiAttackSkill.getCost(), "Skill cost should be 30 at max level"); // 10 + (4 * 5)
        assertEquals(0.8, multiAttackSkill.getHitChance(), "Hit chance should be 0.8 at max level"); // 0.5 + (4 * 0.05)
    }
}