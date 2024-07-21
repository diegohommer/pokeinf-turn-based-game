package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class ErrorSkill extends Skill{
    public ErrorSkill(){
        this.name = "UNUSED_SKILL_SLOT";
        this.cost = 0;
        this.hitChance = 0;
        this.skillLevel = 0;
        this.description = "Não existe";
    }
    @Override
    protected boolean applyEffect(Character targetPersonagem) {
       return false;
    }

    @Override
    protected boolean upgradeEffect(){
        return false;
    }
    
}
