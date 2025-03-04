package com.hysea.cryptotool

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBLabel
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField


class AESEncryptionConfigurable(private val project: Project) : Configurable {
    private val keyTextField = JTextField()

    override fun getDisplayName(): String {
        return "AES Encryption"
    }

    override fun createComponent(): JComponent {
        keyTextField.preferredSize = Dimension(300, keyTextField.preferredSize.height)
        val panel = JPanel()
        val label = JBLabel("AES Key:")
        panel.add(label)
        panel.add(keyTextField)
        return panel
    }

    override fun isModified(): Boolean {
        val settings = AESEncryptionSettings.getInstance(project)
        return keyTextField.text != settings.state.aesKey
    }

    override fun apply() {
        val settings = AESEncryptionSettings.getInstance(project)
        settings.state.aesKey = keyTextField.text
    }

    override fun reset() {
        val settings = AESEncryptionSettings.getInstance(project)
        keyTextField.text = settings.state.aesKey
    }

    private fun loadSettings() {
        val settings = project.getService(AESEncryptionSettings::class.java)
        keyTextField.text = settings.state.aesKey
    }
}