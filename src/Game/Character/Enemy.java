package Game.Character;

import java.util.ArrayList;

import Game.Character.Skill.Skill;
import Game.Character.Skill.Skills.ErrorSkill;

public class Enemy extends Character
{
    private int difficulty;
    private static final int MAX_SHIELD = 3;
    private static final int NUM_SKILLS = 4;

    Enemy(String spritePath, String name, int life, 
    int maxSkillPoints, ArrayList<Skill> activeSkills)
    {
        super(
            spritePath, 
            name, 
            life, 
            MAX_SHIELD, 
            maxSkillPoints, 
            activeSkills);
    }
}
