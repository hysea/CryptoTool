package com.hysea.cryptotool

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.ui.Messages

class ConfuseAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val project = e.project ?: return
        val document = editor.document
        val selectionModel = editor.selectionModel

        if (!selectionModel.hasSelection()) {
            Messages.showInfoMessage(project, "请选择要混淆的文本", "提示")
            return
        }

        val selectedText = selectionModel.selectedText ?: return
        println("selectedText==> $selectedText")
        val seed = AESEncryptionSettings.getInstance(project).state.seed
        if (seed.isNullOrBlank()) {
            Messages.showInfoMessage(project, "请在 Settings/Preferences -> Tools -> AES Encryption 中配置seed", "提示")
            return
        }
        println("seed==> $seed")
        try {
            if (PseudoRandomSubstitution.substitutionMap.isEmpty()) {
                PseudoRandomSubstitution.generateSubstitutionMap(seed.toInt())
            }
        } catch (ex: Exception) {
            Messages.showErrorDialog(project, "混淆失败：${ex.message}", "混淆失败")
        }

        println(PseudoRandomSubstitution.substitutionMap)

        try {
            val confuseText =
                PseudoRandomSubstitution.substitute(selectedText, PseudoRandomSubstitution.substitutionMap)
            println("confuseText==> $confuseText")
            WriteCommandAction.runWriteCommandAction(project) {
                document.replaceString(selectionModel.selectionStart, selectionModel.selectionEnd, confuseText)
            }
        } catch (ex: Exception) {
            Messages.showErrorDialog(project, "混淆失败：${ex.message}", "混淆失败")
        }
    }
}