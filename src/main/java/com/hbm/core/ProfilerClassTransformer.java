package com.hbm.core;

import com.hbm.main.ModEventHandlerClient;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.util.Arrays;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class ProfilerClassTransformer implements IClassTransformer {

	private static final String[] classesBeingTransformed = { "net.minecraft.profiler.Profiler" };

	@Override
	public byte[] transform(String name, String transformedName, byte[] classBeingTransformed) {
		boolean isObfuscated = !name.equals(transformedName);
		int index = Arrays.asList(classesBeingTransformed).indexOf(transformedName);
		return index != -1 ? transform(index, classBeingTransformed, isObfuscated) : classBeingTransformed;
	}

	private static byte[] transform(int index, byte[] classBeingTransformed, boolean isObfuscated) {
		System.out.println("Transforming: " + classesBeingTransformed[index]);
		try {
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(classBeingTransformed);
			classReader.accept(classNode, 0);

			switch (index) {
			case 0:
				transformProfiler(classNode, isObfuscated);
				break;
			}

			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			classNode.accept(classWriter);
			return classWriter.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classBeingTransformed;
	}

	private static void transformProfiler(ClassNode profilerClass, boolean isObfuscated) {

		for (MethodNode method : profilerClass.methods) {
			if ((method.name.equals("endStartSection") || method.name.equals("func_76318_c")) && method.desc.equals("(Ljava/lang/String;)V")) {
				InsnList toInsert = new InsnList();
				toInsert.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(ModEventHandlerClient.class), "profilerStart", "(Ljava/lang/String;)V", false));
				
				method.instructions.insertBefore(method.instructions.get(2), toInsert);
				method.instructions.insertBefore(method.instructions.get(2), new VarInsnNode(ALOAD, 1));
			}
		}
	}

}
