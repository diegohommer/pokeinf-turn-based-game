package Skill;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.Character.Skill.Skill;

public class SkillTest {
    private TestSkill skill;

    @BeforeEach
    public void setUp() {
        skill = new TestSkill();
    }

    @Test
    public void testGetAndSetSpritePath() {
        assertEquals("test/path.png", skill.getSpritePath(), "Initial sprite path should be 'test/path.png'");
        
        boolean result = skill.setSpritePath("new/path.png");
        assertTrue(result, "Setting a valid sprite path should return true");
        assertEquals("new/path.png", skill.getSpritePath(), "Sprite path should be updated to 'new/path.png'");

        result = skill.setSpritePath(null);
        assertFalse(result, "Setting a null sprite path should return false");
        assertEquals("new/path.png", skill.getSpritePath(), "Sprite path should remain 'new/path.png'");
    }

    @Test
    public void testGetAndSetName() {
        assertEquals("Test Skill", skill.getName(), "Initial name should be 'Test Skill'");
        
        boolean result = skill.setName("New Skill Name");
        assertTrue(result, "Setting a valid name should return true");
        assertEquals("New Skill Name", skill.getName(), "Name should be updated to 'New Skill Name'");

        result = skill.setName(null);
        assertFalse(result, "Setting a null name should return false");
        assertEquals("New Skill Name", skill.getName(), "Name should remain 'New Skill Name'");
    }

    @Test
    public void testGetAndSetDescription() {
        assertEquals("This is a test skill.", skill.getDescription(), "Initial description should be 'This is a test skill.'");
        
        boolean result = skill.setDescription("New Description");
        assertTrue(result, "Setting a valid description should return true");
        assertEquals("New Description", skill.getDescription(), "Description should be updated to 'New Description'");

        result = skill.setDescription(null);
        assertFalse(result, "Setting a null description should return false");
        assertEquals("New Description", skill.getDescription(), "Description should remain 'New Description'");
    }

    @Test
    public void testGetAndSetSkillLevel() {
        assertEquals(1, skill.getSkillLevel(), "Initial skill level should be 1");
        
        skill.setSkillLevel(3);
        assertEquals(3, skill.getSkillLevel(), "Skill level should be updated to 3");

        skill.setSkillLevel(10);
        assertEquals(5, skill.getSkillLevel(), "Skill level should be clamped to 5");

        skill.setSkillLevel(0);
        assertEquals(1, skill.getSkillLevel(), "Skill level should be clamped to 1");
    }

    @Test
    public void testGetAndSetCost() {
        assertEquals(10, skill.getCost(), "Initial cost should be 10");
        
        skill.setCost(15);
        assertEquals(15, skill.getCost(), "Cost should be updated to 15");

        skill.setCost(-5);
        assertEquals(0, skill.getCost(), "Cost should be clamped to 0");
    }

    @Test
    public void testGetAndSetHitChance() {
        assertEquals(0.5, skill.getHitChance(), 0.0001, "Initial hit chance should be 0.5");
        
        skill.setHitChance(0.75);
        assertEquals(0.75, skill.getHitChance(), 0.0001, "Hit chance should be updated to 0.75");

        skill.setHitChance(-0.1);
        assertEquals(0.0, skill.getHitChance(), 0.0001, "Hit chance should be clamped to 0.0");

        skill.setHitChance(1.1);
        assertEquals(1.0, skill.getHitChance(), 0.0001, "Hit chance should be clamped to 1.0");
    }

    @Test
    public void testGetType() {
        System.out.println(Skill.Type.DAMAGE);
        assertEquals(Skill.Type.DAMAGE, skill.getType(), "Initial type should be DAMAGE");
    }

    @Test
    public void testSetType() {
        skill.setType(Skill.Type.HEALING);
        assertEquals(Skill.Type.HEALING, skill.getType(), "Type should be updated to HEALING");
    }

    @Test
    public void testIsMaxLevel() {
        assertFalse(Skill.isMaxLevel(skill), "Initial skill should not be at max level");

        skill.setSkillLevel(5);
        assertTrue(Skill.isMaxLevel(skill), "Skill should be at max level");
    }

    @Test
    public void testDidItHit() {
        // This is a probabilistic test; we'll just call the method and ensure no exceptions are thrown
        skill.setHitChance(1.0);
        assertTrue(skill.didItHit(), "With hit chance set to 1.0, didItHit should always return true");

        skill.setHitChance(0.0);
        assertFalse(skill.didItHit(), "With hit chance set to 0.0, didItHit should always return false");
    }
}