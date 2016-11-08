package com.gdlactivity.libgdxdemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.gdlactivity.libgdxdemo.GDLActivity;
import com.gdlactivity.libgdxdemo.ui.Button;
import com.gdlactivity.libgdxdemo.ui.ButtonType;
import com.gdlactivity.libgdxdemo.ui.UIComponent;
import com.gdlactivity.libgdxdemo.utils.Constants;

/**
 * Created by Ivan_Hernandez on 07/11/2016.
 */

public class Menu extends AbstractScreen {

    UIComponent[] uiButtons = new Button[4];

    public Menu() {

        float cellWidth = Constants.SCREEN_WIDTH / 4;
        int row = 1;
        for(int i=0; i<uiButtons.length; i++) {

            uiButtons[i] = new Button(ButtonType.NONE);
            int col = 0;
            if(i % 2 == 0) {
                row += 2;
                col += 2;
            }

            float x = cellWidth * (col) + cellWidth / 3;
            float y = cellWidth * row;

            uiButtons[i].setBounds(x, y - 100, cellWidth * 1.5f, cellWidth * 1.5f);

            ((Button)uiButtons[i]).setId(i);
            uiComponents.add(uiButtons[i]);
        }

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        GDLActivity.getSpriteBatch().begin();

        for(UIComponent ui : uiButtons) {
            ui.draw(GDLActivity.getSpriteBatch());
        }

        GDLActivity.getSpriteBatch().end();
    }

}
