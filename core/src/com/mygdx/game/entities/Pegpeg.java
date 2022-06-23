package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;
import com.mygdx.game.entities.loot.LootHealth;

public class Pegpeg extends Enemy {

      GameScreen gameScreen;

      public Pegpeg(Vector2 pos) {
            super(pos);

            sprite = getSpriteByPos(0, 0);

            hitRect = new Rectangle(pos.x, pos.y, 32 * playerScale, 32 * playerScale);
            setHealth(7);
            damage = 1;
            speed = 0;
            maxSpeed = speed;
            sprite.flip(true, false);
            range = 600;
            reactionTime = 0.2f;
            reactionTimeMax = reactionTime;
            shootDelay = 2;
            shootDelayMax = shootDelay;

            gameScreen = GameScreen.getInstance();
      }

      @Override
      public void dropLoot() {
            super.dropLoot();
            gameScreen.loot.add(new LootHealth(new Vector2(getMidX(), getMidY())));
            gameScreen.score += 20;
      }

      // the enemy should shoot at the player when in range
      @Override
      void searchPlayer() {
            for (Player p : gameScreen.players) {
                  float dist = GameScreen.getDistance(p.getMidPosition(), this.getMidPosition());
                  if (dist < range) {
                        // a function to determine which direction the enemy should head in
                        determineDirection(p);
                        if (dist < range / 2f) {
                              if (shootDelay > 0)
                                    shootDelay -= Gdx.graphics.getDeltaTime();
                              else {
                                    shootDelay = shootDelayMax;
                                    shoot();
                              }
                        }
                  } else {
                        // set enemy direction to 0 if player is too far away
                        direction.x = 0;
                  }
            }
      }

      // a function to make the enemy shoot
      @Override
      void shoot() {
            gameScreen = GameScreen.getInstance();
            gameScreen.enemyBullets.add(new EnemyBullet(getPosition(), this));
      }
}
