import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import Game.Character.Character;
import Game.Character.Skill.Skill;
import Game.Character.Skill.Skills.Heal;

class CharacterTest {
    private Character character;

    @BeforeEach
    void setUp() {
        character = new Character("path/to/sprite.png", "Test Character", 100, 50, 10, new ArrayList<>()) {
            // Implementing an abstract class with dummy methods
            @Override
            public boolean setActiveSkills(ArrayList<Skill> activeSkills) {
                return super.setActiveSkills(activeSkills);
            }
        };
    }

    @Test
    void testSetLife() {
        character.setLife(120);
        assertEquals(100, character.getLife(), "Life should be capped at maxLife");

        character.setLife(-10);
        assertEquals(0, character.getLife(), "Life should be at least 0");
    }

    @Test
    void testSetShield() {
        character.setShield(60);
        assertEquals(50, character.getShield(), "Shield should be capped at maxShield");

        character.setShield(-10);
        assertEquals(0, character.getShield(), "Shield should be at least 0");
    }

    @Test
    void testSetSkillPoints() {
        character.setSkillPoints(15);
        assertEquals(10, character.getSkillPoints(), "Skill points should be capped at maxSkillPoints");

        character.setSkillPoints(-5);
        assertEquals(0, character.getSkillPoints(), "Skill points should be at least 0");
    }

    @Test
    void testSetMaxLife() {
        assertTrue(character.setMaxLife(200), "Max life should be set successfully");
        assertFalse(character.setMaxLife(-10), "Max life should not be set to negative value");
    }

    @Test
    void testSetMaxShield() {
        assertTrue(character.setMaxShield(100), "Max shield should be set successfully");
        assertFalse(character.setMaxShield(-10), "Max shield should not be set to negative value");
    }

    @Test
    void testSetMaxSkillPoints() {
        assertTrue(character.setMaxSkillPoints(20), "Max skill points should be set successfully");
        assertFalse(character.setMaxSkillPoints(-10), "Max skill points should not be set to negative value");
    }

    @Test
    void testDeleteSkill() {
        Skill skill1 = new Heal(); // Assuming Skill class has a default constructor
        Skill skill2 = new Heal();

        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        character.setActiveSkills(skills);

        assertTrue(character.deleteSkill(0), "Skill at index 0 should be removed");
        assertFalse(character.deleteSkill(5), "Deleting skill at index out of bounds should return false");
        assertEquals(1, character.getActiveSkills().size(), "Active skills should be reduced by one");
    }

    @Test
    void testIsDead() {
        character.setLife(0);
        assertTrue(character.isDead(), "Character with 0 life should be dead");

        character.setLife(50);
        assertFalse(character.isDead(), "Character with life greater than 0 should not be dead");
    }
}
