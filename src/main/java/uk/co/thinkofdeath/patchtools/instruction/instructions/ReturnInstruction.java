package uk.co.thinkofdeath.patchtools.instruction.instructions;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;
import uk.co.thinkofdeath.patchtools.PatchScope;
import uk.co.thinkofdeath.patchtools.instruction.Instruction;
import uk.co.thinkofdeath.patchtools.instruction.InstructionHandler;
import uk.co.thinkofdeath.patchtools.patch.PatchInstruction;
import uk.co.thinkofdeath.patchtools.wrappers.ClassSet;

public class ReturnInstruction implements InstructionHandler {

    @Override
    public boolean check(ClassSet classSet, PatchScope scope, PatchInstruction instruction, MethodNode method, AbstractInsnNode insn) {
        if (!(insn instanceof InsnNode)) {
            return false;
        }
        if (insn.getOpcode() != Type.getMethodType(method.desc).getReturnType().getOpcode(Opcodes.IRETURN)) {
            return false;
        }
        return true;
    }

    @Override
    public AbstractInsnNode create(ClassSet classSet, PatchScope scope, PatchInstruction instruction, MethodNode method) {
        return new InsnNode(Type.getMethodType(method.desc).getReturnType().getOpcode(Opcodes.IRETURN));
    }

    @Override
    public boolean print(Instruction instruction, StringBuilder patch, MethodNode method, AbstractInsnNode insn) {
        if (!(insn instanceof InsnNode)) {
            return false;
        }
        if (insn.getOpcode() != Type.getMethodType(method.desc).getReturnType().getOpcode(Opcodes.IRETURN)) {
            return false;
        }
        patch.append("return");
        return true;
    }
}
