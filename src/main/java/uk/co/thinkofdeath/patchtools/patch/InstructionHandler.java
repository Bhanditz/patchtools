package uk.co.thinkofdeath.patchtools.patch;

import org.objectweb.asm.tree.AbstractInsnNode;
import uk.co.thinkofdeath.patchtools.PatchScope;
import uk.co.thinkofdeath.patchtools.wrappers.ClassSet;

public interface InstructionHandler {

    void check(ClassSet classSet, PatchScope scope, PatchInstruction instruction, AbstractInsnNode insn);

    AbstractInsnNode create(ClassSet classSet, PatchScope scope, PatchInstruction instruction);
}