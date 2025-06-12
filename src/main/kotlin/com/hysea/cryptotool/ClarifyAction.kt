package com.hysea.cryptotool

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.ui.Messages

class ClarifyAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val project = e.project ?: return
        val document = editor.document
        val selectionModel = editor.selectionModel

        if (!selectionModel.hasSelection()) {
            Messages.showInfoMessage(project, "请选择要反混淆的文本", "提示")
            return
        }

        val selectedText = selectionModel.selectedText ?: return

        val seed = AESEncryptionSettings.getInstance(project).state.seed
        if (seed.isNullOrBlank()) {
            Messages.showInfoMessage(project, "请在 Settings/Preferences -> Tools -> AES Encryption 中配置seed", "提示")
            return
        }

        try {
            if (PseudoRandomSubstitution.substitutionMap.isEmpty()) {
                PseudoRandomSubstitution.generateSubstitutionMap(seed.toInt())
            }

            if (PseudoRandomSubstitution.reverseSubstitutionMap.isEmpty()) {
                PseudoRandomSubstitution.generateReverseSubstitutionMap(PseudoRandomSubstitution.substitutionMap)
            }
        } catch (ex: Exception) {
            Messages.showErrorDialog(project, "反混淆失败：${ex.message}", "反混淆失败")
        }

        try {
            val clarifyText =
                PseudoRandomSubstitution.substitute(selectedText, PseudoRandomSubstitution.reverseSubstitutionMap)
            WriteCommandAction.runWriteCommandAction(project) {
                document.replaceString(selectionModel.selectionStart, selectionModel.selectionEnd, clarifyText)
            }
        } catch (ex: Exception) {
            Messages.showErrorDialog(project, "反混淆失败：${ex.message}", "反混淆失败")
        }
    }
}