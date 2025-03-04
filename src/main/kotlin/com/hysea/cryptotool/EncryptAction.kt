package com.hysea.cryptotool

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.ui.Messages

class EncryptAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val project = e.project ?: return
        val document = editor.document
        val selectionModel = editor.selectionModel

        if (!selectionModel.hasSelection()) {
            Messages.showInfoMessage(project, "请选择要加密的文本", "提示")
            return
        }

        val selectedText = selectionModel.selectedText ?: return

        val key = AESEncryptionSettings.getInstance(project).state.aesKey
        if (key.isNullOrBlank()) {
            Messages.showInfoMessage(project, "请在 Settings/Preferences -> Tools -> AES Encryption 中配置key", "提示")
            return
        }

        try {
            val encryptedText = AESUtil.encrypt(selectedText, key)
            WriteCommandAction.runWriteCommandAction(project) {
                document.replaceString(selectionModel.selectionStart, selectionModel.selectionEnd, encryptedText)
            }
        } catch (ex: Exception) {
            Messages.showErrorDialog(project, "加密失败：${ex.message}", "AES加密错误")
        }
    }
}