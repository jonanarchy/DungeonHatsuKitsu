package com.mygdx.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Attack extends Character implements Serializable {

    public boolean collided;

    public Attack(Vector2 pos, String spriteLink, Player player) {

        super(pos, spriteLink);

        if (player.flipped){
            sprite.flip(true,false);
            sprite.setX(pos.x-sprite.getWidth());
            direction.x = -1;
        } else {
            sprite.setX(pos.x+player.sprite.getWidth()/2);
            direction.x = 1;
        }
        sprite.setRegion(0,0,25,25);
        sprite.setScale(25/64f);
        hitRect = new Rectangle(sprite.getX(),sprite.getY(),25*playerScale,25*playerScale);

        gravSpeed = 0;
        damage = 1;
        speed = 500;
    }
}