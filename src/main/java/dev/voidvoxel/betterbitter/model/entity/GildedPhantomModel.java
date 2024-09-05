package dev.voidvoxel.betterbitter.model.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class GildedPhantomModel extends EntityModel<Entity> {
	private final ModelPart body;

	public GildedPhantomModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(64, 0).cuboid(-8.0F, -16.0F, -32.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-8.0F, -8.0F, -16.0F, 16.0F, 16.0F, 32.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, 0.0F));

		ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(0, 48).cuboid(-6.0F, -14.0F, 24.0F, 12.0F, 12.0F, 20.0F, new Dilation(0.0F))
		.uv(44, 48).cuboid(-4.0F, -12.0F, 44.0F, 8.0F, 8.0F, 12.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.0F, -10.0F, 56.0F, 4.0F, 4.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 22).cuboid(-1.0F, -9.0F, 62.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.0F, -8.0F));

		ModelPartData leg = body.addChild("leg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 16.0F, -8.0F));

		ModelPartData leg_left = leg.addChild("leg_left", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -6.0F, 0.0F));

		ModelPartData leg_left_upper = leg_left.addChild("leg_left_upper", ModelPartBuilder.create(), ModelTransform.pivot(14.0F, 0.0F, -6.0F));

		ModelPartData leg_0 = leg_left_upper.addChild("leg_0", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leg_lower_r1 = leg_0.addChild("leg_lower_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r1 = leg_0.addChild("leg_upper_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_1 = leg_left_upper.addChild("leg_left_1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 8.0F));

		ModelPartData leg_lower_r2 = leg_left_1.addChild("leg_lower_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r2 = leg_left_1.addChild("leg_upper_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_2 = leg_left_upper.addChild("leg_left_2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 16.0F));

		ModelPartData leg_lower_r3 = leg_left_2.addChild("leg_lower_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r3 = leg_left_2.addChild("leg_upper_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_3 = leg_left_upper.addChild("leg_left_3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 24.0F));

		ModelPartData leg_lower_r4 = leg_left_3.addChild("leg_lower_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r4 = leg_left_3.addChild("leg_upper_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_lower = leg_left.addChild("leg_left_lower", ModelPartBuilder.create(), ModelTransform.of(23.0F, 4.0F, -6.0F, 0.0F, 0.0F, -1.5708F));

		ModelPartData leg_left_4 = leg_left_lower.addChild("leg_left_4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leg_lower_r5 = leg_left_4.addChild("leg_lower_r5", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r5 = leg_left_4.addChild("leg_upper_r5", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_5 = leg_left_lower.addChild("leg_left_5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 8.0F));

		ModelPartData leg_lower_r6 = leg_left_5.addChild("leg_lower_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r6 = leg_left_5.addChild("leg_upper_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_6 = leg_left_lower.addChild("leg_left_6", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 16.0F));

		ModelPartData leg_lower_r7 = leg_left_6.addChild("leg_lower_r7", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r7 = leg_left_6.addChild("leg_upper_r7", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_7 = leg_left_lower.addChild("leg_left_7", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 24.0F));

		ModelPartData leg_lower_r8 = leg_left_7.addChild("leg_lower_r8", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r8 = leg_left_7.addChild("leg_upper_r8", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_right = leg.addChild("leg_right", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -6.0F, 0.0F));

		ModelPartData leg_left_upper3 = leg_right.addChild("leg_left_upper3", ModelPartBuilder.create(), ModelTransform.pivot(14.0F, 0.0F, -6.0F));

		ModelPartData leg_left_24 = leg_left_upper3.addChild("leg_left_24", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leg_lower_r9 = leg_left_24.addChild("leg_lower_r9", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r9 = leg_left_24.addChild("leg_upper_r9", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_25 = leg_left_upper3.addChild("leg_left_25", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 8.0F));

		ModelPartData leg_lower_r10 = leg_left_25.addChild("leg_lower_r10", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r10 = leg_left_25.addChild("leg_upper_r10", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_26 = leg_left_upper3.addChild("leg_left_26", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 16.0F));

		ModelPartData leg_lower_r11 = leg_left_26.addChild("leg_lower_r11", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r11 = leg_left_26.addChild("leg_upper_r11", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_27 = leg_left_upper3.addChild("leg_left_27", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 24.0F));

		ModelPartData leg_lower_r12 = leg_left_27.addChild("leg_lower_r12", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r12 = leg_left_27.addChild("leg_upper_r12", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_lower3 = leg_right.addChild("leg_left_lower3", ModelPartBuilder.create(), ModelTransform.of(23.0F, 4.0F, -6.0F, 0.0F, 0.0F, -1.5708F));

		ModelPartData leg_left_28 = leg_left_lower3.addChild("leg_left_28", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leg_lower_r13 = leg_left_28.addChild("leg_lower_r13", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r13 = leg_left_28.addChild("leg_upper_r13", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_29 = leg_left_lower3.addChild("leg_left_29", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 8.0F));

		ModelPartData leg_lower_r14 = leg_left_29.addChild("leg_lower_r14", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r14 = leg_left_29.addChild("leg_upper_r14", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_30 = leg_left_lower3.addChild("leg_left_30", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 16.0F));

		ModelPartData leg_lower_r15 = leg_left_30.addChild("leg_lower_r15", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r15 = leg_left_30.addChild("leg_upper_r15", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_31 = leg_left_lower3.addChild("leg_left_31", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 24.0F));

		ModelPartData leg_lower_r16 = leg_left_31.addChild("leg_lower_r16", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r16 = leg_left_31.addChild("leg_upper_r16", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_right_upper3 = leg_right.addChild("leg_right_upper3", ModelPartBuilder.create(), ModelTransform.pivot(-14.0F, 0.0F, -6.0F));

		ModelPartData leg_right_24 = leg_right_upper3.addChild("leg_right_24", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leg_lower_r17 = leg_right_24.addChild("leg_lower_r17", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_upper_r17 = leg_right_24.addChild("leg_upper_r17", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_right_25 = leg_right_upper3.addChild("leg_right_25", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 8.0F));

		ModelPartData leg_lower_r18 = leg_right_25.addChild("leg_lower_r18", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_upper_r18 = leg_right_25.addChild("leg_upper_r18", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_right_26 = leg_right_upper3.addChild("leg_right_26", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 16.0F));

		ModelPartData leg_lower_r19 = leg_right_26.addChild("leg_lower_r19", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_upper_r19 = leg_right_26.addChild("leg_upper_r19", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_right_27 = leg_right_upper3.addChild("leg_right_27", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 24.0F));

		ModelPartData leg_lower_r20 = leg_right_27.addChild("leg_lower_r20", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_upper_r20 = leg_right_27.addChild("leg_upper_r20", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_right_lower3 = leg_right.addChild("leg_right_lower3", ModelPartBuilder.create(), ModelTransform.of(-23.0F, 4.0F, -6.0F, 0.0F, 0.0F, 1.5708F));

		ModelPartData leg_right_28 = leg_right_lower3.addChild("leg_right_28", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leg_lower_r21 = leg_right_28.addChild("leg_lower_r21", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_upper_r21 = leg_right_28.addChild("leg_upper_r21", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_right_29 = leg_right_lower3.addChild("leg_right_29", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 8.0F));

		ModelPartData leg_lower_r22 = leg_right_29.addChild("leg_lower_r22", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_upper_r22 = leg_right_29.addChild("leg_upper_r22", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_right_30 = leg_right_lower3.addChild("leg_right_30", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 16.0F));

		ModelPartData leg_lower_r23 = leg_right_30.addChild("leg_lower_r23", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_upper_r23 = leg_right_30.addChild("leg_upper_r23", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_right_31 = leg_right_lower3.addChild("leg_right_31", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 24.0F));

		ModelPartData leg_lower_r24 = leg_right_31.addChild("leg_lower_r24", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_upper_r24 = leg_right_31.addChild("leg_upper_r24", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData wing = body.addChild("wing", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 16.0F, -8.0F));

		ModelPartData wing_left_lower = wing.addChild("wing_left_lower", ModelPartBuilder.create(), ModelTransform.of(9.0F, -18.0F, 10.0F, 0.0F, 0.0F, -0.7418F));

		ModelPartData leg_left_8 = wing_left_lower.addChild("leg_left_8", ModelPartBuilder.create(), ModelTransform.pivot(5.2226F, 13.142F, -12.0F));

		ModelPartData leg_lower_r25 = leg_left_8.addChild("leg_lower_r25", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r25 = leg_left_8.addChild("leg_upper_r25", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_9 = wing_left_lower.addChild("leg_left_9", ModelPartBuilder.create(), ModelTransform.pivot(5.2226F, 13.142F, -4.0F));

		ModelPartData leg_lower_r26 = leg_left_9.addChild("leg_lower_r26", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r26 = leg_left_9.addChild("leg_upper_r26", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_10 = wing_left_lower.addChild("leg_left_10", ModelPartBuilder.create(), ModelTransform.pivot(5.2226F, 13.142F, 4.0F));

		ModelPartData leg_lower_r27 = leg_left_10.addChild("leg_lower_r27", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r27 = leg_left_10.addChild("leg_upper_r27", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_left_11 = wing_left_lower.addChild("leg_left_11", ModelPartBuilder.create(), ModelTransform.pivot(5.2226F, 13.142F, 12.0F));

		ModelPartData leg_lower_r28 = leg_left_11.addChild("leg_lower_r28", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leg_upper_r28 = leg_left_11.addChild("leg_upper_r28", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData wing_right_lower = wing.addChild("wing_right_lower", ModelPartBuilder.create(), ModelTransform.of(-9.0F, -18.0F, 10.0F, 0.0F, 0.0F, 0.7418F));

		ModelPartData leg_right_12 = wing_right_lower.addChild("leg_right_12", ModelPartBuilder.create(), ModelTransform.pivot(-5.2226F, 13.142F, -12.0F));

		ModelPartData leg_lower_r29 = leg_right_12.addChild("leg_lower_r29", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_upper_r29 = leg_right_12.addChild("leg_upper_r29", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_right_13 = wing_right_lower.addChild("leg_right_13", ModelPartBuilder.create(), ModelTransform.pivot(-5.2226F, 13.142F, -4.0F));

		ModelPartData leg_lower_r30 = leg_right_13.addChild("leg_lower_r30", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_upper_r30 = leg_right_13.addChild("leg_upper_r30", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_right_14 = wing_right_lower.addChild("leg_right_14", ModelPartBuilder.create(), ModelTransform.pivot(-5.2226F, 13.142F, 4.0F));

		ModelPartData leg_lower_r31 = leg_right_14.addChild("leg_lower_r31", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_upper_r31 = leg_right_14.addChild("leg_upper_r31", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_right_15 = wing_right_lower.addChild("leg_right_15", ModelPartBuilder.create(), ModelTransform.pivot(-5.2226F, 13.142F, 12.0F));

		ModelPartData leg_lower_r32 = leg_right_15.addChild("leg_lower_r32", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData leg_upper_r32 = leg_right_15.addChild("leg_upper_r32", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -9.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		body.render(matrices, vertexConsumer, light, overlay, color);
	}
}