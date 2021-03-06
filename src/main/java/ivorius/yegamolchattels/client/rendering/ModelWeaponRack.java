/***************************************************************************************************
 * Copyright (c) 2014, Lukas Tenbrink.
 * http://lukas.axxim.net
 **************************************************************************************************/

// Date: 29-3-2013 18:56:29
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package ivorius.yegamolchattels.client.rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWeaponRack extends ModelBase
{
    //fields
    ModelRenderer pole1;
    ModelRenderer pole2;
    ModelRenderer pole3;
    ModelRenderer pole4;
    ModelRenderer fronttop;
    ModelRenderer frontbottom;
    ModelRenderer backtop;
    ModelRenderer backbottom;
    ModelRenderer left;
    ModelRenderer right;
    ModelRenderer backdetail1;
    ModelRenderer backdetail2;
    ModelRenderer frontdetail1;
    ModelRenderer frontdetail2;
    ModelRenderer rightdetail1;
    ModelRenderer rightdetail2;
    ModelRenderer rightdetail3;
    ModelRenderer rightdetail4;
    ModelRenderer leftdetail1;
    ModelRenderer leftdetail2;
    ModelRenderer leftdetail3;
    ModelRenderer leftdetail4;
    ModelRenderer connectionright;
    ModelRenderer connectionleft;
    ModelRenderer connectionmid;
    ModelRenderer bottom;

    public ModelWeaponRack()
    {
        textureWidth = 128;
        textureHeight = 64;

        pole1 = new ModelRenderer(this, 0, 11);
        pole1.addBox(-7F, -4F, 3F, 2, 16, 2);
        pole1.setRotationPoint(0F, 12F, 0F);
        pole1.setTextureSize(64, 32);
        pole1.mirror = true;
        setRotation(pole1, 0F, 0F, 0F);
        pole2 = new ModelRenderer(this, 10, 11);
        pole2.addBox(-7F, -4F, -5F, 2, 16, 2);
        pole2.setRotationPoint(0F, 12F, 0F);
        pole2.setTextureSize(64, 32);
        pole2.mirror = true;
        setRotation(pole2, 0F, 0F, 0F);
        pole3 = new ModelRenderer(this, 20, 11);
        pole3.addBox(5F, -4F, 3F, 2, 16, 2);
        pole3.setRotationPoint(0F, 12F, 0F);
        pole3.setTextureSize(64, 32);
        pole3.mirror = true;
        setRotation(pole3, 0F, 0F, 0F);
        pole4 = new ModelRenderer(this, 30, 11);
        pole4.addBox(5F, -4F, -5F, 2, 16, 2);
        pole4.setRotationPoint(0F, 12F, 0F);
        pole4.setTextureSize(64, 32);
        pole4.mirror = true;
        setRotation(pole4, 0F, 0F, 0F);
        fronttop = new ModelRenderer(this, 0, 30);
        fronttop.addBox(-5F, -3F, -5F, 10, 2, 1);
        fronttop.setRotationPoint(0F, 12F, 0F);
        fronttop.setTextureSize(64, 32);
        fronttop.mirror = true;
        setRotation(fronttop, 0F, 0F, 0F);
        frontbottom = new ModelRenderer(this, 0, 35);
        frontbottom.addBox(-5F, 0F, -5F, 10, 2, 1);
        frontbottom.setRotationPoint(0F, 12F, 0F);
        frontbottom.setTextureSize(64, 32);
        frontbottom.mirror = true;
        setRotation(frontbottom, 0F, 0F, 0F);
        backtop = new ModelRenderer(this, 24, 30);
        backtop.addBox(-5F, -3F, 4F, 10, 2, 1);
        backtop.setRotationPoint(0F, 12F, 0F);
        backtop.setTextureSize(64, 32);
        backtop.mirror = true;
        setRotation(backtop, 0F, 0F, 0F);
        backbottom = new ModelRenderer(this, 24, 35);
        backbottom.addBox(-5F, 0F, 4F, 10, 2, 1);
        backbottom.setRotationPoint(0F, 12F, 0F);
        backbottom.setTextureSize(64, 32);
        backbottom.mirror = true;
        setRotation(backbottom, 0F, 0F, 0F);
        left = new ModelRenderer(this, 0, 40);
        left.addBox(6F, -3F, -3F, 1, 2, 6);
        left.setRotationPoint(0F, 12F, 0F);
        left.setTextureSize(64, 32);
        left.mirror = true;
        setRotation(left, 0F, 0F, 0F);
        right = new ModelRenderer(this, 15, 40);
        right.addBox(-7F, -3F, -3F, 1, 2, 6);
        right.setRotationPoint(0F, 12F, 0F);
        right.setTextureSize(64, 32);
        right.mirror = true;
        setRotation(right, 0F, 0F, 0F);
        backdetail1 = new ModelRenderer(this, 48, 30);
        backdetail1.addBox(1F, -1F, 4F, 2, 1, 1);
        backdetail1.setRotationPoint(0F, 12F, 0F);
        backdetail1.setTextureSize(64, 32);
        backdetail1.mirror = true;
        setRotation(backdetail1, 0F, 0F, 0F);
        backdetail2 = new ModelRenderer(this, 48, 33);
        backdetail2.addBox(-3F, -1F, 4F, 2, 1, 1);
        backdetail2.setRotationPoint(0F, 12F, 0F);
        backdetail2.setTextureSize(64, 32);
        backdetail2.mirror = true;
        setRotation(backdetail2, 0F, 0F, 0F);
        frontdetail1 = new ModelRenderer(this, 48, 36);
        frontdetail1.addBox(1F, -1F, -5F, 2, 1, 1);
        frontdetail1.setRotationPoint(0F, 12F, 0F);
        frontdetail1.setTextureSize(64, 32);
        frontdetail1.mirror = true;
        setRotation(frontdetail1, 0F, 0F, 0F);
        frontdetail2 = new ModelRenderer(this, 48, 39);
        frontdetail2.addBox(-3F, -1F, -5F, 2, 1, 1);
        frontdetail2.setRotationPoint(0F, 12F, 0F);
        frontdetail2.setTextureSize(64, 32);
        frontdetail2.mirror = true;
        setRotation(frontdetail2, 0F, 0F, 0F);
        rightdetail1 = new ModelRenderer(this, 15, 49);
        rightdetail1.addBox(-7F, -1F, 1F, 1, 1, 2);
        rightdetail1.setRotationPoint(0F, 12F, 0F);
        rightdetail1.setTextureSize(64, 32);
        rightdetail1.mirror = true;
        setRotation(rightdetail1, 0F, 0F, 0F);
        rightdetail2 = new ModelRenderer(this, 22, 49);
        rightdetail2.addBox(-7F, -1F, -3F, 1, 1, 2);
        rightdetail2.setRotationPoint(0F, 12F, 0F);
        rightdetail2.setTextureSize(64, 32);
        rightdetail2.mirror = true;
        setRotation(rightdetail2, 0F, 0F, 0F);
        rightdetail3 = new ModelRenderer(this, 15, 53);
        rightdetail3.addBox(-7F, 0F, 2F, 1, 2, 1);
        rightdetail3.setRotationPoint(0F, 12F, 0F);
        rightdetail3.setTextureSize(64, 32);
        rightdetail3.mirror = true;
        setRotation(rightdetail3, 0F, 0F, 0F);
        rightdetail4 = new ModelRenderer(this, 20, 53);
        rightdetail4.addBox(-7F, 0F, -3F, 1, 2, 1);
        rightdetail4.setRotationPoint(0F, 12F, 0F);
        rightdetail4.setTextureSize(64, 32);
        rightdetail4.mirror = true;
        setRotation(rightdetail4, 0F, 0F, 0F);
        leftdetail1 = new ModelRenderer(this, 0, 49);
        leftdetail1.addBox(6F, -1F, -3F, 1, 1, 2);
        leftdetail1.setRotationPoint(0F, 12F, 0F);
        leftdetail1.setTextureSize(64, 32);
        leftdetail1.mirror = true;
        setRotation(leftdetail1, 0F, 0F, 0F);
        leftdetail2 = new ModelRenderer(this, 7, 49);
        leftdetail2.addBox(6F, -1F, 1F, 1, 1, 2);
        leftdetail2.setRotationPoint(0F, 12F, 0F);
        leftdetail2.setTextureSize(64, 32);
        leftdetail2.mirror = true;
        setRotation(leftdetail2, 0F, 0F, 0F);
        leftdetail3 = new ModelRenderer(this, 0, 53);
        leftdetail3.addBox(6F, 0F, -3F, 1, 2, 1);
        leftdetail3.setRotationPoint(0F, 12F, 0F);
        leftdetail3.setTextureSize(64, 32);
        leftdetail3.mirror = true;
        setRotation(leftdetail3, 0F, 0F, 0F);
        leftdetail4 = new ModelRenderer(this, 5, 53);
        leftdetail4.addBox(6F, 0F, 2F, 1, 2, 1);
        leftdetail4.setRotationPoint(0F, 12F, 0F);
        leftdetail4.setTextureSize(64, 32);
        leftdetail4.mirror = true;
        setRotation(leftdetail4, 0F, 0F, 0F);
        connectionright = new ModelRenderer(this, 41, 0);
        connectionright.addBox(-4F, -2F, -4F, 1, 1, 8);
        connectionright.setRotationPoint(0F, 12F, 0F);
        connectionright.setTextureSize(64, 32);
        connectionright.mirror = true;
        setRotation(connectionright, 0F, 0F, 0F);
        connectionleft = new ModelRenderer(this, 60, 0);
        connectionleft.addBox(3F, -2F, -4F, 1, 1, 8);
        connectionleft.setRotationPoint(0F, 12F, 0F);
        connectionleft.setTextureSize(64, 32);
        connectionleft.mirror = true;
        setRotation(connectionleft, 0F, 0F, 0F);
        connectionmid = new ModelRenderer(this, 79, 0);
        connectionmid.addBox(-1F, -2F, -4F, 2, 1, 8);
        connectionmid.setRotationPoint(0F, 12F, 0F);
        connectionmid.setTextureSize(64, 32);
        connectionmid.mirror = true;
        setRotation(connectionmid, 0F, 0F, 0F);
        bottom = new ModelRenderer(this, 0, 0);
        bottom.addBox(-6F, 8F, -4F, 12, 1, 8);
        bottom.setRotationPoint(0F, 12F, 0F);
        bottom.setTextureSize(64, 32);
        bottom.mirror = true;
        setRotation(bottom, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        pole1.render(f5);
        pole2.render(f5);
        pole3.render(f5);
        pole4.render(f5);
        fronttop.render(f5);
        frontbottom.render(f5);
        backtop.render(f5);
        backbottom.render(f5);
        left.render(f5);
        right.render(f5);
        backdetail1.render(f5);
        backdetail2.render(f5);
        frontdetail1.render(f5);
        frontdetail2.render(f5);
        rightdetail1.render(f5);
        rightdetail2.render(f5);
        rightdetail3.render(f5);
        rightdetail4.render(f5);
        leftdetail1.render(f5);
        leftdetail2.render(f5);
        leftdetail3.render(f5);
        leftdetail4.render(f5);
        connectionright.render(f5);
        connectionleft.render(f5);
        connectionmid.render(f5);
        bottom.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
