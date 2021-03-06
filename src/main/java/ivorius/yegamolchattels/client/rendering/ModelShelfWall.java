/***************************************************************************************************
 * Copyright (c) 2014, Lukas Tenbrink.
 * http://lukas.axxim.net
 **************************************************************************************************/

// Date: 3-1-2014 16:22:40
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package ivorius.yegamolchattels.client.rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShelfWall extends ModelBase
{
    //fields
    ModelRenderer plank;
    ModelRenderer support1;
    ModelRenderer support2;

    public ModelShelfWall()
    {
        textureWidth = 64;
        textureHeight = 32;

        plank = new ModelRenderer(this, 0, 0);
        plank.addBox(-8F, 0F, -2F, 16, 2, 10);
        plank.setRotationPoint(0F, 20F, 0F);
        plank.setTextureSize(64, 32);
        plank.mirror = true;
        setRotation(plank, 0F, 0F, 0F);
        support1 = new ModelRenderer(this, 0, 13);
        support1.addBox(-1F, -1F, 0F, 2, 2, 7);
        support1.setRotationPoint(-6F, 22F, 1.5F);
        support1.setTextureSize(64, 32);
        support1.mirror = true;
        setRotation(support1, -0.1115358F, 0F, 0F);
        support2 = new ModelRenderer(this, 19, 13);
        support2.addBox(-1F, -1F, 0F, 2, 2, 7);
        support2.setRotationPoint(6F, 22F, 1.5F);
        support2.setTextureSize(64, 32);
        support2.mirror = true;
        setRotation(support2, -0.1115358F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        plank.render(f5);
        support1.render(f5);
        support2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
