package Game.Character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

import Game.Character.Skill.Skill;

public class Enemy extends Character
{
    private int difficulty;
    private static final int MAX_SHIELD = 3;
    private Map<Skill.Type, ArrayList<Skill>> skillsByType = new HashMap<Skill.Type, ArrayList<Skill>>();

    public Enemy(String spritePath, String name, int life, int skillPoints, ArrayList<Skill> activeSkills)
    {
        super(
            spritePath, 
            name, 
            life, 
            MAX_SHIELD, 
            skillPoints, 
            activeSkills);

        for (Skill skill: getActiveSkills())
        {
            ArrayList<Skill> skillsOfType = skillsByType.get(skill.getType());
            if(skillsOfType != null)
                skillsOfType.add(skill);
            else
                skillsByType.put(skill.getType(), new ArrayList<>(Arrays.asList(skill)));
        }
    }

    public Skill resolveNextSkill(final Player p) {
        Player targetTemp = new Player("TempTarget");
        Enemy casterTemp = new Enemy("TempCaster", name, maxLife, maxSkillPoints, activeSkills);

        for(Skill attackSkill: skillsByType.get(Skill.Type.DAMAGE))
        {
            targetTemp.setLife(p.getLife());
            targetTemp.setShield(p.getShield());

            attackSkill.applyEffect(casterTemp, targetTemp);
            if(attackSkill.getCost() <= skillPoints && targetTemp.isDead())
                return attackSkill;

            casterTemp.resetStatus();
        }

        if((float)life < (float)maxLife * 0.3f)
        {
            ArrayList<Skill> healingSkills = skillsByType.get(Skill.Type.HEALING);
            if(healingSkills != null && healingSkills.get(0).getCost() <= skillPoints)
                return healingSkills.get(0);
        }

        if((float)skillPoints < (float)maxSkillPoints * 0.2f)
        {
            ArrayList<Skill> restSkills = skillsByType.get(Skill.Type.REST);
            if(restSkills != null && restSkills.get(0).getCost() <= skillPoints)
                return restSkills.get(0);
        }

        int rndIndex = (int)Math.round(Math.random() * (double)(activeSkills.size() - 1));

        return activeSkills.get(rndIndex);
    }
}
