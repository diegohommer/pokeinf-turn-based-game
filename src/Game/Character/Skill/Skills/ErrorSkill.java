package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class ErrorSkill extends Skill{
    // class constants
    private static final String SPRITE_PATH = "assets//errorSkill.png";
    private static final String SKILL_NAME = "UNUSED_SKILL_SLOT";
    private static final int INITIAL_LEVEL = 0;
    private static final int INITIAL_COST = 0;
    private static final double INITIAL_HIT_CHANCE = 0.0;

    public ErrorSkill(){
        super.setName(SKILL_NAME);
        super.setSpritePath(SPRITE_PATH);
        super.setCost(INITIAL_COST);
        super.setHitChance(INITIAL_HIT_CHANCE);
        super.setSkillLevel(INITIAL_LEVEL);
        super.setDescription("");
    }

    // skills methods
    @Override
    public boolean applyEffect(Character casterCharacter, Character targetCharacter) {
       return false;
    }

    @Override
    public boolean upgradeEffect(){
        return false;
    }
    
    @Override
    public void resetLevel()
    {
        skillLevel = INITIAL_LEVEL;
        cost = INITIAL_COST;
        hitChance = INITIAL_HIT_CHANCE;
    }
}
