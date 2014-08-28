package uk.co.thinkofdeath.patchtools.instruction.instructions;

import com.google.common.base.Joiner;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;
import uk.co.thinkofdeath.patchtools.PatchScope;
import uk.co.thinkofdeath.patchtools.instruction.Instruction;
import uk.co.thinkofdeath.patchtools.instruction.InstructionHandler;
import uk.co.thinkofdeath.patchtools.patch.PatchInstruction;
import uk.co.thinkofdeath.patchtools.wrappers.ClassSet;

public class PushStringInstruction implements InstructionHandler {
    @Override
    public boolean check(ClassSet classSet, PatchScope scope, PatchInstruction patchInstruction, MethodNode method, AbstractInsnNode insn) {
        if (!(insn instanceof LdcInsnNode)) {
            return false;
        }
        LdcInsnNode ldcInsnNode = (LdcInsnNode) insn;
        String cst = Joiner.on(' ').join(patchInstruction.params);

        if (cst.equals("*")) {
            return true;
        }

        if (ldcInsnNode.cst instanceof String) {
            if (!cst.startsWith("\"") || !cst.endsWith("\"")) {
                return false;
            }
            cst = cst.substring(1, cst.length() - 1);
            if (!ldcInsnNode.cst.equals(cst)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public AbstractInsnNode create(ClassSet classSet, PatchScope scope, PatchInstruction instruction, MethodNode method) {
        String cst = Joiner.on(' ').join(instruction.params);
        return new LdcInsnNode(Utils.parseConstant(cst));
    }

    @Override
    public boolean print(Instruction instruction, StringBuilder patch, MethodNode method, AbstractInsnNode insn) {
        if (!(insn instanceof LdcInsnNode) || !(((LdcInsnNode) insn).cst instanceof String)) {
            return false;
        }
        patch.append("push-string ");
        Utils.printConstant(patch, ((LdcInsnNode) insn).cst);
        return true;
    }
}
