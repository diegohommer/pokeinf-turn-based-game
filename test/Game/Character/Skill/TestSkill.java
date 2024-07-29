package Skill;

import Game.Character.Skill.Skill;
import Game.Character.Character;

public class TestSkill extends Skill {
    public TestSkill() {
        this.name = "Test Skill";
        this.spritePath = "test/path.png";
        this.description = "This is a test skill.";
        this.cost = 10;
        this.skillLevel = 1;
        this.hitChance = 0.5;
        this.type = Type.DAMAGE;
    }

    private static final int MAX_LEVEL = 5;

    @Override
    public boolean applyEffect(Character casterCharacter, Character targetCharacter) {
        // Dummy implementation for testing purposes
        return true;
    }

    @Override
    public void setType(Type t)
    {
        type = t;
    }

    @Override
    public boolean upgradeEffect() {
        // Dummy implementation for testing purposes
        if (this.skillLevel < MAX_LEVEL) {
            this.skillLevel++;
            return true;
        }
        return false;
    }
}
