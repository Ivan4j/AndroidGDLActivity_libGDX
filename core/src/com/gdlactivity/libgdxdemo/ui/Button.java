package com.gdlactivity.libgdxdemo.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.gdlactivity.libgdxdemo.action.IAction;

/**
 * Created by Ivan_Hernandez on 08/11/2016.
 */

public class Button extends UIComponent {

    private Texture texture;
    private ButtonType type;

    private String text;
    private int id;

    IAction action;

    public Button(ButtonType type) {

        this.type = type;
        setTexture();
    }

    private void setTexture() {

        switch(type) {

            case OK:
                texture = new Texture(Gdx.files.internal("imgs/ui/button_ok.png"));
                break;
            case CANCEL:
                texture = new Texture(Gdx.files.internal("imgs/ui/button_cancel.png"));
                break;
            case CONTINUE:
                texture = new Texture(Gdx.files.internal("imgs/ui/button_continue.png"));
                break;
            case CLOSE:
                texture = new Texture(Gdx.files.internal("imgs/ui/button_close.png"));
                break;
            case START:
                texture = new Texture(Gdx.files.internal("imgs/ui/button_start.png"));
                break;
            case RESTART:
                texture = new Texture(Gdx.files.internal("imgs/ui/button_restart.png"));
                break;
            case PAUSE:
                texture = new Texture(Gdx.files.internal("imgs/ui/button_pause.png"));
                break;
            case RESUME:
                texture = new Texture(Gdx.files.internal("imgs/ui/button_resune.png"));
                break;
            case BACK:
                texture = new Texture(Gdx.files.internal("imgs/ui/button_back.png"));
                break;
            case NONE:
                texture = new Texture(Gdx.files.internal("imgs/ui/button_none.png"));
                break;
        }

        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        setTexture(texture);
        this.setRegion(0, 0, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        this.setOrigin(width/2, height/2);
    }


    public void setText(String text) {
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public int getId() {
        return this.id;
    }

    public void setAction(IAction action) {
        this.action = action;
    }

    public void executeAction() {

        if(this.action != null) {
            System.out.println("Action OK");
            this.action.execute();
        } else
            System.err.println("Action null");
    }

}

