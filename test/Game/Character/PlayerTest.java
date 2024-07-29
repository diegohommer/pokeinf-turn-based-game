import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.Character.Player;
import Game.Character.Skill.Skills.*;
import Game.Character.Skill.Skill;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("TestPlayer");
    }

    @Test
    public void testAddSkillSuccess() {
        Skill newSkill = new ShieldUp();
        boolean result = player.addSkill(newSkill);

        assertTrue(result, "Skill should be added successfully");
        assertTrue(player.getUnlockedSkillsList().contains(newSkill), "Unlocked skills should contain the new skill");
    }

    @Test
    public void testAddSkillDuplicate() {
        Skill newSkill = new QuickAttack();
        player.addSkill(newSkill); // Adding skill first time

        boolean result = player.addSkill(newSkill);

        assertFalse(result, "Adding a duplicate skill should fail");
        assertEquals(1, player.getUnlockedSkillsList().stream().filter(skill -> skill.getName().equals(newSkill.getName())).count(), "Skill should not be duplicated in the list");
    }

    @Test
    public void testSetSkillSlotSuccess() {
        Skill newSkill = new ShieldUp();
        player.addSkill(newSkill);
        boolean result = player.setSkillSlot(newSkill, 1);

        assertTrue(result, "Skill should be set in the slot successfully");
        assertEquals(newSkill, player.getActiveSkills().get(1), "The skill slot should contain the new skill");
    }

    @Test
    public void testSetSkillSlotInvalidSlot() {
        Skill newSkill = new ShieldUp();
        player.addSkill(newSkill);
        boolean result = player.setSkillSlot(newSkill, 5);

        assertFalse(result, "Setting skill in an invalid slot should fail");
    }

    @Test
    public void testSelectSkillValid() {
        Skill selectedSkill = player.selectSkill(1);

        assertNotNull(selectedSkill, "Selected skill should not be null");
        assertEquals("Multi-Attack", selectedSkill.getName(), "Selected skill should be MultiAttack");
    }

    @Test
    public void testSelectSkillInvalid() {
        Skill selectedSkill = player.selectSkill(5);

        assertNotNull(selectedSkill, "Selected skill should not be null");
        assertTrue(selectedSkill instanceof ErrorSkill, "Selected skill should be an instance of ErrorSkill for invalid index");
    }
}
