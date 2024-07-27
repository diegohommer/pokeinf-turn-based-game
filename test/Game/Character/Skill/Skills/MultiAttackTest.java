package Skill.Skills;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.Character.Player;
import Game.Character.Skill.Skills.MultiAttack;

public class MultiAttackTest {
    private MultiAttack multiAttackSkill;
    private Player player;
    private Player target;

    @BeforeEach
    public void setUp() {
        multiAttackSkill = new MultiAttack();
        player = new Player("Attacker");
        target = new Player("Defender");

        // Set initial values for testing
        player.setSkillPoints(20);
        player.setLife(100);
        player.setShield(0);
        
        target.setLife(100);
        target.setShield(0);
    }

    @Test
    public void testApplyEffectSuccess() {
        int initialLife = target.getLife();
        int maxDamage = multiAttackSkill.getDamagePerHit();
        int maxHits = multiAttackSkill.getMaxAttacks();
        int minLife = Math.max(target.getLife() - maxDamage * maxHits, 0);
        multiAttackSkill.applyEffect(player, target);

        assertTrue(target.getLife() <= initialLife, "Player's life should not exceed max life");
        assertTrue(target.getLife() >= minLife, "Player's life should be decreased according to the number of successful attacks");
        assertEquals(10, player.getSkillPoints(), "Player's skill points should be correctly deducted");
    }

    @Test
    public void testApplyEffectInsufficientSkillPoints() {
        player.setSkillPoints(5); // Insufficient skill points
        boolean result = multiAttackSkill.applyEffect(player, target);

        assertFalse(result, "Multi-Attack should fail with insufficient skill points");
    }

    @Test
    public void testApplyEffectWithShield() {
        int initialShield = 5;
        int maxHits = multiAttackSkill.getMaxAttacks();
        target.setShield(initialShield);
        boolean result = multiAttackSkill.applyEffect(player, target);

        assertTrue(result, "Multi-Attack should be successful");
        assertTrue(target.getShield() >= initialShield - maxHits, "Player's life should be correctly adjusted based on shield and attacks");
        assertEquals(10, player.getSkillPoints(), "Player's skill points should be correctly deducted");
    }

    @Test
    public void testUpgradeEffect() {
        boolean result = multiAttackSkill.upgradeEffect();

        assertTrue(result, "Upgrade should be successful");
        assertEquals(2, multiAttackSkill.getSkillLevel(), "Skill level should be 2 after upgrade");
        assertEquals(20, multiAttackSkill.getDamagePerHit(), "Damage per hit should be 20 after upgrade"); // 15 + 5
        assertEquals(15, multiAttackSkill.getCost(), "Skill cost should be 15 after upgrade"); // 10 + 5
        assertEquals(0.55, multiAttackSkill.getHitChance(), "Hit chance should be 0.55 after upgrade"); // 0.5 + 0.05
    }

    @Test
    public void testUpgradeEffectMaxLevel() {
        for (int i = 0; i < 4; i++) {
            multiAttackSkill.upgradeEffect();
        }

        boolean result = multiAttackSkill.upgradeEffect();
        assertFalse(result, "Upgrade should fail at max level");
        assertEquals(5, multiAttackSkill.getSkillLevel(), "Skill level should be 5 at max level"); // Assuming 5 is the max level
        assertEquals(35, multiAttackSkill.getDamagePerHit(), "Damage per hit should be 35 at max level"); // 15 + (4 * 5)
        assertEquals(30, multiAttackSkill.getCost(), "Skill cost should be 30 at max level"); // 10 + (4 * 5)
        assertEquals(0.7, multiAttackSkill.getHitChance(), "Hit chance should be 0.8 at max level"); // 0.5 + (4 * 0.05)
    }
}